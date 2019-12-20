package com.egzosn.contract.xml.soap.bean;

import java.util.Map;

import com.egzosn.contract.xml.soap.SoapElement;


/**
 * wsdl:binding   服务绑定 binding 只需解析头节点定位 type即可
 *
 * @author egan
 *         email egzosn@gmail.com
 *         date 2018/5/13.17:56
 */
public class Binding extends BaseElement {

    private PortType type;
    private String typeName;

    public static final String LINK = "portType";

    /**
     * 关联自身内部对象，需要对应的内部对象
     * @param elements  所有的属性节点
     */
    @Override
    public void relate(Map<String, SoapElement> elements) {
        type = (PortType) elements.get(LINK + ":" + typeName);
        type.relate(elements);
    }

    @Override
    public SoapElement parsing(org.w3c.dom.Element node) {
        setName(node.getAttribute(NAME));
        setTypeName(node.getAttribute("type"));
        return this;
    }

    public PortType getType() {
        return type;
    }

    public void setType(PortType type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = getValue(typeName);
    }

}
