package com.egzosn.contract.xml.soap.bean;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.*;
import org.w3c.dom.Element;

import com.egzosn.contract.xml.soap.SoapElement;


/**
 * wsdl 服务
 * @author egan
 *         email egzosn@gmail.com
 *         date 2018/5/13.17:55
 */
public class Service  extends BaseElement {

    public static final String LINK ="binding";

    private Map<String, String> portName = new HashMap<String, String>();
    private Map<String, Binding> port = new HashMap<String, Binding>();
    private Map<String, String> address = new HashMap<String, String>();
    private String namespace = "";

    @Override
    public SoapElement parsing(Element serviceNode) {
        setName(serviceNode.getAttribute(NAME));
        if (!serviceNode.hasChildNodes()){
            return this;
        }
        NodeList childNodes = serviceNode.getChildNodes();
        for (int i = 0; i <childNodes.getLength(); i++  ){
            //这一层是wsdl:service -> wsdl:port
            Node node = childNodes.item(i);
            if (node.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            org.w3c.dom.Element item = (org.w3c.dom.Element) node;
            String name = item.getAttribute(NAME);
            put(name,  item.getAttribute(LINK));
            parsingAddress(name, item);
        }
        return this;
    }

    @Override
    public void relate(Map<String, SoapElement> elements) {
        for (String name : portName.values()){
            port.put(name,  (Binding)elements.get(LINK + ":" + name));
        }
    }

    public Map<String, String> getPortName() {
        return portName;
    }

    public void setPortName(Map<String, String> portName) {
        this.portName = portName;
    }

    public Map<String, Binding> getPort() {
        return port;
    }

    public void setPort(Map<String, Binding> port) {
        this.port = port;
    }

    @Override
    public String getName() {
        return name;
    }



    /**
     * 解析存放地址
     * @param name portNodeName
     * @param portNode portNode
     */
   private void parsingAddress( String name, Element portNode) {
    if (!portNode.hasChildNodes()){
        return;
    }
       NodeList childNodes = portNode.getChildNodes();
       for (int i = 0; i <childNodes.getLength(); i++  ) {
           Node node = childNodes.item(i);
           if (node.getNodeType() != Node.ELEMENT_NODE) {
               continue;
           }
           org.w3c.dom.Element item = (org.w3c.dom.Element) node;
           address.put(name, item.getAttribute("location"));
       }
    }
    public Map<String, String> getAddress() {
        return address;
    }

    public void setAddress(Map<String, String> address) {
        this.address = address;
    }

    public void put(String portName, String binding){
        this.portName.put(portName, getValue(binding));
    }
    public void put(String portName, Binding binding){
        this.port.put(portName, binding);
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }
}
