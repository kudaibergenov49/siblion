package ru.kkihlasovich.wl.service;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Created by Куаныш on 01.11.2016.
 */
    public class LogDates {
    @XmlElement(name = "dateFrom")protected XMLGregorianCalendar dateFrom;
    @XmlElement(name = "dateTo") protected XMLGregorianCalendar dateTo;

    protected boolean isDateBetween(XMLGregorianCalendar date, XMLGregorianCalendar dateFrom, XMLGregorianCalendar dateTo){
        return (!(date.toGregorianCalendar().compareTo(dateFrom.toGregorianCalendar()) == -1))
                && (!(date.toGregorianCalendar().compareTo(dateTo.toGregorianCalendar()) == 1));
    }

    protected boolean isDateSmaller(LogDates logDate){
        return (logDate.dateFrom.toGregorianCalendar().compareTo(logDate.dateTo.toGregorianCalendar()) == -1);
    }
}
