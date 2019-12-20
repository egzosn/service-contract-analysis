package com.egzosn.contract.xml.soap.bean;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.egzosn.contract.xml.soap.SoapElement;


/**
 * 解析<s:element>--》s:complexType--》s:element  部分
 *
 * @author egan
 *         email egzosn@gmail.com
 *         date 2018/5/17.13:41
 */
public class Element extends BaseElement {
    private Map<String, String> element = new HashMap<String, String>();


    @Override
    public void relate(Map<String, SoapElement> elements) {

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public SoapElement parsing(org.w3c.dom.Element elementNode) {

        if (!elementNode.hasChildNodes()) {
            return this;
        }
        NodeList childNodes = elementNode.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            //这一层s:schema
            Node node = childNodes.item(i);
            if (node.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            org.w3c.dom.Element item = (org.w3c.dom.Element) node;
            if (item.getTagName().contains(":element")) {
                put(item.getAttribute(NAME), item.getAttribute("type"));
            } else {
                parsing(item);
            }
        }

        return this;
    }


    @Override
    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getElement() {
        return element;
    }

    public void setElement(Map<String, String> element) {
        this.element = element;
    }

    public void put(String elementName, String type) {
        element.put(elementName, getValue(type));
    }
}
