package ru.kkihlasovich.wl.restservice;

import com.itextpdf.text.DocumentException;
import ru.kkihlasovich.wl.businesslogic.BusinessLogic;
import ru.kkihlasovich.wl.businesslogic.InputTypes;
import ru.kkihlasovich.wl.creator.XMLFromJAXB;
import ru.kkihlasovich.wl.service.Log;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.util.Set;

/**
 * Created by Куаныш on 02.11.2016.
 */
@Path("RestGetLogs")
public class RestLogs {
        @POST
        @Produces("application/json")
        @Consumes(MediaType.APPLICATION_XML)
        @Path("/restMethod")
        public File restGetLogs(InputTypes inputTypes) throws IOException, TransformerException, DocumentException {
                //return new ConvertXMLToHTML().convertXMLToHTML(inputTypes.getServerLogs("File.xml"), "File.html");
                Set<Log> set = new BusinessLogic().getAllLogs(inputTypes);
                return new XMLFromJAXB().createXMLFROMJAXB("Sync.xml",set);
        }

        @POST
        @Produces("application/json")
        @Consumes(MediaType.APPLICATION_XML)
        @Path("/restMethod/document/123/data")
        public File restAsyncGetLogs(InputTypes inputTypes) throws IOException, TransformerException, DocumentException {
                Set<Log> set = new BusinessLogic().getAllLogs(inputTypes);
                return new XMLFromJAXB().createXMLFROMJAXB("Async.xml",set);
        }



}

