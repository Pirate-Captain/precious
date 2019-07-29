/**
 * Created on 2016-4-14
 */
package com.zyl.designpattern.prototype;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class PrototypeBean implements Cloneable {
    private String name;
    private String info;

    @Override
    protected Object clone()  {
        Object beanClone = null;
        try {
            beanClone = super.clone();
        } catch ( CloneNotSupportedException e ) {
            e.printStackTrace();
        }
        return beanClone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}