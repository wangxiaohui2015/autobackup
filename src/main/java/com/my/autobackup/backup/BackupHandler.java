package com.my.autobackup.backup;

import java.io.File;
import java.util.List;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.my.autobackup.backup.util.BackupConfigPropertiesUtil;
import com.my.autobackup.common.FileUtil;

/**
 * This is the backup task, extends TimerTask.
 * 
 * @author Administrator
 */
public class BackupHandler extends TimerTask {

    private static Logger logger = Logger.getLogger(BackupHandler.class);

    @Override
    public void run() {
        try {
            logger.info("Begin to execute backup task.");
            BackupConfigPropertiesUtil.getInstance().reloadProperties();
            BackupController controller = new BackupController();
            executeBackupTask(controller);

            // Waiting for all tasks to finish.
            controller.await();
            logger.info("End to execute backup task.");
        } catch (Throwable e) {
            logger.error("Exception occurred while executing backup task.", e);
        }
    }

    private void executeBackupTask(BackupController controller) {
        List<String> sourceKeys = BackupConfigPropertiesUtil.getInstance().getAllSourceKeys();
        for (String sourceKey : sourceKeys) {
            String destKey = BackupConfigPropertiesUtil.getInstance()
                            .getDestKeyBySourceKey(sourceKey);
            String sourceDir = BackupConfigPropertiesUtil.getInstance().getValue(sourceKey);
            String destDir = BackupConfigPropertiesUtil.getInstance().getValue(destKey);
            if (null == sourceDir || "".equals(sourceDir)) {
                logger.warn("sourceDir is null or empty, sourceDir: " + sourceDir + ", destDir: "
                                + destDir);
                continue;
            }

            if (null == destDir || "".equals(destDir)) {
                logger.warn("destDir is null or empty, sourceDir: " + sourceDir + ", destDir: "
                                + destDir);
                continue;
            }

            File sourceFile = new File(sourceDir);
            File destFile = new File(BackupConfigPropertiesUtil.getInstance().getValue(destKey));

            if (FileUtil.isSubFile(sourceFile, destFile)) {
                logger.warn("Source dir is the sub dir of dest dir, or dest dir is the sub dir of source dir, skip this entry, source dir: "
                                + sourceDir + ", dest dir: " + destDir);
                continue;
            }

            logger.info("Processing backup task, sourceDir: " + sourceDir + ", destDir: "
                            + destDir);

            if (!sourceFile.isDirectory() || !sourceFile.exists()) {
                logger.warn("Source dir isn't a directory or doesn't exist, sourceDir: "
                                + sourceFile);
                continue;
            }

            if (destFile.exists()) {
                if (destFile.isFile()) {
                    logger.warn("Dest dir exists, but it isn't a directory.");
                    continue;
                }
            }

            // Append source file name to dest file name
            destDir = destDir + File.separator + sourceFile.getName();

            if (!destFile.exists()) {
                logger.info("Dest dir doesn't exist, create it now.");
                destFile.mkdirs();
            }
            processBackupTask(sourceDir, destDir, controller);
        }
    }

    private void processBackupTask(String sourceDir, String destDir,
                    BackupController controller) {
        File sourceFile = new File(sourceDir);
        File[] files = sourceFile.listFiles();
        for (File file : files) {
            File newDestFile = new File(destDir + File.separator + file.getName());
            if (file.isFile()) {
                if (!newDestFile.exists()) {
                    FileEncryptionExecutor.getInstance()
                                    .submitTask(new FileEncryptionThread(file.getAbsolutePath(),
                                                    newDestFile.getAbsolutePath(), controller),
                                    controller);
                } else {
                    // ... Check if this file is changed or not.
                }
            } else {
                if (!newDestFile.exists()) {
                    newDestFile.mkdir();
                }
                processBackupTask(file.getAbsolutePath(), newDestFile.getAbsolutePath(),
                                controller);
            }
        }
    }
}
