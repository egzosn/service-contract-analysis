package com.egzosn.contract.xml.soap.bean;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.egzosn.contract.xml.soap.SoapElement;


/**
 * 负责解析 wsdl:types 部分
 * @author egan
 *         email egzosn@gmail.com
 *         date 2018/5/17.13:55
 */
public class Types  extends BaseElement {
    private Map<String, Element> element = new HashMap<String, Element>();

    @Override
    public String getName() {
        return "";
    }

    @Override
    public SoapElement parsing(org.w3c.dom.Element typeNode) {
        if (!typeNode.hasChildNodes()){
            return this;
        }

        NodeList childNodes = typeNode.getChildNodes();
        for (int i = 0; i <childNodes.getLength(); i++  ){
            //这一层s:schema
            Node node = childNodes.item(i);
            if (node.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            parsingElement((org.w3c.dom.Element)node);
        }


        return this;
    }

    @Override
    public void relate(Map<String, SoapElement> elements) {

    }


    private void parsingElement(org.w3c.dom.Element typesNode){
        if (!typesNode.hasChildNodes()){
            return;
        }
        NodeList childNodes = typesNode.getChildNodes();
        for (int i = 0; i <childNodes.getLength(); i++  ){
            //这一层s:schema >s:element
            Node node = childNodes.item(i);
            if (node.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            org.w3c.dom.Element item = (org.w3c.dom.Element) node;
            Element element = new Element();
            element.setName(item.getAttribute(NAME));
            element.parsing(item);
            put(element.getName(), element);
        }
    }
    @Override
    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Element> getElement() {
        return element;
    }

    public void setElement(Map<String, Element> element) {
        this.element = element;
    }

    public void put(String elementName, Element element){
        this.element.put(elementName, element);
    }
}
