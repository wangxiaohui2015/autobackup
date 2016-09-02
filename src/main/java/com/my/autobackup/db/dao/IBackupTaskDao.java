
package com.my.autobackup.db.dao;

import com.my.autobackup.db.entity.BackupTask;

public interface IBackupTaskDao {
    int addBackupTask(BackupTask backupTask);
}
