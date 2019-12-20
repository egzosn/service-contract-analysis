package com.egzosn.contract.xml.soap.bean;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.*;
import org.w3c.dom.Element;

import com.egzosn.contract.xml.soap.SoapElement;


/**
 *  wsdl:portType 获取每个 operation
 * @author egan
 *         email egzosn@gmail.com
 *         date 2018/5/13.20:22
 */
public class PortType  extends BaseElement{
    /**
     * 操作对应的方法接口
     */
    private Map<String, Operation> operation = new HashMap<String, Operation>();
    @Override
    public void relate(final Map<String, SoapElement> elements) {
        for (Operation o : operation.values()){
            o.relate(elements);
        }
    }
    @Override
    public SoapElement parsing(Element typeNode) {
        setName(typeNode.getAttribute(NAME));
        if (!typeNode.hasChildNodes()){
            return this;
        }
        NodeList childNodes = typeNode.getChildNodes();
        for (int i = 0; i <childNodes.getLength(); i++  ){
            //这一层是wsdl:operation
            Node node = childNodes.item(i);
            if (node.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            org.w3c.dom.Element item = (org.w3c.dom.Element) node;
            Operation operation = new Operation();
            operation.setName(item.getAttribute(NAME));
            operation.parsing(item);
            put(operation.getName(), operation);
        }
        return this;
    }


    @Override
    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Operation> getOperation() {
        return operation;
    }

    public void setOperation(Map<String, Operation> operation) {
        this.operation = operation;
    }

    public void put(String operationName, Operation operation){
        this.operation.put(operationName, operation);

    }
}
