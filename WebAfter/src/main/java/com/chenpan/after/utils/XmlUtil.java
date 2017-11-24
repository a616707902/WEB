package com.chenpan.after.utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * xml 工具，对象转xml，xml转对象
 * @param <T>
 */
@SuppressWarnings({ "unchecked" })
public class XmlUtil<T> {
    /**
     * 对象转xml
     * @param req
     * @param c
     * @return
     */
    public static String toXml(Object req, Class<?> c) {  
        try {  
            StringWriter writer = new StringWriter();
            JAXBContext context = JAXBContext.newInstance(c);
            javax.xml.bind.Marshaller marshaller = context.createMarshaller();  
            marshaller.marshal(req, new StreamResult(writer));
            String result = writer.toString();  
            writer.close();  
            return result;  
        } catch (Exception e) {  
  
            throw new RuntimeException(e.getMessage(), e);  
        }  
    }

    /**
     * xml转对象
     * @param respXml
     * @param c
     * @param <T>
     * @return
     */
    public static <T> T toObj(String respXml, Class<T> c) {  
        try {  
            JAXBContext context = JAXBContext.newInstance(c);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StringReader reader = new StringReader(respXml);
            T result = (T) unmarshaller.unmarshal(new StreamSource(reader));
            reader.close();  
            return result;  
        } catch (Exception e) {  
  
            throw new RuntimeException(e.getMessage(), e);  
        }  
    }  
  
}  