package ru.kkihlasovich.wl.service;
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

      protected List<Log> getFileLogs
             (String findZone,
              String word,
              XMLGregorianCalendar dateFrom, XMLGregorianCalendar dateTo) throws IOException {

          XMLGregorianCalendar date = null;
          List<Log> list = new ArrayList<Log>();
          File f = new File(findZone);
          BufferedReader fin = new BufferedReader(new FileReader(f));
          Pattern wordPattern = Pattern.compile(word);
          String line;
          String threadName = null;
          boolean haveWord = false;
          String text = null;

              while ((line = fin.readLine()) != null) {
                  if (line.contains("####")) {
                      Log log = new Log();
                      if (haveWord) {
                          log.text = text;
                          log.date = date;
                          log.threadName = threadName;
                          list.add(log);
                      }
                      date = log.getTime(line);
                      text = line.substring(5);
                      threadName = log.getThreadName(line);
                      haveWord = false;
                  }
                  if (!line.contains("####")) {
                      text = text + "\n" + line;
                  }
                  Matcher matcher = wordPattern.matcher(line);
                  if (matcher.find() && new LogDates().isDateBetween(date, dateFrom, dateTo)) haveWord = true;

              }
          fin.close();

          return list;
    }
}
