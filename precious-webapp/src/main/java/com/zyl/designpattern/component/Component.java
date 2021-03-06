/**
 * Created on 2016-4-26
 */
package com.zyl.designpattern.component;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public abstract class Component {
    private String name;
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addChild(Component component) {
        throw new UnsupportedOperationException();
    }
    
    public void removeChild(Component component) {
        throw new UnsupportedOperationException();
    }
    
    public Component getChild(int index) {
        throw new UnsupportedOperationException();
    }
    
    abstract void printComponent();
}