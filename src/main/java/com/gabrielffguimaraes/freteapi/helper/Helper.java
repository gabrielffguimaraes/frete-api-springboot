package com.gabrielffguimaraes.freteapi.helper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

public class Helper {

    public static <T> Object convertXMLToObject(Class<T> c, String xml) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(c);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        Object response =  unmarshaller.unmarshal(new StringReader(xml));
        return response;
    }
}
