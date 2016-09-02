
package com.my.autobackup.db.entity;

public class BackupTask {
    private String name;
    private String sourceDir;
    private String destDir;
    private String schedule;
    private String encryptAlgorithm;
    private String encryptPassword;
    private String status;
    private long nextBackupTime;
    private String description;

    public Object[] getDBParameters() {
        return new Object[] {name, sourceDir, destDir, schedule, encryptAlgorithm, encryptPassword,
                status, nextBackupTime, description};
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSourceDir() {
        return sourceDir;
    }

    public void setSourceDir(String sourceDir) {
        this.sourceDir = sourceDir;
    }

    public String getDestDir() {
        return destDir;
    }

    public void setDestDir(String destDir) {
        this.destDir = destDir;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getEncryptAlgorithm() {
        return encryptAlgorithm;
    }

    public void setEncryptAlgorithm(String encryptAlgorithm) {
        this.encryptAlgorithm = encryptAlgorithm;
    }

    public String getEncryptPassword() {
        return encryptPassword;
    }

    public void setEncryptPassword(String encryptPassword) {
        this.encryptPassword = encryptPassword;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getNextBackupTime() {
        return nextBackupTime;
    }

    public void setNextBackupTime(long nextBackupTime) {
        this.nextBackupTime = nextBackupTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
