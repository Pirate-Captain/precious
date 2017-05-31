/**
 * chsi
 * Created on 2017-05-25
 */
package com.zyl.xml.xstream;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
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
