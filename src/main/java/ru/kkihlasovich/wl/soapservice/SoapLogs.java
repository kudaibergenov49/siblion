package ru.kkihlasovich.wl.soapservice;
import com.itextpdf.text.DocumentException;
import ru.kkihlasovich.wl.businesslogic.BusinessLogic;
import ru.kkihlasovich.wl.businesslogic.InputTypes;
import ru.kkihlasovich.wl.service.ApplicationServerLogs;
import ru.kkihlasovich.wl.service.Log;
import ru.kkihlasovich.wl.service.LogDates;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.soap.SOAPBinding;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Куаныш on 02.11.2016.
 */

@javax.jws.WebService
@Stateless
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class SoapLogs {

    @WebMethod
    public Set<Log> soapGetLogs(@WebParam(name = "InputTypes") InputTypes inputTypes) throws TransformerException, IOException, DocumentException {

        return new BusinessLogic().getAllLogs(inputTypes);
    }

//    private void Convert() throws IOException,
//            URISyntaxException,
//            TransformerException {
//        Source xslt        = new StreamSource(new File(""));
//        Source text        = new StreamSource(new File(""));
//        TransformerFactory factory     = TransformerFactory.newInstance();
//        Transformer transformer = factory.newTransformer(xslt);
//
//        transformer.transform(text, new StreamResult(new File("")));
//    }
//


}
