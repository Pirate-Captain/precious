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

public class ChildBean {
    @XStreamAlias("MediaId")
    private String mediaId;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}