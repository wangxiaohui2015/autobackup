package com.my.autobackup.backup;

import org.apache.log4j.Logger;

import com.my.autobackup.backup.util.ServiceConfigPropertiesUtil;

/**
 * File encryption thread object.
 * 
 * @author Administrator
 */
public class FileEncryptionThread implements Runnable {

    private static Logger logger = Logger.getLogger(FileEncryptionThread.class);

    private String sourceFile;
    private String destFile;
    private BackupController controller;

    /**
     * Constructor method.
     * 
     * @param sourceFile source file
     * @param destFile destination file
     * @param controller BackupTaskController object
     */
    public FileEncryptionThread(String sourceFile, String destFile,
                    BackupController controller) {
        this.sourceFile = sourceFile;
        this.destFile = destFile;
        this.controller = controller;
    }

    public void run() {
        try {
            FileEncryptor.encryptFile(ServiceConfigPropertiesUtil.getInstance().getBackupKey(),
                            sourceFile, destFile);
            logger.info("Backup succeed, source: " + sourceFile + ", dest: " + destFile);
        } catch (Exception e) {
            logger.error("Backup failed, source: " + sourceFile + ", source: " + destFile, e);
        } finally {
            controller.finishTask();
        }
    }
}
