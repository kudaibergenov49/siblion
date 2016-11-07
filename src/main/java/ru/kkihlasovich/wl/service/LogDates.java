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
        return !(date.toGregorianCalendar().compareTo(dateFrom.toGregorianCalendar()) == -1)
                && !(date.toGregorianCalendar().compareTo(dateTo.toGregorianCalendar()) == 1);
    }

    private boolean isDateSmaller(LogDates logDates){
        return (logDates.dateFrom.toGregorianCalendar().compareTo(logDates.dateTo.toGregorianCalendar()) == -1);
    }

    protected void changeDates(LogDates logDates){
        if(isDateSmaller(logDates)) {
            XMLGregorianCalendar tmpDate = logDates.dateFrom;
            logDates.dateFrom = logDates.dateTo;
            logDates.dateTo = tmpDate;
        }
    }
}
