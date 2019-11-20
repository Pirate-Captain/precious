/*
 * chsi
 * Created on 2019-11-07
 */
package com.zyl.tree.rb;

/**
 * 红黑树，右旋转
 *
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class RedBlackTreeRotateRight {
    private static TreeNode<String, String> root = null;

    public static void rotateRight(TreeNode<String, String> node) {
        TreeNode<String, String> newParent = node.getLeft();
        node.setLeft(newParent.getRight());
        if ( null != newParent.getRight() ) {
            newParent.getRight().setParent(node);
        }
        newParent.setParent(node.getParent());
        if ( null == node.getParent() ) {
            root = newParent;
        } else if ( node.getParent().getLeft() == node ) {
            node.getParent().setLeft(newParent);
        } else if ( node.getParent().getRight() == node ) {
            node.getParent().setRight(newParent);
        }
        node.setParent(newParent);
        newParent.setRight(node);
    }
}