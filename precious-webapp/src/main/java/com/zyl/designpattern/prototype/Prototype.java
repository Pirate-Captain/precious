/**
 * Created on 2016-4-14
 */
package com.zyl.designpattern.prototype;

/**
 * 原型模式
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class Prototype implements Cloneable {
    private String name;
    private String desc;
    private String message;

    @Override
    public Object clone() {
        Object prototype = null;
        try {
            prototype = super.clone();
        } catch ( CloneNotSupportedException e ) {
            e.printStackTrace();
        }
        return prototype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
