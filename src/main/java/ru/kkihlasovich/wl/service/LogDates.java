package ru.kkihlasovich.wl.service;
import org.apache.log4j.LogManager;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Куаныш on 01.11.2016.
 */
    @XmlRootElement
    @XmlAccessorType
    public class LogDates {
    private final static org.apache.log4j.Logger logger = LogManager.getLogger(LogDates.class);
    private final static DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy, HH:mm:ss,SSS a Z");
    @XmlElement(name = "dateFrom")private XMLGregorianCalendar dateFrom;
    @XmlElement(name = "dateTo") private XMLGregorianCalendar dateTo;

    protected LogDates dateHandler(LogDates logDates){
        String MIN_DATE = "00.00.1000, 01:01:01,001 AM MSK";
        String MAX_DATE = "25.12.9999, 23:59:59,999 AM MSK";
        if(dateFrom == null) {
            setDateFrom(getXMLTime(MIN_DATE));
        }
        if(dateTo == null) {
            setDateTo(getXMLTime(MAX_DATE));
        }
        return this;
    }

    private XMLGregorianCalendar getXMLTime(String time)  {
        Date logTime = null;
        try {
            logTime = dateFormat.parse(time);
        } catch (ParseException e) {
            logger.error("getXMLTime from line error");
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

    public XMLGregorianCalendar getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(XMLGregorianCalendar dateFrom) {
        this.dateFrom = dateFrom;
    }

    public XMLGregorianCalendar getDateTo() {
        return dateTo;
    }

    public void setDateTo(XMLGregorianCalendar dateTo) {
        this.dateTo = dateTo;
    }

    protected boolean isDateBetween(XMLGregorianCalendar date, XMLGregorianCalendar dateFrom, XMLGregorianCalendar dateTo){
        return (!(date.toGregorianCalendar().compareTo(dateFrom.toGregorianCalendar()) == -1))
                && (!(date.toGregorianCalendar().compareTo(dateTo.toGregorianCalendar()) == 1));
    }

    protected boolean isDateSmaller(LogDates logDate){
        return (logDate.dateFrom.toGregorianCalendar().compareTo(logDate.dateTo.toGregorianCalendar()) == -1);
    }
}
