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
@XStreamAlias("xml")
public class XmlBean extends ParentBean {
    @XStreamAlias("FromUserName")
    @XStreamCDATA
    protected String fromUserName;
    @XStreamAlias("CreateTime")
    protected String createTime;
    @XStreamAlias("MsgType")
    @XStreamCDATA
    protected String msgType;
    @XStreamAlias("Media")
    private ChildBean childBean;

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public ChildBean getChildBean() {
        return childBean;
    }

    public void setChildBean(ChildBean childBean) {
        this.childBean = childBean;
    }
}