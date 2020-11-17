package com.macie.util;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.util.Date;

/**
 * @author Macie
 * @date 2021/11/16 -15:58
 */
public class DateJsonValueProcessor implements JsonValueProcessor {

    private Object process(Object o) {
        Date value = (Date) o;
        return value.getTime();
    }

    @Override
    public Object processArrayValue(Object o, JsonConfig jsonConfig) {
        if (o instanceof Date) {
            return process(o);
        }
        return null;
    }

    @Override
    public Object processObjectValue(String s, Object o, JsonConfig jsonConfig) {
        if (o instanceof Date) {
            return process(o);
        }
        return null;
    }
}
