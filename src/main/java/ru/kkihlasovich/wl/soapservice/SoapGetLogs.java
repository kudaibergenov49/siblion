package ru.kkihlasovich.wl.soapservice;

import ru.kkihlasovich.wl.service.ApplicationGetServerLogs;
import ru.kkihlasovich.wl.service.Log;
import ru.kkihlasovich.wl.service.LogDates;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.soap.SOAPBinding;
import javax.ws.rs.Produces;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Куаныш on 02.11.2016.
 */

@javax.jws.WebService
@Stateless
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class SoapGetLogs {
    @WebMethod
    public Set<Log> soapGetLogs(@WebParam(name = "zone") String zone,
                                @WebParam(name = "word") String word,
                                @WebParam(name = "list") ArrayList<LogDates> list) throws IOException {
        return new ApplicationGetServerLogs().getServerLogs(zone,word,list);
    }

}
