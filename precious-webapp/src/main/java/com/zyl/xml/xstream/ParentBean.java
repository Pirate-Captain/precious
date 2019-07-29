/**
 * chsi
 * Created on 2017-05-25
 */
package com.zyl.xml.xstream;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class ParentBean {
    @XStreamAlias("ToUserName")
    @XStreamCDATA
    protected String toUserName;

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }
}
