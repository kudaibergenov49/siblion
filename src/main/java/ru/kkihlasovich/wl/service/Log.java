package ru.kkihlasovich.wl.service;
import org.apache.log4j.LogManager;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Created by Куаныш on 27.10.2016.
 */


    public class Log {

    private final static org.apache.log4j.Logger logger = LogManager.getLogger(Log.class);
    private final static DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy, HH:mm:ss,SSS a Z");
    @XmlElement(name = "date") protected XMLGregorianCalendar date;
    @XmlElement(name = "threadName") protected String threadName;
    @XmlElement(name = "text") protected ArrayList<String> text;

    protected XMLGregorianCalendar getTime (String line){
        Pattern pattern = Pattern.compile("<([0-9]{11,})>");
        Matcher matcher = pattern.matcher(line);
        matcher.find();
        String time = matcher.group(1);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(time));
        Date logTime = null;
        try {
            logTime = dateFormat.parse(dateFormat.format(calendar.getTime()));
        } catch (ParseException e) {
            logger.error("getTime method is error");
            e.printStackTrace();
        }
        return getXMLTimeFromDateFormat(logTime);
    }

    protected XMLGregorianCalendar getXMLTime(String time)  {
        Date logTime = null;
        try {
            logTime = dateFormat.parse(time);
        } catch (ParseException e) {
            logger.error("getXMLTime from line error");
            e.printStackTrace();
        }
        return getXMLTimeFromDateFormat(logTime);
    }

    private  XMLGregorianCalendar getXMLTimeFromDateFormat(Date date){
        GregorianCalendar gregory = new GregorianCalendar();
        gregory.setTime(date);
        XMLGregorianCalendar xmlGregorianCalendar = null;
        try {
            xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory);
        } catch (DatatypeConfigurationException e) {
            logger.error("getXMLTimeFromDateFormat error");
            e.printStackTrace();
        }
        return xmlGregorianCalendar;
    }

    protected String getThreadName(String line){
        Pattern threadPattern = Pattern.compile("(Thread\\-\\d+|ExecuteThread\\: \\'\\d+)");
        Matcher matcher = threadPattern.matcher(line);
        if (matcher.find()) {
            return matcher.group(1);
        }   else return "No find thread";
    }
}
