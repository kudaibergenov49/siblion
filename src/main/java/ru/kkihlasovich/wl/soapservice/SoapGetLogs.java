package ru.kkihlasovich.wl.soapservice;
import ru.kkihlasovich.wl.service.ApplicationGetServerLogs;
import ru.kkihlasovich.wl.service.Log;
import ru.kkihlasovich.wl.service.LogDates;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.soap.SOAPBinding;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
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
    public String getAsync(@WebParam(name = "str") String str){
        if(str.equals("Async")){
            return "Async";
        } else {
            return "Sync";
        }

    }
    @WebMethod
    public Set<Log> soapGetLogs(@WebParam(name = "zone") String zone,
                                @WebParam(name = "word") String word,
                                @WebParam(name = "list") ArrayList<LogDates> list) {
        return new ApplicationGetServerLogs().getServerLogs(zone,word,list);
    }

    private void Convert() throws IOException,
            URISyntaxException,
            TransformerException {
        Source xslt        = new StreamSource(new File(""));
        Source text        = new StreamSource(new File(""));
        TransformerFactory factory     = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(xslt);

        transformer.transform(text, new StreamResult(new File("")));
    }



}
