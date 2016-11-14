package ru.kkihlasovich.wl.businesslogic;

import com.itextpdf.text.DocumentException;
import ru.kkihlasovich.wl.businesslogic.BusinessLogic;
import ru.kkihlasovich.wl.creator.XMLFromJAXB;
import ru.kkihlasovich.wl.service.ApplicationServerLogs;
import ru.kkihlasovich.wl.service.Log;
import ru.kkihlasovich.wl.service.LogDates;
import javax.xml.bind.annotation.*;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;


/**
 * Created by Куаныш on 03.11.2016.
 */
@XmlRootElement(name = "InputTypes")
@XmlAccessorType(XmlAccessType.FIELD)
    public class InputTypes {
    @XmlElement(name = "zone") private String zone;
    @XmlElement(name = "word") private String word;
    @XmlElement(name = "list") private ArrayList<LogDates> list;


     protected Set<Log> getServerLogs() throws IOException, TransformerException, DocumentException {
         //return new CreatePDF().createPDF(fileName,zone,word,list);
         //return new OutputLogs().getOutputLogs(fileName,zone,word,list);
         //return new ApplicationGetServerLogs().getServerLogs(zone,word,list);
         //return new CreateXML().createXML(fileName, zone, word, list);
         //return new XMLtoXML().XMLtoXML(fileName, zone, word, list);
         //return new BusinessLogic().getAllLogs(zone,word,list);
         //return new XMLFromJAXB().createXMLFROMJAXB(fileName,set);
         return new ApplicationServerLogs().getServerLogs(zone,word,list);
     }
}
