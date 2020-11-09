package com.macie.util.dbutil;

import com.alibaba.druid.pool.DruidPooledConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


/**
 * @author Macie
 * @date 2020/9/25 -21:41
 */
public class DbRetrieveUtils {
    public static <T> ArrayList<T> retrieveBeanListByParams(String sql, ArrayList<Object> params, Class<T> classType) {
        DruidConnectUtils druidConnectUtils = DruidConnectUtils.getInstance();
        DruidPooledConnection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<T> beanList = null;
        try {
            conn = druidConnectUtils.getConnection();
            ps = conn.prepareStatement(sql);
            SetPsParams.setPsParam(params, ps);
            rs = ps.executeQuery();
            beanList = ResultSet2BeanMethods.setResultSet2BeanList(rs, classType);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DruidConnectUtils.closeConnection(conn, ps, rs);
        }
        return beanList;
    }

    public static <T> T retrieveBeanByParams(String sql, ArrayList<Object> params, Class<T> classType) {
        DruidConnectUtils druidConnectUtils = DruidConnectUtils.getInstance();
        DruidPooledConnection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        T tVo = null;
        try {
            conn = druidConnectUtils.getConnection();
            ps = conn.prepareStatement(sql);
            SetPsParams.setPsParam(params, ps);
            rs = ps.executeQuery();
            tVo = ResultSet2BeanMethods.setResultSet2Bean(rs, classType);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DruidConnectUtils.closeConnection(conn, ps, rs);
        }
        return tVo;
    }

    public static <T> T retrieveBasicTypeByParams(String sql, ArrayList<Object> params, Class<T> classType){
        DruidConnectUtils druidConnectUtils = DruidConnectUtils.getInstance();
        DruidPooledConnection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        T obj = null;

        try {
//            if(int.class.equals(classType) || Integer.class.equals(classType)) {
//                obj = classType.getDeclaredConstructor(int.class).newInstance(0);
//            }
//            else if(BigInteger.class.equals(classType)) {
//                obj = new BigInteger(0);
//            }
//            else{
//                obj = classType.getDeclaredConstructor().newInstance();
//            }
            conn = druidConnectUtils.getConnection();
            ps = conn.prepareStatement(sql);
            SetPsParams.setPsParam(params, ps);
            rs = ps.executeQuery();
            if(rs.next())
                obj = (T) rs.getObject(1);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DruidConnectUtils.closeConnection(conn, ps, rs);
        }
        return obj;
    }

}
