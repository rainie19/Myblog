package com.macie.util.dbutil;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import static java.sql.Types.NULL;

/**
 * @author Macie
 * @date 2020/9/29 -18:44
 */

public class SetPsParams {
    /**
     * @desc 设置ps参数
     * @param params
     * @param ps
     * @throws SQLException
     */
    static void setPsParam(ArrayList<Object> params, PreparedStatement ps) throws SQLException {
        if (params != null) {
            int loc = 1;
            for (Object o : params) {
                setParamInit(loc++, o, ps);
            }
        }
    }

    /**
     * 判断参数类型并转换
     *
     * @param index
     * @param obj
     * @param ps
     * @throws SQLException
     */
    public static void setParamInit(int index, Object obj, PreparedStatement ps) throws SQLException {
        if(obj == null) {
            setParamNull(index, NULL, ps);
        }
        if(obj instanceof Integer) {
            setParamInt(index, (Integer) obj, ps);
        }
        if(obj instanceof Long) {
            setParamLong(index, (Long) obj, ps);
        }
        if(obj instanceof Double) {
            setParamDouble(index, (Double) obj, ps);
        }
        if(obj instanceof String) {
            setParamString(index, (String) obj, ps);
        }
        if(obj instanceof Date) {
            setParamDate(index, (Date) obj, ps);
        }
    }

    /**
     * 设置NULL值
     *
     * @param index
     * @param sqlType
     * @param ps
     * @throws SQLException
     */
    public static void setParamNull(int index, int sqlType, PreparedStatement ps) throws SQLException {
        ps.setNull(index, sqlType);
    }

    /**
     * 设置int类型的参数
     *
     * @param index
     * @param param
     * @param ps
     * @throws SQLException
     */
    public static void setParamInt(int index, Integer param, PreparedStatement ps) throws SQLException {
        ps.setInt(index, param);
    }

    /**
     * 设置Long类型的参数
     *
     * @param index
     * @param param
     * @param ps
     * @throws SQLException
     */
    public static void setParamLong(int index, Long param, PreparedStatement ps) throws SQLException {
        ps.setLong(index, param);
    }

    /**
     * 设置Double类型的参数
     *
     * @param index
     * @param param
     * @param ps
     * @throws SQLException
     */
    public static void setParamDouble(int index, Double param, PreparedStatement ps) throws SQLException {
        ps.setDouble(index, param);
    }

    /**
     * 设置String类型的参数
     *
     * @param index
     * @param param
     * @param ps
     * @throws SQLException
     */
    public static void setParamString(int index, String param, PreparedStatement ps) throws SQLException {
        ps.setString(index, param);
    }

    /**
     * 设置Date->Timestamp类型的参数
     *
     * @param index
     * @param param
     * @param ps
     * @throws SQLException
     */
    public static void setParamDate(int index, Date param, PreparedStatement ps) throws SQLException {
        Timestamp timestamp = new Timestamp(param.getTime());
        ps.setTimestamp(index, timestamp);
    }
}
