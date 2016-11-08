package ru.kkihlasovich.wl.restservice;
import ru.kkihlasovich.wl.service.Log;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Set;
/**
 * Created by Куаныш on 02.11.2016.
 */
@Path("RestGetLogs")
public class RestGetLogs {
        @POST
        @Produces("application/json")
        @Consumes(MediaType.APPLICATION_XML)
        @Path("/restMethod")

        public Set<Log> restGetLogs(InputTypes inputTypes)  {
                return inputTypes.getServerLogs();
        }



}

