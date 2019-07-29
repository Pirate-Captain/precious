/**
 * Created on 2016-4-26
 */
package com.zyl.designpattern.component;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class TestComponentPattern {
    public static void main(String[] args) {
        Component root = new Composite();
        root.setName("水果");
        
        Component c1 = new Composite();
        c1.setName("夏天的水果");
        
        Component c1Child1 = new Leaf();
        c1Child1.setName("西瓜");
        
        Component c1Child2 = new Leaf();
        c1Child2.setName("香瓜");
        
        Component c2 = new Composite();
        c2.setName("春天的水果");
        
        Component c2Child1 = new Leaf();
        c2Child1.setName("草莓");
        
        root.addChild(c1);
        root.addChild(c2);
        
        c1.addChild(c1Child1);
        c1.addChild(c1Child2);
        
        c2.addChild(c2Child1);
        
        root.printComponent();
    }
}