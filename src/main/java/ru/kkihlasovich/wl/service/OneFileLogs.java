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
 class OneFileLogs {
      final static private Integer LOG_LINE_START = 5;
      private final static org.apache.log4j.Logger logger = LogManager.getLogger(OneFileLogs.class);
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
          StringBuffer threadName = new StringBuffer();
          boolean haveWord = false;
          StringBuffer text = new StringBuffer();
          LogDates logDates = new LogDates();
          Log log = new Log();

          try(BufferedReader fin = new BufferedReader(new FileReader(f))) {
              logger.info("read File");
              while ((line = fin.readLine()) != null) {
                  if (line.contains("####")) {
                      if (haveWord) {
                          log.setText(text);
                          log.setDate(date);
                          log.setThreadName(threadName);
                          list.add(log);
                      }
                      text.setLength(0);
                      threadName.setLength(0);
                      date = log.getTime(line);
                      text.append(line.substring(LOG_LINE_START));
                      threadName.append(getThreadName(line));
                      haveWord = false;
                  }
                  if (!line.contains("####")) {
                      text.append(line);
                  }
                  Matcher matcher = wordPattern.matcher(line);
                  if (matcher.find() && logDates.isDateBetween(date, dateFrom, dateTo)) {
                      haveWord = true;
                  }
              }
          } catch (FileNotFoundException e) {
              logger.error("FileNotFoundException in getFileLogs()");
              e.printStackTrace();
          } catch (IOException e) {
              logger.error("IOException in getFileLogs()");
              e.printStackTrace();
          }
          return list;
    }

    private String getThreadName(String line){
        Pattern threadPattern = Pattern.compile("(Thread\\-\\d+|ExecuteThread\\: \\'\\d+)");
        Matcher matcher = threadPattern.matcher(line);
        if (matcher.find()) {
            return matcher.group(1);
        }   else return "No find thread";
    }
}
