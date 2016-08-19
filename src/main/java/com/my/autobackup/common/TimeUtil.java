
package com.my.autobackup.common;


/**
 * Time util class
 * 
 * @author Administrator
 */
public class TimeUtil {

    /**
     * Calculate elapsed time.
     * 
     * @param time1 Time1
     * @param time2 Time2
     * @return Elapsed time.
     */
    public static String calculateElapsedTime(long time1, long time2) {
        long timeDiff = Math.abs(time1 - time2);
        long hours = timeDiff / (60 * 60 * 1000);
        long minutes = (timeDiff - hours * 60 * 60 * 1000) / (60 * 1000);
        long seconds = (timeDiff - hours * 60 * 60 * 1000 - minutes * 60 * 1000) / 1000;

        StringBuffer sb = new StringBuffer();
        if (hours == 1) {
            sb.append(hours + " hour, ");
        }
        if (hours > 1) {
            sb.append(hours + " hours, ");
        }

        if (minutes == 1) {
            sb.append(minutes + " minute, ");
        }
        if (minutes > 1) {
            sb.append(minutes + " minutes, ");
        }

        if (seconds == 1) {
            sb.append(seconds + " second.");
        }
        if (seconds > 1) {
            sb.append(seconds + " seconds.");
        }
        return sb.toString();
    }
}
