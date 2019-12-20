package com.egzosn.contract.xml.soap;

import java.util.Map;

import org.w3c.dom.Element;

import com.egzosn.contract.core.ContractElement;

/**
 * soap 节点
 * @author egan
 *         email egzosn@gmail.com
 *         date 2018/5/17.14:58
 */
public interface SoapElement extends ContractElement<SoapElement, Element> {
    /**
     *  获取节点名称
     * @return 节点名称
     */
    String getName();
    /**
     * 关联自身内部对象，需要对应的内部对象
     * @param elements  所有的属性节点
     */
    void relate(Map<String, SoapElement> elements);


}
