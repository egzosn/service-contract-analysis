package com.egzosn.contract.xml.soap;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.*;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.egzosn.contract.xml.soap.bean.BaseElement;
import com.egzosn.contract.xml.soap.bean.Service;


/**
 * SOAP xml解析
 *
 * @author egan
 * email egzosn@gmail.com
 * date 2018/5/17.14:42
 */
public class SoapUtil {


    /**
     * 获取SOAP服务
     *
     * @param content SOAP的xml内容
     * @return SOAP服务
     */
    public static final Service getService(InputStream content) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        org.w3c.dom.Document doc = documentBuilder.parse(content);
        doc.getDocumentElement().normalize();
        return getService(doc.getDocumentElement());


    }


    /**
     * 通过element获取 soap服务
     *
     * @param root soap根节点
     * @return soap服务
     */
    public static final Service getService(Element root) {
        if (!root.hasChildNodes()) {
            return null;
        }
        NodeList childNodes = root.getChildNodes();
        //用与存储所有的soap节点
        Map<String, SoapElement> soapElements = new HashMap<String, SoapElement>();
        Service service = null;
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node node = childNodes.item(i);
            if (node.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            Element item = (Element) node;
            String tagName = BaseElement.getValue(item.getTagName());
            SoapChild wsdlChild = SoapChild.valueOf(tagName);

            SoapElement element = wsdlChild.parsing(item);
            if (SoapChild.service == wsdlChild) {
                service = (Service) element;
                service.setNamespace(root.getAttribute("targetNamespace"));
            }
            soapElements.put(tagName + ":" + element.getName(), element);
        }
        //将所有的节点串连起来
        for (SoapElement element : soapElements.values()) {
            element.relate(soapElements);
        }
        return service;
    }

    /**
     * 获取SOAP服务
     *
     * @param content SOAP的xml内容
     * @return SOAP服务
     */
    public static final Service getService(String content) throws IOException, SAXException, ParserConfigurationException {
        return getService(new ByteArrayInputStream(content.getBytes(Charset.forName("UTF-8"))));
    }

    /**
     * 获取soap动作
     *
     * @param action     动作名称，方法名
     * @param parameters 方法对应的参数
     * @return soap动作字符串
     */
    public static final ByteArrayOutputStream getSoapAction(String action, String namespace, Map<String, Object> parameters) {

        try {
            // 创建消息工厂
            MessageFactory factory = MessageFactory.newInstance();
            // 根据消息工厂创建SoapMessage
            SOAPMessage message = factory.createMessage();
            // 创建SOAPPart
            SOAPPart part = message.getSOAPPart();
            // 获取SOAPEnvelope
            SOAPEnvelope envelope = part.getEnvelope();
//            envelope.setPrefix("soap");
//            envelope.removeNamespaceDeclaration("SOAP-ENV");
//            envelope.addNamespaceDeclaration("xsi", "http://www.w3.org/2001/XMLSchema-instance" );
//            envelope.addNamespaceDeclaration("xsd", "http://www.w3.org/2001/XMLSchema" );
//            envelope.removeChild(envelope.getHeader());
            // 通过SoapEnvelope可以获取到相应的Body和Header等信息
            SOAPBody body = envelope.getBody();
//            body.setPrefix("soap");
            // 根据Qname创建相应的节点,Qname是一个带有命名空间的节点
            QName qname = new QName(namespace, action);
            SOAPBodyElement ele = body.addBodyElement(qname);
            for (String key : parameters.keySet()) {
                ele.addChildElement(key).setValue(parameters.get(key).toString());
            }
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            stream.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>".getBytes());
            message.writeTo(stream);

            return stream;
        }
        catch (SOAPException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取soap Body里面内容
     *
     * @param str 字符
     * @return Body里面内容
     */
    public static final String getSoapBody(String str) {
        int start = str.indexOf("Body>");
        if (start < 10) {
            return str;
        }
        start += 11;
        int end = str.indexOf("Body>", start);
        return str.substring(start, end);
    }

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "cdaa");
        System.out.println(getSoapAction("aa", "aasd", map));

    }


}
