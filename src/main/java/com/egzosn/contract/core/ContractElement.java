package com.egzosn.contract.core;


/**
 * 契约节点
 * @author egan
 * <br/>
 * Create time 2019/12/20 11:22
 * <br/>
 */
public interface ContractElement<T,O> {

    /**
     * 解析
     * @param node 需要解析的节点
     * @return 解析好的节点
     */
    T parsing(O node);
}
