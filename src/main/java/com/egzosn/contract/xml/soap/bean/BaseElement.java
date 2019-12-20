package com.egzosn.contract.xml.soap.bean;


import com.egzosn.contract.xml.soap.SoapElement;
import com.egzosn.contract.utils.StringUtils;

/**
 *  基础节点属性
 * @author egan
 *         email egzosn@gmail.com
 *         date 2018/5/17.19:06
 */
public abstract class BaseElement implements SoapElement {
    protected final static String NAME = "name";
    protected String name;

    @Override
    public String getName() {
        return name;
    }

    /**
     * 设置节点名称
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * 获取“:”后面的值
     *
     * @param value 转化前
     * @return 转化后
     */
    public static final String getValue(String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        return value.substring(value.indexOf(":") + 1);
    }
}
