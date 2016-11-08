package ru.kkihlasovich.wl.restservice;

import ru.kkihlasovich.wl.service.ApplicationGetServerLogs;
import ru.kkihlasovich.wl.service.Log;
import ru.kkihlasovich.wl.service.LogDates;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Куаныш on 03.11.2016.
 */
@XmlRootElement(name = "InputTypes")
@XmlAccessorType(XmlAccessType.FIELD)
    class InputTypes {
    @XmlElement private String zone;
    @XmlElement private String word;
    @XmlElement private ArrayList<LogDates> list;

     protected Set<Log> getServerLogs() {
        return new ApplicationGetServerLogs().getServerLogs(this.zone,this.word,this.list);
    }
}
