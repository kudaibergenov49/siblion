package ru.kkihlasovich.wl.types;

import com.itextpdf.text.Document;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

/**
 * Created by Куаныш on 09.11.2016.
 */
     public class ConvertXMLToHTML {
      /*  public OutputStream convertXMLToHTML(Document fileXML, String fileNameHTML) throws IOException {
            String fileNameXML = fileXML.getName();
            try {
                TransformerFactory tFactory=TransformerFactory.newInstance();

                Source xslDoc=new StreamSource("C:\\Users\\Куаныш\\IdeaProjects\\GetWeblogicLogs\\src\\main\\java\\ru\\kkihlasovich\\wl\\restservice\\Sheet.xsl");
                Source xmlDoc=new StreamSource(fileNameXML);
                File outputFile = new File(fileNameHTML);
                //проверяем, что если файл не существует то создаем его
                if(!outputFile.exists()){
                    outputFile.createNewFile();
                }





                OutputStream htmlFile = new FileOutputStream(fileNameHTML);
                Transformer transform = tFactory.newTransformer(xslDoc);
                transform.transform(xmlDoc, new StreamResult(htmlFile));
                return htmlFile;
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
            catch (TransformerConfigurationException e)
            {
                e.printStackTrace();
            }
            catch (TransformerFactoryConfigurationError e)
            {
                e.printStackTrace();
            }
            catch (TransformerException e)
            {
                e.printStackTrace();
            }

            return null;
        }
*/
}
