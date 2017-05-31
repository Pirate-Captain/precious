/**
 * chsi
 * Created on 2017-05-24
 */
package com.zyl.xml.jaxb;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.util.regex.Pattern;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class CDataXMLStreamWriter extends DelegatingXMLStreamWriter {
    private static final Pattern XML_CHARS = Pattern.compile("[&<>]");

    public CDataXMLStreamWriter(XMLStreamWriter del) {
        super(del);
    }

    @Override
    public void writeCharacters(String text) throws XMLStreamException {
        boolean useCData = XML_CHARS.matcher(text).find();
        if ( useCData ) {
            super.writeCData(text);
        } else {
            super.writeCharacters(text);
        }
    }
}