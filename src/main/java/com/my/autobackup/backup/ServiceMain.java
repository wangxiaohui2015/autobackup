package com.my.autobackup.backup;

import java.util.Timer;

import org.apache.log4j.Logger;

import com.my.autobackup.backup.util.ServiceConfigPropertiesUtil;

/**
 * This is the service entry, used to start and stop service.
 * 
 * @author Administrator
 */
public class ServiceMain {

	private static Logger logger = Logger.getLogger(ServiceMain.class);

	private static String rootDir = "";
	private static Timer timer = new Timer();

	/**
	 * Start service.
	 * 
	 * @param args
	 *            service arguments.
	 */
	public static void startService(String[] args) {
		init(args);
		logger.info("Service is starting...");
		timer.schedule(new BackupTask(), ServiceConfigPropertiesUtil
				.getInstance().getBackupStartupDelay(),
				ServiceConfigPropertiesUtil.getInstance().getBackupInterval());
	}

	/**
	 * Stop service.
	 * 
	 * @param args
	 *            service arguments.
	 */
	public static void stopService(String[] args) {
		logger.info("Service is stopping...");
		FileEncryptionExecutor.getInstance().shutDownExecutorService();
		shutdownTimer();
		logger.info("Service is stopped.");
	}

	/**
	 * Get installation root directory.
	 * 
	 * @return
	 */
	public static String getRootDir() {
		return rootDir;
	}

	private static void shutdownTimer() {
		logger.info("Shutting down main timer...");
		timer.cancel();
		logger.info("Shut down main timer.");
	}

	private static void init(String[] args) {
		rootDir = System.getProperty("rootDir");
		if (null == rootDir || "".equals(rootDir)) {
			logger.error("Cannot find environment var: rootDir, system exits.");
			System.exit(-1);
		}
	}
}