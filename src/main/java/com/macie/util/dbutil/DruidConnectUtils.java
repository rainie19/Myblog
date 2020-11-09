package com.macie.util.dbutil;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Macie
 * @date 2020/9/25 -21:00
 */
public class DruidConnectUtils {
    private static DruidConnectUtils druidConnectUtils = null;
    private static DruidDataSource dds;

    /**
     * 初始化DataSource
     */
    static {
        Properties prop = new Properties();
        try {
            File webRootPath= new File(DruidConnectUtils.class.getResource("").getPath());
            prop.load(DruidConnectUtils.class.getResourceAsStream("../../../../druid.properties"));
            dds = (DruidDataSource) DruidDataSourceFactory.createDataSource(prop);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private DruidConnectUtils() {}

    public static synchronized DruidConnectUtils getInstance() {
        if(druidConnectUtils == null) {
            druidConnectUtils = new DruidConnectUtils();
        }
        return druidConnectUtils;
    }

    public static DruidPooledConnection getConnection() throws SQLException {
        return dds.getConnection();
    }

    public static void closeConnection(DruidPooledConnection conn, PreparedStatement ps, ResultSet rs) {
        if (rs != null) {
            try {
                ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
