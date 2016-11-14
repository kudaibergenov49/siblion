package ru.kkihlasovich.wl.types;

import ru.kkihlasovich.wl.service.Log;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import java.util.ArrayList;

/**
 * Created by Куаныш on 10.11.2016.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class LogConvert {
    @XmlSchemaType(name = "date") private String date;
    @XmlSchemaType(name = "threadName") private String threadName;
    @XmlSchemaType(name = "text") private ArrayList<String> text;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public ArrayList<String> getText() {
        return text;
    }

    public void setText(ArrayList<String> text) {
        this.text = text;
    }

    public LogConvert getConvertLog(Log log){
       /* LogConvert logConvert = new LogConvert();
        logConvert.setDate(log.getDate().toString());
        logConvert.setThreadName(log.getThreadName());*/
        //logConvert.setText(log.getText());
        /*return logConvert;*/
        return null;
    }


}
