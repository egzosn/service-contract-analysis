package com.egzosn.contract.xml.soap;

import org.w3c.dom.Element;

import com.egzosn.contract.xml.soap.bean.Binding;
import com.egzosn.contract.xml.soap.bean.Message;
import com.egzosn.contract.xml.soap.bean.PortType;
import com.egzosn.contract.xml.soap.bean.Service;
import com.egzosn.contract.xml.soap.bean.Types;


/**
 * Soap 节点
 * @author egan
 *         email egzosn@gmail.com
 *         date 2018/5/17.14:54
 */
public enum SoapChild {

    types {
       @Override
        public SoapElement parsing(Element node) {
            SoapElement parsing = new Types().parsing(node);
            return parsing;
        }
    }, message {
        @Override
        public SoapElement parsing(Element node) {
            return new Message().parsing(node);
        }
    }, portType {
        @Override
        public SoapElement parsing(Element node) {
            return new PortType().parsing(node);
        }
    }, binding {
        @Override
        public SoapElement parsing(Element node) {
            return new Binding().parsing(node);
        }
    }, service {
        @Override
        public SoapElement parsing(Element node) {
            return new Service().parsing(node);
        }
    };
    public abstract SoapElement parsing(Element node);

}
