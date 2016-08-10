package com.my.autobackup.restore;

import java.io.File;
import java.util.Scanner;

import com.my.autobackup.common.FileUtil;
import com.my.autobackup.common.ProgressUtil;

/**
 * The main entry to decrypt file.
 * 
 * @author Administrator
 *
 */
public class RestoreMain {

	private static Scanner scanner = new Scanner(System.in);
	private static ProgressUtil progressUtil = null;

	private static String enterString(String message, int minLen, int maxLen) {
		prt(message);
		while (true) {
			String str = scanner.nextLine();
			if (str.length() >= minLen && str.length() <= maxLen) {
				return str;
			} else {
				prt("Invalid input, enter again: ");
			}
		}
	}

	public static void prt(String str) {
		System.out.print(str);
	}

	public static void prt(int str) {
		System.out.print(str);
	}

	public static void prtln(String str) {
		System.out.println(str);
	}

	public static void prtln(int str) {
		System.out.println(str);
	}

	private static void executeRestoreTask(File sourceFile, File destFile,
			String key) {
		File[] files = sourceFile.listFiles();
		for (File file : files) {
			File newDestFile = new File(destFile.getAbsoluteFile()
					+ File.separator + file.getName());
			if (file.isFile()) {
				try {
					FileDecryptor.decryptFile(key, file.getAbsolutePath(),
							newDestFile.getAbsolutePath());
					progressUtil.showProgressByIncress(file.length());
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				newDestFile.mkdir();
				executeRestoreTask(file, newDestFile, key);
			}
		}
	}

	private static void startToRestore() {
		prtln("");
		prtln("*********** WELCOME TO DATA RESTORE *************");
		prtln("");
		String sourceDir = enterString("Enter source dir: ", 1, 256);
		File sourceFile = new File(sourceDir);
		if (!sourceFile.exists() || !sourceFile.isDirectory()) {
			prtln("Invalid input, source dir doesn't exist or isn't a directory.");
			System.exit(-1);
		}

		String destDir = enterString("Enter target dir: ", 1, 256);
		File destFile = new File(destDir);
		if (!destFile.exists() || !destFile.isDirectory()) {
			prtln("Invalid input, dest dir doesn't exist or isn't a directory.");
			System.exit(-1);
		}

		if (FileUtil.isSubFile(sourceFile, destFile)) {
			prtln("Source dir cannot be the sub dir of dest dir, and dest dir cannot be the sub dir of source dir.");
			System.exit(-1);
		}

		String password = enterString("Enter password: ", 1, 256);

		// Append source file name to dest file path
		destFile = new File(destFile.getAbsolutePath() + File.separator
				+ sourceFile.getName());

		long totalSize = FileUtil.getDirSize(sourceFile);
		progressUtil = new ProgressUtil(totalSize);
		prtln("");
		prtln("Data Size: " + FileUtil.getFileSizeString(totalSize));
		prtln("");

		try {
			Thread.sleep(1000 * 2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		prtln("Executing...");
		prtln("");
		executeRestoreTask(sourceFile, destFile, password);
		prtln("");
		prtln("Finished.");
		prtln("");
	}

	public static void main(String[] args) {
		startToRestore();
	}
}