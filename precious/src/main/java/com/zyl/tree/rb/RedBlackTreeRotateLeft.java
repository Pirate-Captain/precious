/*
 * chsi
 * Created on 2019-11-07
 */
package com.zyl.tree.rb;

/**
 * 红黑树，左旋转
 *
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class RedBlackTreeRotateLeft {
    private static TreeNode<String, String> root = null;

    public static void main(String[] args) {

    }

    public static void rotateLeft(TreeNode<String, String> node) {
        TreeNode<String, String> newParent = node.getRight();
        node.setRight(newParent.getLeft());
        if ( null != newParent.getLeft() ) {
            newParent.getLeft().setParent(node);
        }
        newParent.setParent(node.getParent());
        if ( null == node.getParent() ) {
            root = newParent;
        } else if ( node.getParent().getLeft() == node ) {
            node.getParent().setLeft(newParent);
        } else if ( node.getParent().getRight() == node ) {
            node.getParent().setRight(newParent);
        }
        newParent.setLeft(node);
        node.setParent(newParent);
    }
}