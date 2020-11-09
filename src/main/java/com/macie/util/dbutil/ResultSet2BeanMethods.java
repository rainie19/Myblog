package com.macie.util.dbutil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Macie
 * @date 2020/9/25 -22:39
 */
public class ResultSet2BeanMethods {
    /**
     * 将sql语句执行结果集合注入bean中
     * @param rs
     * @param classType
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> ArrayList<T> setResultSet2BeanList(ResultSet rs, Class<T> classType)
            throws Exception {
        ArrayList<T> aList = new ArrayList<>();
        ResultSetMetaData rsmd = rs.getMetaData();
        int numberOfColumns = rsmd.getColumnCount();
        while(rs.next()) {
            T tVO = classType.getDeclaredConstructor().newInstance();
            // 设置每一行的值
            setBeanValue(rs, classType, tVO, rsmd, numberOfColumns);
            aList.add(tVO);
        }
        return aList;
    }

    /**
     * 将sql语句执行结果注入bean中
     * @param rs
     * @param classType
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T setResultSet2Bean(ResultSet rs, Class<T> classType)
            throws Exception {
        T tVO = classType.getDeclaredConstructor().newInstance();
        ResultSetMetaData rsmd = rs.getMetaData();
        int numberOfColumns = rsmd.getColumnCount();
        while(rs.next()) {
            setBeanValue(rs, classType, tVO, rsmd, numberOfColumns);
        }
        return tVO;
    }

    /**
     * bean注入真正实现
     * @param rs
     * @param classType
     * @param tVO
     * @param rsmd
     * @param numberOfColumns
     * @param <T>
     * @throws SQLException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchFieldException
     */
    private static <T> void setBeanValue(ResultSet rs, Class<T> classType, T tVO, ResultSetMetaData rsmd, int numberOfColumns)
            throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        for(int i = 1; i <= numberOfColumns; i++) {
            String fieldName = rsmd.getColumnName(i).toLowerCase();
            fieldName = underline2LowerCamelCase(fieldName);
            Class<?> type = getFieldType(classType, fieldName);
            String methodName = "set" + fieldName.replaceFirst(fieldName.substring(0,1), fieldName.substring(0,1).toUpperCase());
            Method method = classType.getMethod(methodName, type);
            if(String.class.getCanonicalName().equals(type.getCanonicalName())) {
                method.invoke(tVO, rs.getString(i));
            }
            else if (Integer.class.getCanonicalName().equals(type.getCanonicalName())) {
                method.invoke(tVO, rs.getInt(i));
            }
            else if (Date.class.getCanonicalName().equals(type.getCanonicalName())) {
                method.invoke(tVO, new Date(rs.getTimestamp(i).getTime()));
            }
            else if(Boolean.class.getCanonicalName().equals(type.getCanonicalName())) {
                method.invoke(tVO, rs.getBoolean(i));
            }
        }
    }

    /**
     * @desc 根据反射查找bean中字段所对应的类型
     * @param classType
     * @param fieldName
     * @param <T>
     * @return
     * @throws NoSuchFieldException
     */
    public static <T> Class<?> getFieldType(Class<T> classType, String fieldName) throws NoSuchFieldException {
        Class<?> currentClass = classType;
        if(fieldName == null) {
            return null;
        }
        while(currentClass != null) {
            for(Field field : currentClass.getDeclaredFields()) {
                if(fieldName.equals(field.getName())) {
                    return currentClass.getDeclaredField(fieldName).getType();
                }
            }
            currentClass = currentClass.getSuperclass();
        }
        return null;
    }

    /**
     * @desc field转成bean驼峰样式
     * @param name
     * @return
     */
    public static String underline2LowerCamelCase(String name) {
        if(name == null) {
            return null;
        }
        String[] array = name.split("_");
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append(array[0]);
        String camelSubName = null;
        for(int i = 1; i < array.length; i++) {
            camelSubName = array[i].substring(0,1).toUpperCase() + array[i].substring(1);
            sBuilder.append(camelSubName);
        }
        return sBuilder.toString();
    }
}
