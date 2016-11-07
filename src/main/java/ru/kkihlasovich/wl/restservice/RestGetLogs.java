package ru.kkihlasovich.wl.restservice;
import ru.kkihlasovich.wl.service.Log;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
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

        public Set<Log> restGetLogs(InputTypes inputTypes) throws IOException {
             return inputTypes.getServerLogs();
        }
}

