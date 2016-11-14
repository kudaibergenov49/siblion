package ru.kkihlasovich.wl.businesslogic;
import com.itextpdf.text.DocumentException;
import ru.kkihlasovich.wl.service.Log;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.Set;

/**
 * Created by Куаныш on 11.11.2016.
 */
public class BusinessLogic {
   public Set<Log> getAllLogs(InputTypes inputTypes) throws DocumentException, TransformerException, IOException {
      return inputTypes.getServerLogs();
   }
}
