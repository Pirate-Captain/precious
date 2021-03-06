/**
 * chsi
 * Created on 2017-04-24
 */
package com.zyl.datastructure.linkedlist.singlenode;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class SingleLinkedListTest {
    public static void main(String[] args) {
        SingleLinkedList linkedList = new SingleLinkedList();
        linkedList.appendNode(10);
        linkedList.appendNode(20);
        linkedList.appendNode(30);
        linkedList.appendNode(20);
        linkedList.appendNode(40);
        linkedList.appendNode(20);

        System.out.println(linkedList.getNodeSize());

        linkedList.deleteNode(20);
        System.out.println(linkedList.getNodeSize());
    }
}