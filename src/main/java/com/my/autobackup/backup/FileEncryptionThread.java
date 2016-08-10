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
	private BackupTaskController controller;

	/**
	 * Constructor method.
	 * 
	 * @param sourceFile
	 *            source file
	 * @param destFile
	 *            destination file
	 * @param controller
	 *            BackupTaskController object
	 */
	public FileEncryptionThread(String sourceFile, String destFile,
			BackupTaskController controller) {
		this.sourceFile = sourceFile;
		this.destFile = destFile;
		this.controller = controller;
	}

	public void run() {
		try {
			FileEncryptor.encryptFile(ServiceConfigPropertiesUtil.getInstance()
					.getBackupKey(), sourceFile, destFile);
			logger.info("Backup file successfully, sourceFile: " + sourceFile
					+ ", destFile: " + destFile);
		} catch (Exception e) {
			logger.error("Backup file failed, sourceFile: " + sourceFile
					+ ", destFile: " + destFile, e);
		} finally {
			controller.finishTask();
		}
	}
}
