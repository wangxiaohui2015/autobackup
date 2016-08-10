package com.my.autobackup.common;

import com.my.autobackup.restore.RestoreMain;

/**
 * Progress util, used to show progress when encrypt or decrypt.
 * 
 * @author Administrator
 */
public class ProgressUtil {
	private long totalPoints = 0;
	private int index = 1;
	private long incressPoints = 0;

	/**
	 * Constructor method.
	 * 
	 * @param totalPoints
	 *            total points, in general, we need to pass the size of a file
	 *            or a directory.
	 */
	public ProgressUtil(long totalPoints) {
		super();
		this.totalPoints = totalPoints;
	}

	/**
	 * Show current progress.
	 * 
	 * @param currentPoint
	 *            current total point
	 */
	public void showProgress(long currentPoint) {
		if (totalPoints == 0) {
			return;
		}
		if (currentPoint * 1.0 / totalPoints >= (index / 10.0)) {
			RestoreMain.prtln("Completed " + ((index * 10)) + "%.");
			index += 1;
		}
	}

	/**
	 * Show current progress.
	 * 
	 * @param incressPoint
	 *            Incress point
	 */
	public void showProgressByIncress(long incressPoint) {
		incressPoints += incressPoint;
		if (incressPoints * 1.0 / totalPoints >= (index / 10.0)) {
			index = (int) ((incressPoints * 1.0 / totalPoints) * 10);
			RestoreMain.prtln("Completed " + ((index * 10)) + "%.");
			index += 1;
		}
	}
}
