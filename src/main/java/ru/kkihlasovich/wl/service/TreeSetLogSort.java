package ru.kkihlasovich.wl.service;

import java.util.Comparator;

/**
 * Created by Куаныш on 07.11.2016.
 */
    class TreeSetLogSort implements Comparator<Log> {
    public int compare(Log o1, Log o2) {
        return o1.date.toGregorianCalendar().compareTo(o2.date.toGregorianCalendar());
    }
}