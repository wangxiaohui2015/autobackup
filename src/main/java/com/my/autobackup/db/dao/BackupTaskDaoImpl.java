
package com.my.autobackup.db.dao;

import org.apache.commons.dbutils.QueryRunner;

import com.my.autobackup.db.DBBase;
import com.my.autobackup.db.DBHandler;
import com.my.autobackup.db.entity.BackupTask;

public class BackupTaskDaoImpl extends DBBase implements IBackupTaskDao {

    public int addBackupTask(BackupTask backupTask) {
        int result = 0;
        if (backupTask != null) {
            try {
                QueryRunner runner = new QueryRunner(DBHandler.getInstance().getSource());
                result = runner.update(BACKUP_TASK_INSERT, backupTask.getDBParameters());
                logger.info("Succeed to insert data to backup_task, name: " + backupTask.getName());
            } catch (Exception e) {
                logger.error("Failed to insert data to backup_task, name: " + backupTask.getName(),
                                e);
            }
        }
        return result;
    }
}
