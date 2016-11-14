package ru.kkihlasovich.wl.types;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import ru.kkihlasovich.wl.service.ApplicationServerLogs;
import ru.kkihlasovich.wl.service.Log;
import ru.kkihlasovich.wl.service.LogDates;
import javax.xml.transform.TransformerException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;


/**
 * Created by Куаныш on 10.11.2016.
 */
public class CreatePDF {
    public Document createPDF(String fileName, String zone, String word, ArrayList<LogDates> list) throws TransformerException, IOException, DocumentException, DocumentException {
        Set<Log> setLogs = new ApplicationServerLogs().getServerLogs(zone,word,list);
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));

        document.open();

        Paragraph paragraph1 = new Paragraph();

        paragraph1.setSpacingBefore(50);




        List l = new List(true, false, 10);
        String s = "First item of listssssss";
        l.add(new ListItem(s));
        l.add(new ListItem("Second item of list"));

        paragraph1.add(l);
        document.add(paragraph1);
        document.close();
        return document;
    }
}
