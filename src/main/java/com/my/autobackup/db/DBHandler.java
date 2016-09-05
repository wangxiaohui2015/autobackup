
package com.my.autobackup.db;

import javax.sql.DataSource;

import org.sqlite.SQLiteConfig;
import org.sqlite.javax.SQLiteConnectionPoolDataSource;

import com.my.autobackup.backup.ServiceMain;

public class DBHandler extends DBBase {
    private static final DBHandler INSTANCE = new DBHandler();
    private DataSource source = null;
    private String dbPath = null;
    private String dbBusyTimeout = null;
    private String dbUrl = null;

    public static DBHandler getInstance() {
        return INSTANCE;
    }

    private DBHandler() {
        initDBConfig();
        source = createConnectionPool();
    }

    private void initDBConfig() {
        dbPath = ServiceMain.getRootDir() + "\\db\\autobackupdb";
        dbBusyTimeout = "120000"; // 2 minutes
        dbUrl = "jdbc:sqlite:" + dbPath;
    }

    private DataSource createConnectionPool() {
        SQLiteConnectionPoolDataSource source = new SQLiteConnectionPoolDataSource();
        SQLiteConfig config = source.getConfig();
        config.setBusyTimeout(dbBusyTimeout);
        source.setUrl(dbUrl);
        source.setEnforceForeinKeys(true);
        return source;
    }

    public DataSource getSource() {
        return source;
    }
}
