package com.my.autobackup.backup.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.my.autobackup.backup.ServiceMain;

/**
 * Service configuration properties utility.
 * 
 * @author Administrator
 */
public class ServiceConfigPropertiesUtil {

	private static Logger logger = Logger
			.getLogger(ServiceConfigPropertiesUtil.class);
	private static Properties properties = new Properties();
	private static final ServiceConfigPropertiesUtil instance = new ServiceConfigPropertiesUtil();

	private String configFilePath = "";

	private static String KEY_BACKUP_STARTUP_DELAY = "backup.startup.delay";
	private static String KEY_BACKUP_INTERVAL = "backup.interval";
	private static String KEY_BACKUP_KEY = "backup.key";
	private static String KEY_BACKUP_THREADS = "backup.threads";

	private static String DEFAULT_SECURITY_KEY = "autobackup";

	private ServiceConfigPropertiesUtil() {
		configFilePath = ServiceMain.getRootDir() + File.separator + "conf"
				+ File.separator + "service_config.properties";
		try {
			InputStream in = new FileInputStream(configFilePath);
			properties.load(in);
		} catch (IOException e) {
			logger.error(
					"Failed to init ServiceConfigPropertiesUtil, service exits.",
					e);
			System.exit(-1);
		}
	}

	/**
	 * Get singleton object.
	 * 
	 * @return singleton object.
	 */
	public static ServiceConfigPropertiesUtil getInstance() {
		return instance;
	}

	/**
	 * Get backup startup delay, default time is 1 minutes.
	 * 
	 * @return backup task startup delay time.
	 */
	public long getBackupStartupDelay() {
		String strVal = properties.getProperty(KEY_BACKUP_STARTUP_DELAY);
		long longVal = 1000 * 60;
		try {
			long temLongVal = Long.parseLong(strVal);
			if (temLongVal < 0 || temLongVal > 1000 * 60 * 60 * 24) {
				logger.warn("Backup startup delay time is invalid, "
						+ temLongVal + ", use default: " + longVal);
			} else {
				longVal = temLongVal;
			}
		} catch (NumberFormatException e) {
			logger.error(
					"NumberFormatException occurred when getBackupStartupDelay.",
					e);
		}
		return longVal;
	}

	/**
	 * Get backup interval, default value is 1 hour.
	 * 
	 * @return backup interval
	 */
	public long getBackupInterval() {
		String strVal = properties.getProperty(KEY_BACKUP_INTERVAL);
		long longVal = 1000 * 60 * 60;
		try {
			long temLongVal = Long.parseLong(strVal);
			if (temLongVal < 0 || temLongVal > 1000 * 60 * 60 * 24 * 365) {
				logger.warn("Backup interval time is invalid, " + temLongVal
						+ ", use default: " + longVal);
			} else {
				longVal = temLongVal;
			}
		} catch (NumberFormatException e) {
			logger.error(
					"NumberFormatException occurred when getBackupInterval.", e);
		}
		return longVal;
	}

	/**
	 * Get backup security key, the default key is "autobackup".
	 * 
	 * @return backup security key.
	 */
	public String getBackupKey() {
		String key = properties.getProperty(KEY_BACKUP_KEY);
		if (null == key || "".equals(key)) {
			key = DEFAULT_SECURITY_KEY;
		}
		return key;
	}

	/**
	 * Get backup threads number, the default value is 3.
	 * 
	 * @return backup threads number.
	 */
	public int getThreadNumber() {
		String strVal = properties.getProperty(KEY_BACKUP_THREADS);
		int threadNumber = 3;
		try {
			int temThreadNumber = Integer.parseInt(strVal);
			if (temThreadNumber < 1 || temThreadNumber > 20) {
				logger.warn("Thread number is invalid, " + temThreadNumber
						+ ", use default: " + threadNumber);
			} else {
				threadNumber = temThreadNumber;
			}
		} catch (NumberFormatException e) {
			logger.error(
					"NumberFormatException occurred when get thread number.", e);
		}
		return threadNumber;
	}

	/**
	 * Get value by key.
	 * 
	 * @param key
	 *            key
	 * @return value.
	 */
	public String getProperties(String key) {
		return properties.getProperty(key);
	}
}
