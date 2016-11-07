package ru.kkihlasovich.wl.service;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * Created by Куаныш on 27.10.2016.
 */

public class ApplicationGetServerLogs {
    @XmlElement private final static String MIN_DATE = "00.00.1000, 01:01:01,001 AM MSK";
    @XmlElement private final static String MAX_DATE = "25.12.9999, 23:59:59,999 AM MSK";
    public Set<Log> getServerLogs(String zone, String word, ArrayList<LogDates> list) throws IOException {

        Set<Log> logSet = new TreeSet<Log>(new Comparator<Log>() {
            public int compare(Log o1, Log o2) {return o1.date.toGregorianCalendar().compareTo(o2.date.toGregorianCalendar());}});


            for (LogDates logdate : list) {
            if(logdate.dateFrom == null) {
                logdate.dateFrom = getXMLTime(MIN_DATE);
            }
            if(logdate.dateTo == null) {
                logdate.dateTo = getXMLTime(MAX_DATE);
            }
                //обработчик неправильного ввода дат
                //logdates.changeDates(logdates);
                //в sortedSet записываем все логи
                logSet.addAll(new ReturnLogFiles().returnLogFiles(zone, word, logdate.dateFrom, logdate.dateTo));
            }
        return logSet;
    }

    private static XMLGregorianCalendar getXMLTime(String time)  {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy, HH:mm:ss,SSS a Z");
        Date logTime = null;
        try {
            logTime = dateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        GregorianCalendar gregory = new GregorianCalendar();
        gregory.setTime(logTime);
        XMLGregorianCalendar calendar = null;
        try {
            calendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        return calendar;
    }

}
