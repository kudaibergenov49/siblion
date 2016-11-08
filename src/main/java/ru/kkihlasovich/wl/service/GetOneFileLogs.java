package ru.kkihlasovich.wl.service;
import org.apache.log4j.LogManager;

import javax.xml.datatype.XMLGregorianCalendar;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Created by Куаныш on 27.10.2016.
 */

//call GetLogTime;
 class GetOneFileLogs {
      final static private Integer LOG_LINE_START = 5;
      private final static org.apache.log4j.Logger logger = LogManager.getLogger(GetOneFileLogs.class);
      protected List<Log> getFileLogs
             (String findZone,
              String word,
              XMLGregorianCalendar dateFrom, XMLGregorianCalendar dateTo) {
          logger.info("getFileLogs run is success");

          XMLGregorianCalendar date = null;
          List<Log> list = new ArrayList<Log>();
          File f = new File(findZone);
          Pattern wordPattern = Pattern.compile(word);
          String line;
          String threadName = null;
          boolean haveWord = false;
          ArrayList<String> text = new ArrayList<>();

          try(BufferedReader fin = new BufferedReader(new FileReader(f))) {
              logger.info("read File");
              while ((line = fin.readLine()) != null) {
                  if (line.contains("####")) {
                      Log log = new Log();
                      if (haveWord) {
                          log.text = text;
                          log.date = date;
                          log.threadName = threadName;
                          list.add(log);
                      }
                      text = new ArrayList<>();
                      date = log.getTime(line);
                      text.add(line.substring(LOG_LINE_START));
                      threadName = log.getThreadName(line);
                      haveWord = false;
                  }
                  if (!line.contains("####")) {
                      text.add(line);
                  }
                  Matcher matcher = wordPattern.matcher(line);
                  if (matcher.find() && new LogDates().isDateBetween(date, dateFrom, dateTo)) haveWord = true;
              }
          } catch (FileNotFoundException e) {
              logger.error("FileNot'FoundException in getFileLogs()");
              e.printStackTrace();
          } catch (IOException e) {
              logger.error("IOException in getFileLogs()");
              e.printStackTrace();
          }
          return list;
    }
}
