/**
 * chsi
 * Created on 2017-04-24
 */
package com.zyl.datastructure.linkedlist.singlenode;

/**
 * 节点信息
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class Node {
    private Object dataNode;//节点数据
    private Node nextNode;//下一个节点

    public Object getDataNode() {
        return dataNode;
    }

    public void setDataNode(Object dataNode) {
        this.dataNode = dataNode;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }
}