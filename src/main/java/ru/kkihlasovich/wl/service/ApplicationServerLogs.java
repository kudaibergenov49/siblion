package ru.kkihlasovich.wl.service;
import org.apache.log4j.LogManager;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Куаныш on 27.10.2016.
 */

public class ApplicationServerLogs {
    private final static org.apache.log4j.Logger logger = LogManager.getLogger(ApplicationServerLogs.class);

    public Set<Log> getServerLogs(String zone, String word, ArrayList<LogDates> list)
            throws TransformerException, IOException {

        TreeSet<Log> logSet = new TreeSet<>(new TreeSetLogSort());
        LogFiles logFiles = new LogFiles();
            for (LogDates logDate : list) {
                logDate.dateHandler(logDate);
                //обработчик неправильного ввода дат
                //в sortedSet записываем все логи
                if(!logDate.isDateSmaller(logDate)){
                    logSet.addAll(logFiles.returnLogFiles(zone, word, logDate.getDateTo(), logDate.getDateFrom()));
                }else {
                    logSet.addAll(logFiles.returnLogFiles(zone, word, logDate.getDateFrom(), logDate.getDateTo()));
                }
            }
        logger.info("ApplicationGetServerLogs.class success!");
        return logSet;
    }
}
