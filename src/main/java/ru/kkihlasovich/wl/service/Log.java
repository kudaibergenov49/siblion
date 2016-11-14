package ru.kkihlasovich.wl.service;
import org.apache.log4j.LogManager;
import javax.xml.bind.annotation.*;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Created by Куаныш on 27.10.2016.
 */
    @XmlRootElement
    @XmlAccessorType(XmlAccessType.FIELD)
    public class Log {

    private final static org.apache.log4j.Logger logger = LogManager.getLogger(Log.class);
    private final static DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy, HH:mm:ss,SSS a Z");
    private final static Integer TIME_LENGTH = 13;
    @XmlElement(name = "date") private XMLGregorianCalendar date;
    @XmlElement(name = "threadName") private StringBuffer threadName;
    @XmlElement(name = "text") private StringBuffer text;


    public XMLGregorianCalendar getDate() {
        return date;
    }

    public void setDate(XMLGregorianCalendar date) {
        this.date = date;
    }

    public StringBuffer getThreadName() {
        return threadName;
    }

    public void setThreadName(StringBuffer threadName) {
        this.threadName = threadName;
    }

    public StringBuffer getText() {
        return text;
    }

    public void setText(StringBuffer text) {
        this.text = text;
    }

    protected XMLGregorianCalendar getTime (String line){
        /*String QQQQ = new String();
        StringBuffer str = new StringBuffer();
        boolean newBlock =false;
        int t = 0;
        for (int i = 0; i < line.length(); i++) {
            if(line.charAt(i) == '<'){
                newBlock = true;
                t++;
            }
            if(newBlock && line.charAt(i) != '<' && line.charAt(i) != '>'){
               if(t == 11){
                   str.append(line.charAt(i));
               }
            }
            if(line.charAt(i) == '>'){
                if(t == 11) QQQQ = str.toString();
                newBlock = false;
                str.setLength(0);
            }
        }
*/
        Pattern pattern = Pattern.compile("<([0-9]{13})>");
        Matcher matcher = pattern.matcher(line);
        matcher.find();
        String time = matcher.group(1);//QQQQ;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(time));
        Date logTime = null;
        try {
            logTime = dateFormat.parse(dateFormat.format(calendar.getTime()));
        } catch (ParseException e) {
            logger.error("getTime method is error");
            e.printStackTrace();
        }

        GregorianCalendar gregory = new GregorianCalendar();
        gregory.setTime(logTime);
        XMLGregorianCalendar xmlGregorianCalendar = null;
        try {
            xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory);
        } catch (DatatypeConfigurationException e) {
            logger.error("getXMLTimeFromDateFormat error");
            e.printStackTrace();
        }
        return xmlGregorianCalendar;
    }


}
