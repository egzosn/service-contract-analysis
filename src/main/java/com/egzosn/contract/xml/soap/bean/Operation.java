package com.egzosn.contract.xml.soap.bean;

import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.egzosn.contract.xml.soap.SoapElement;
import com.egzosn.contract.utils.StringUtils;


/**
 * 每个操作方法的结构
 *
 * @author egan
 *         email egzosn@gmail.com
 *         date 2018/5/13.20:23
 */
public class Operation extends BaseElement {

    /**
     * 输入消息
     */
    public Message input;
    /**
     * 输入消息名称
     */
    public String inputName;
    /**
     * 输出消息
     */
    public Message output;
    /**
     * 输出消息名称
     */
    public String outputName;
    /**
     * 描述
     */
    public String documentation;

    public static final String LINK = "message";


    @Override
    public SoapElement parsing(Element operationNode) {
        setName(operationNode.getAttribute(NAME));
        if (!operationNode.hasChildNodes()) {
            return this;
        }
        NodeList childNodes = operationNode.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            //这一层是wsdl:operation
            Node node = childNodes.item(i);
            if (node.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            org.w3c.dom.Element item = (org.w3c.dom.Element) node;
            if ("wsdl:documentation".equals(item.getTagName())) {
                documentation = item.getTextContent();
            }
            if ("wsdl:input".equals(item.getTagName())) {
                setInputName(item.getAttribute("message"));
            } else if ("wsdl:output".equals(item.getTagName())) {
                setOutputName(item.getAttribute("message"));
            }
        }


        return this;
    }

    @Override
    public void relate(Map<String, SoapElement> elements) {
        if (!StringUtils.isEmpty(inputName)) {
            input = (Message) elements.get(LINK + ":" + inputName);
        }
        if (!StringUtils.isEmpty(outputName)) {
            output = (Message) elements.get(LINK + ":" + outputName);
        }
    }
    @Override
    public void setName(String name) {
        this.name = name;
    }

    public Message getInput() {
        return input;
    }

    public void setInput(Message input) {
        this.input = input;
    }

    public String getInputName() {
        return inputName;
    }

    public void setInputName(String inputName) {
        this.inputName = getValue(inputName);
    }

    public Message getOutput() {
        return output;
    }

    public void setOutput(Message output) {
        this.output = output;
    }

    public String getOutputName() {
        return outputName;
    }

    public void setOutputName(String outputName) {
        this.outputName = getValue(outputName);
    }

    public String getDocumentation() {
        return documentation;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }
}
