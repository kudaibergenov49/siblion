package ru.kkihlasovich.wl.types;

import com.thoughtworks.xstream.XStream;
import ru.kkihlasovich.wl.service.ApplicationServerLogs;
import ru.kkihlasovich.wl.service.Log;
import ru.kkihlasovich.wl.service.LogDates;

import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Куаныш on 10.11.2016.
 */
public class OutputLogs {
    private Set<LogConvert> setLogConvert;

    public Set<LogConvert> getSetLogConvert() {
        return setLogConvert;
    }

    public void setSetLogConvert(Set<LogConvert> setLogConvert) {
        this.setLogConvert = setLogConvert;
    }

    private Set<LogConvert> getSetConvertLog(Set<Log> logs){
        Set<LogConvert> set = new LinkedHashSet<>();
        for (Log log : logs){
            set.add(new LogConvert().getConvertLog(log));
        }
        return set;
    }

    public File getOutputLogs(String fileName, String zone, String word, ArrayList<LogDates> list)
            throws TransformerException, IOException {
        Set<Log> logSet = new ApplicationServerLogs().getServerLogs(zone, word, list);
        Set<LogConvert> setLogConvert = getSetConvertLog(logSet);
        File file = new File(fileName);
        if(!file.exists()){
            file.createNewFile();
        }

        XStream xs = new XStream();
        OutputLogs outputLogs = new OutputLogs();
        outputLogs.setLogConvert = setLogConvert;
        xs.alias("Output", OutputLogs.class);
        xs.alias("Log", LogConvert.class);

        try(FileOutputStream fs = new FileOutputStream(fileName)) {
            /*for (LogConvert logconvert: set) {
                xs.toXML(logconvert, fs);
            }*/
            xs.toXML(outputLogs, fs);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

        return file;
    }



}
