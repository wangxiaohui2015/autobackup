
package com.my.autobackup.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class DBBase implements DBConstants {
    protected Logger logger = Logger.getLogger(this.getClass());

    protected void close(ResultSet rs, Statement s, Connection con) {
        close(rs);
        close(s, con);
    }

    protected void close(Statement s, Connection con) {
        close(s);
        close(con);
    }

    protected void close(Statement s) {
        try {
            if (null != s)
                s.close();
        } catch (Exception e) {
            logger.error(e);
        }
    }

    protected void close(ResultSet rs) {
        try {
            if (null != rs)
                rs.close();
        } catch (Exception e) {
            logger.error(e);
        }
    }

    protected void close(Connection con) {
        if (null != con) {
            try {
                con.close();
            } catch (Exception e) {
                logger.error(e);
            }
        }
    }
}
