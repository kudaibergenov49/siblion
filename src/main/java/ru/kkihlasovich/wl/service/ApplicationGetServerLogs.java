package ru.kkihlasovich.wl.service;
import org.apache.log4j.LogManager;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Куаныш on 27.10.2016.
 */

public class ApplicationGetServerLogs {
    @XmlElement private final static String MIN_DATE = "00.00.1000, 01:01:01,001 AM MSK";
    @XmlElement private final static String MAX_DATE = "25.12.9999, 23:59:59,999 AM MSK";
    private final static org.apache.log4j.Logger logger = LogManager.getLogger(ApplicationGetServerLogs.class);

    public Set<Log> getServerLogs(String zone, String word, ArrayList<LogDates> list) {
        TreeSet<Log> logSet = new TreeSet<>(new TreeSetLogSort());
            for (LogDates logDate : list) {
                Log log = new Log();
                if(logDate.dateFrom == null) {
                    logDate.dateFrom = log.getXMLTime(MIN_DATE);
                    logger.info("dateFrom is MIN_DATE");
                }
                if(logDate.dateTo == null) {
                    logDate.dateTo = log.getXMLTime(MAX_DATE);
                    logger.info("dateTo is MAX_DATE");
                }
                log = null;
                //обработчик неправильного ввода дат
                //в sortedSet записываем все логи
                if(!logDate.isDateSmaller(logDate)){
                    logSet.addAll(new ReturnLogFiles().returnLogFiles(zone, word, logDate.dateTo, logDate.dateFrom));
                }else {
                    logSet.addAll(new ReturnLogFiles().returnLogFiles(zone, word, logDate.dateFrom, logDate.dateTo));
                }
            }
        logger.info("ApplicationGetServerLogs.class success!");
        return logSet;
    }
}
