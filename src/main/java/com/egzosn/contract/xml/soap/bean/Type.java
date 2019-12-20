package com.egzosn.contract.xml.soap.bean;

/**
 * soap 基础数据类型定义
 * @author egan
 *         email egzosn@gmail.com
 *         date 2018/5/17.13:44
 */
public enum Type {
    BOOLEAN,BYTE,CHAR,SHORT,INT,LONG,FLOAT,DOUBLE,NUMBER,STRING,DATE;

    /**
     * 字符串转Type
     * @param type 字符
     * @return 类型
     */
    public static final Type getType(String type){

        try {
            return Type.valueOf(type.toUpperCase());
        }catch (Exception e){

        }
        return null;
    }

    /**
     * 是否存在的类型
     * @param type 类型
     * @return true 存在，否则不存在
     */
    public static final boolean isExists(String type){
        return  null != getType(type);
    }

}
