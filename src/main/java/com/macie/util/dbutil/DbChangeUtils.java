package com.macie.util.dbutil;

import com.alibaba.druid.pool.DruidPooledConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 数据库 增删改 操作
 * @author Macie
 * @date 2020/10/21 -0:46
 */
public class DbChangeUtils {
    /**
     * 修改,新增,删除操作
     * @param sql
     * @param params
     * @return
     */
    public static int changeDatabase(String sql, ArrayList<Object> params) {
        DruidConnectUtils druidConnectUtils = DruidConnectUtils.getInstance();
        DruidPooledConnection conn = null;
        PreparedStatement ps = null;
        int ret = -1;
        try {
            conn = druidConnectUtils.getConnection();
            ps = conn.prepareStatement(sql);
            SetPsParams.setPsParam(params, ps);
            ret = ps.executeUpdate();
            String sqlQueryId = "SELECT LAST_INSERT_ID()";
            ps = conn.prepareStatement(sqlQueryId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                ret = rs.getInt(1);
            }
            System.out.println("last insert id: "+ret);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidConnectUtils.closeConnection(conn, ps, null);
        }
        return ret;
    }
}
