package ru.kkihlasovich.wl.service;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Куаныш on 27.10.2016.
 */

//call getLogFiles (GetFiles class)
   class ReturnLogFiles {
     //call GetFiles
     protected List<Log> returnLogFiles(String zone, String word, XMLGregorianCalendar dateFrom, XMLGregorianCalendar dateTo) throws IOException {

        List<Log> enterZoneArray = new ArrayList<Log>();
        String DOMAIN_PATH = System.getProperty("user.dir");
        if (zone.contains("domain")) {
            List<String> serverPaths = getServersPaths(DOMAIN_PATH + "/servers");
            for(String serverPath : serverPaths){
                enterZoneArray.addAll(getLogFiles(serverPath + "/logs", word, dateFrom, dateTo));
            }
        } else if(zone.equals("new_Cluster_1")) {
            enterZoneArray.addAll(getLogFiles(DOMAIN_PATH + "/servers/Server-1/logs",
                    word, dateFrom, dateTo));
            enterZoneArray.addAll(getLogFiles(DOMAIN_PATH + "/servers/Server-2/logs",
                    word, dateFrom, dateTo));
        }
        else if(zone.contains("Server")){
            enterZoneArray.addAll(getLogFiles(DOMAIN_PATH + "/servers/" + zone + "/logs", word, dateFrom, dateTo));
        }
        return enterZoneArray;
    }

    private List<Log> getLogFiles(String zone, String word, XMLGregorianCalendar dateFrom, XMLGregorianCalendar dateTo) throws IOException {
        File dir = new File(zone);
        List<Log> zoneLogs = new ArrayList<Log>();
        for (File item : dir.listFiles()) {
            if (item.getName().contains(".log") && item.getName().contains("Server")) {
                zoneLogs.addAll(new GetOneFileLogs().getFileLogs(item.getAbsolutePath(), word, dateFrom, dateTo));
            }
        }
        return zoneLogs;
    }

    private List<String> getServersPaths(String zone){
        List<String> serverNames = new ArrayList<String>();
        File dir  = new File(zone);
        for (File item : dir.listFiles()){
            if(item.getName().contains("Server")) {
                serverNames.add(item.getAbsolutePath());
            }
        }
        return serverNames;
    }

}
