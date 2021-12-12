package com.zmy.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import java.util.Map;

public class WebUtil {
    public  static <T> T copyToBean(Map value ,T bean){
        BeanUtils.copyProperties(value,bean);
        return bean;
    }

    /**
     * 将value1转换成Integer类型，如果转换失败，返回默认值value2
     * @param value1
     * @param value2
     * @return
     */
    public static Integer parameterInt(String value1,int value2){
        try {
            return Integer.parseInt(value1);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return value2;
    }
}
