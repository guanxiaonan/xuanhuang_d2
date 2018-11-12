package com.dmx.one.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.expression.ParseException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description:
 * @Date: Create at 17:32, 2017/12/18
 * @Author: Matthew
 */
public class MyConventer implements Converter {

    @Override
    public Object convert(Object o) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            return sdf.parse((String)o);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
