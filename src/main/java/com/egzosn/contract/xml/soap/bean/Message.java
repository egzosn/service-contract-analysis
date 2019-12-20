package com.egzosn.contract.xml.soap.bean;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.egzosn.contract.xml.soap.SoapElement;


/**
 * 解析对应的消息
 *
 * @author egan
 *         email egzosn@gmail.com
 *         date 2018/5/17.13:37
 */
public class Message extends BaseElement {


    // 如果有 element存在时，以下这种，不存在时设置 parts
    private String partName;
    private String elementName;
    private Element element;


    /**
     * 如果element不存在时设置 parts
     */
    private Map<String, String> parts = new HashMap<String, String>();


    @Override
    public void relate(Map<String, SoapElement> elements) {

        if (null != elementName) {
            Types types = (Types) elements.get("types:");
            element = types.getElement().get(elementName);

        }
    }

    @Override
    public SoapElement parsing(org.w3c.dom.Element messageNode) {
        setName(messageNode.getAttribute(NAME));
        if (!messageNode.hasChildNodes()) {
            return this;
        }

        NodeList childNodes = messageNode.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node node = childNodes.item(i);
            if (node.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            org.w3c.dom.Element item = (org.w3c.dom.Element) node;
            String element = getValue(item.getAttribute("element"));
            if (null != element) {
                if (!Type.isExists(element)) {
                    setElementName(element);
                    setPartName(item.getAttribute(NAME));
                    continue;
                }
            }
            put(item.getAttribute(NAME), item.getAttribute("type"));

        }

        return this;
    }


    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }


    public Map<String, String> getParts() {
        return parts;
    }

    public void setParts(Map<String, String> parts) {
        this.parts = parts;
    }

    public void put(String part, String type) {
        parts.put(part, getValue(type));
    }

}
