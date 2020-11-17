package com.macie.helper;

import com.macie.util.DateJsonValueProcessor;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * json数据包装类
 *
 * @author Macie
 * @date 2020/10/29 -11:20
 */
public class JsonResponseHelper {
    private JSONObject jsonObject = new JSONObject();

    /**
     * 设置返回主体数据
     *
     * @param paramName
     * @param paramData
     */
    public void setResponseData(String paramName, Object paramData) {
        // 修改Date格式
        JsonConfig config = new JsonConfig();
        config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor());
        if (paramData instanceof Map) {
            if (paramName != null) {
                JSONObject o = new JSONObject();
                o.accumulateAll((Map) paramData, config);
                jsonObject.accumulate(paramName, o);
            } else {
                jsonObject.accumulateAll((Map) paramData, config);
            }
        } else {
            jsonObject.accumulate(paramName, paramData, config);
        }
    }

    /**
     * 设置返回消息
     * @param msg
     */
    public void setResponseMessage(String msg) {
        jsonObject.accumulate("message", msg);
    }

    /**
     * 设置返回代码
     * @param code
     */
    public void setResponseCode(Integer code) {
        jsonObject.accumulate("code", code);
    }

    /**
     * 成功返回
     * @param paramName
     * @param paramData
     */
    public void setResponseOK(String paramName, Object paramData) {
        setResponseData(paramName, paramData);
        setResponseCode(ResponseCode.CODE_SUCCESS);
    }

    public void setResponseOK() {
        setResponseCode(ResponseCode.CODE_SUCCESS);
    }

    /**
     * 失败返回
     * @param paramName
     * @param paramData
     * @param msg
     */
    public void setResponseFailed(String paramName, Object paramData, String msg) {
        setResponseData(paramName, paramData);
        setResponseCode(ResponseCode.CODE_FAILED);
        setResponseMessage(msg);
    }

    public void setResponseFailed(String msg) {
        setResponseCode(ResponseCode.CODE_FAILED);
        setResponseMessage(msg);
    }

    /**
     * 把json数据转化为bean
     * @param jsonData
     * @return
     */
    public <T> T convertJson2Bean(String jsonData, Class<T> classType) {
        jsonObject = JSONObject.fromObject(jsonData);
        T tVO = null;
        String methodGetName, methodSetName;
        String paramValue;
        String fieldName;
        Class<?> fieldType;
        try {
            tVO = classType.getDeclaredConstructor().newInstance();
            for (Field field : tVO.getClass().getDeclaredFields()) {
                fieldName = field.getName();

                paramValue = jsonObject.containsKey(fieldName) ? jsonObject.getString(fieldName) : null;

                fieldType = tVO.getClass().getDeclaredField(fieldName).getType();
                //methodGetName = "get" + fieldName.replaceFirst(fieldName.substring(0,1), fieldName.substring(0,1).toUpperCase());
                //Method methodGet = currentArticleVo.getClass().getMethod(methodGetName);
                //Object fieldValue = methodGet.invoke(currentArticleVo);
                methodSetName = "set" + fieldName.replaceFirst(fieldName.substring(0, 1), fieldName.substring(0, 1).toUpperCase());
                Method methodSet = tVO.getClass().getMethod(methodSetName, fieldType);
                if (paramValue == null) {
                    continue;
                } else if (String.class.getCanonicalName().equals(fieldType.getCanonicalName())) {
                    methodSet.invoke(tVO, paramValue);
                } else if (Integer.class.getCanonicalName().equals(fieldType.getCanonicalName())) {
                    methodSet.invoke(tVO, Integer.parseInt(paramValue));
                } else if (Date.class.getCanonicalName().equals(fieldType.getCanonicalName())) {
                    methodSet.invoke(tVO, new Timestamp(Long.parseLong(paramValue)));
                } else if (Boolean.class.getCanonicalName().equals(fieldType.getCanonicalName())) {
                    methodSet.invoke(tVO, Boolean.parseBoolean(paramValue));
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return tVO;
    }

    /**
     * 把json数组转换成ArrayList
     * @param str
     * @return
     */
    public ArrayList<String> convertString2Array(String str) {
        ArrayList<String> strings = new ArrayList<>();
        JSONArray jsonArray = JSONArray.fromObject(str);
        for (Object o : jsonArray) {
            strings.add(String.valueOf(o));
        }
        return strings;
    }

    /**
     * 把json对象转换为Map对象
     * @param stringBuffer
     * @return
     */
    public HashMap<String, StringBuffer> convertString2Map(StringBuffer stringBuffer) {
        HashMap<String, StringBuffer> map = new HashMap<>();
        JSONObject jsonObj = JSONObject.fromObject(stringBuffer);
        Iterator keys = jsonObj.keys();
        while(keys.hasNext()) {
            String key = String.valueOf(keys.next());
            map.put(key, new StringBuffer(jsonObj.getString(key)));
        }
        return map;
    }

    @Override
    public String toString() {
        return jsonObject.toString();
    }
}
