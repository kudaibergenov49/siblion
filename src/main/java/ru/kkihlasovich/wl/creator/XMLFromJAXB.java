package ru.kkihlasovich.wl.creator;

import ru.kkihlasovich.wl.service.Log;

import javax.faces.application.Application;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.security.acl.Group;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Куаныш on 11.11.2016.
 */
public class XMLFromJAXB {
     public File createXMLFROMJAXB(String fileName, Set<Log> set) {
         try {
             File file = new File(fileName);
             JAXBContext context = JAXBContext.newInstance(MarshallerObj.class);
             Marshaller marshaller = context.createMarshaller();
             marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
             MarshallerObj marshallerObj = new MarshallerObj();
             marshaller.marshal(marshallerObj.setLogSet(set), file);
             return file;
         } catch (JAXBException ex) {
             Logger.getLogger(XMLFromJAXB.class.getName())
                     .log(Level.SEVERE, null, ex);
         }
            return null;
    }

}
