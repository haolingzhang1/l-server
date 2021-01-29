package com.intelasers.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.text.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * JSON工具类
 *
 * @author zhanghaoling
 * @date 2020-12-03
 */
@Component(JsonUtils.BEAN_NAME)
public class JsonUtils {
    static final String BEAN_NAME = "util.JsonUtils";
    private static ObjectMapper objectMapper;

//    static {
//        objectMapper = new ObjectMapper();
//        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES,true);
//        objectMapper.setDateFormat(new JacksonDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));
//    }

//    @Autowired
//    public void setObjectMapper(ObjectMapper objectMapper) {
//        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES,true);
//        JacksonDateFormat jacksonDateFormat = new JacksonDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
//        objectMapper.setDateFormat(jacksonDateFormat);
//        objectMapper.registerModule(new JavaTimeModule());//支持localdate
//        JsonUtils.objectMapper = objectMapper;
//    }



        /**
         * 对象转字符串
         *
         * @param obj java对象
         * @return json字符串
         */
    public static String toJsonString(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 字符串转对象
     *
     * @param json  json字符串
     * @param clazz class类型
     * @param <T>   对象泛型
     * @return java对象
     */
    public static <T> T toJavaBean(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 字符串转对象
     *
     * @param json json字符串
     * @param type JavaType类型
     * @param <T>  对象泛型
     * @return java对象
     */
    public static <T> T toJavaBean(String json, JavaType type) {
        try {
            return objectMapper.readValue(json, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 字符串转对象
     *
     * @param json json字符串
     * @param type TypeReference类型
     * @param <T>  对象泛型
     * @return java对象
     */
    public static <T> T toJavaBean(String json, TypeReference<T> type) {
        try {
            return objectMapper.readValue(json, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 字符串转集合对象
     *
     * @param json  json字符串
     * @param clazz class类型
     * @param <T>   对象泛型
     * @return java对象
     */
    public static <T> List<T> toCollectionBeans(String json, Class<T> clazz) {
        CollectionType collectionType = objectMapper.getTypeFactory()
                .constructCollectionType(List.class, clazz);
        try {
            return objectMapper.readValue(json, collectionType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Map toMap(Object obj) {
        try {
            String json = toJsonString(obj);
            return toJavaBean(json, Map.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T toBean(Object map, Class<T> clazz) {
        try {
            String json = toJsonString(map);
            return toJavaBean(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * jackson只支持以下时间格式，
     * 自定义DateFormat支持yyy-MM-dd HH:mm:ss等格式
     * "yyyy-MM-dd'T'HH:mm:ss.SSSZ"；
     * "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"；
     * "yyyy-MM-dd";
     * "EEE, dd MMM yyyy HH:mm:ss zzz";
     * long类型的时间戳
     */
    private static class MyDateFormat extends DateFormat {
        private static final long serialVersionUID = -5389018060102244978L;
        private final DateFormat dateFormat;
        private String[] patterns = new String[]{"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm"};

        MyDateFormat(DateFormat dateFormat) {
            this.dateFormat = dateFormat;
        }

        @Override
        public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
            return dateFormat.format(date, toAppendTo, fieldPosition);
        }

        @Override
        public Date parse(String source, ParsePosition pos) {
            Date date;
            try {
                date = myParse(source, pos);
            } catch (Exception e) {
                date = dateFormat.parse(source, pos);
            }
            return date;
        }

        @Override
        public Date parse(String source) throws ParseException {
            ParsePosition pos = new ParsePosition(0);
            Date result = parse(source, pos);
            if (pos.getIndex() == 0) {
                throw new ParseException("failure parse date: \"" + source + "\"",
                        pos.getErrorIndex());
            }
            return result;
        }

        private Date myParse(String source, ParsePosition pos) {
            try {
                Date rtn;
                SimpleDateFormat dateFormat;
                for (String pattern : patterns) {
                    dateFormat = new SimpleDateFormat(pattern);
                    rtn = dateFormat.parse(source, pos);
                    if (rtn != null) {
                        return rtn;
                    }
                }
                throw new RuntimeException("date format error: " + source);
            } catch (Exception e) {
                throw new RuntimeException("date format error: " + source, e);
            }
        }

        @SuppressWarnings("MethodDoesntCallSuperMethod")
        @Override
        public Object clone() {
            Object format = dateFormat.clone();
            return new MyDateFormat((DateFormat) format);
        }
    }

}
