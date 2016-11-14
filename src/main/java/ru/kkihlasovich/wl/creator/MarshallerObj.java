package ru.kkihlasovich.wl.creator;

import ru.kkihlasovich.wl.service.Log;

import java.util.Set;

/**
 * Created by Куаныш on 11.11.2016.
 */
public class MarshallerObj {
    Set<Log> set;

    public Set<Log> getSet() {
        return set;
    }

    public void setSet(Set<Log> set) {
        this.set = set;
    }

    public MarshallerObj setLogSet(Set<Log> set){
        this.setSet(set);
        return this;
    }
}
