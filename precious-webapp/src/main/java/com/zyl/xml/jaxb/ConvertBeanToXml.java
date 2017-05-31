package com.zyl.xml.jaxb;

import java.io.ByteArrayOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class ConvertBeanToXml {
    public static void main(String[] args) {
        Xmlbean bean = new Xmlbean();
        bean.setName("Abcde");
        bean.setValue("<a href=\"www.chsi.com.cn\">wangzhi</a>");
        
        JAXBContext context;
        try {
            context = JAXBContext.newInstance(bean.getClass());
            ByteArrayOutputStream op = new ByteArrayOutputStream();

            XMLOutputFactory xof = XMLOutputFactory.newInstance();
            XMLStreamWriter streamWriter = xof.createXMLStreamWriter(op);
            CDataXMLStreamWriter cdataStreamWriter = new CDataXMLStreamWriter(streamWriter);

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            
            marshaller.marshal(bean, cdataStreamWriter);
            cdataStreamWriter.flush();
            cdataStreamWriter.close();
            String result = op.toString();
            System.out.println(result);
        } catch ( JAXBException e ) {
        } catch ( XMLStreamException e ) {
            e.printStackTrace();
        }
    }
}