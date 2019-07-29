/**
 * chsi
 * Created on 2017-04-24
 */
package com.zyl.datastructure.linkedlist.singlenode;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class SingleLinkedList {
    private Node headerNode;//头节点
    private int nodeSize;//链表的长度

    public SingleLinkedList() {
        headerNode = new Node();
    }

    /**
     * 添加新的节点
     * @param data
     */
    public void appendNode(Object data) {
        Node lastNode = headerNode;
        for ( int i=0; i < nodeSize; i++ ) {
            lastNode = lastNode.getNextNode();
        }
        Node newNode = new Node();
        newNode.setDataNode(data);
        lastNode.setNextNode(newNode);
        nodeSize++;
    }

    public void deleteNode(Object data) {
        if ( 0 == nodeSize ) {
            return;
        }
        Node currentNode = headerNode.getNextNode();
        Node previous = headerNode;
        while ( null != currentNode ) {
            if ( data == currentNode.getDataNode() ) {
                previous.setNextNode(currentNode.getNextNode());
                nodeSize--;
            } else {
                previous = currentNode;
            }
            currentNode = currentNode.getNextNode();
        }
    }

    public Node getHeaderNode() {
        return headerNode;
    }

    public void setHeaderNode(Node headerNode) {
        this.headerNode = headerNode;
    }

    public int getNodeSize() {
        return nodeSize;
    }

    public void setNodeSize(int nodeSize) {
        this.nodeSize = nodeSize;
    }
}