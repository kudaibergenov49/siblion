package ru.kkihlasovich.wl.service;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Created by Куаныш on 27.10.2016.
 */


    public class Log {
    private final static DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy, HH:mm:ss,SSS a Z");

    @XmlElement(name = "date") protected XMLGregorianCalendar date;
    @XmlElement(name = "threadName") protected String threadName;
    @XmlElement(name = "text") protected String text;


    protected XMLGregorianCalendar getTime (String line){
        Pattern pattern  = Pattern.compile("<(.*?)> <\\[severity");
        Matcher matcher = pattern.matcher(line);
        matcher.find();
        String time = matcher.group(1);
        String d = new String();
        int i = time.length()-1;
        while (time.charAt(i) != '<'){
            d = time.charAt(i) + d;
            i--;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(d));
        Date logTime = null;
        try {
            logTime = dateFormat.parse(dateFormat.format(calendar.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        GregorianCalendar gregory = new GregorianCalendar();
        gregory.setTime(logTime);
        XMLGregorianCalendar xmlGregorianCalendar = null;
        try {
            xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        return xmlGregorianCalendar;
    }

    protected String getThreadName(String line){
        Pattern threadPattern = Pattern.compile("(Thread\\-\\d|ExecuteThread\\: \\'\\d)");
        Matcher matcher = threadPattern.matcher(line);
        if (matcher.find()) {
            return matcher.group(1);
        }   else return "No find thread";
    }
}
