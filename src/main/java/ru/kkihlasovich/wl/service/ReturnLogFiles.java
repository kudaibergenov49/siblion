package ru.kkihlasovich.wl.service;
import org.apache.log4j.LogManager;

import javax.xml.datatype.XMLGregorianCalendar;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Куаныш on 27.10.2016.
 */

//call getLogFiles (GetFiles class)
   class ReturnLogFiles {
     //call GetFiles
     final static private String DOMAIN_PATH = System.getProperty("user.dir");
     private final static org.apache.log4j.Logger logger = LogManager.getLogger(ReturnLogFiles.class);
     protected List<Log> returnLogFiles(String zone, String word, XMLGregorianCalendar dateFrom,
                                        XMLGregorianCalendar dateTo) {
        List<Log> enterZoneArray = new ArrayList<Log>();
        if (zone.contains("domain")) {
            List<String> serverPaths = getServersPaths(DOMAIN_PATH + "/servers");
            for(String serverPath : serverPaths){
                enterZoneArray.addAll(getLogFiles(serverPath + "/logs", word, dateFrom, dateTo));
            }
        } else if(zone.contains("Cluster")) {
            List<String> serverNames = getClusterServerNames(zone);
            for (String serverName: serverNames) {
                enterZoneArray.addAll(getLogFiles(DOMAIN_PATH + "/servers/" + serverName + "/logs", word, dateFrom, dateTo));
            }
        }
        else if(zone.contains("Server")){
            enterZoneArray.addAll(getLogFiles(DOMAIN_PATH + "/servers/" + zone + "/logs", word, dateFrom, dateTo));
        }
        logger.info("success returnLogFiles in ReturnLogFiles");
        return enterZoneArray;
    }

     private List<Log> getLogFiles(String zone, String word, XMLGregorianCalendar dateFrom, XMLGregorianCalendar dateTo) {
        File dir = new File(zone);
        List<Log> zoneLogs = new ArrayList<Log>();
        for (File item : dir.listFiles())
            if (isLogFile(item)) {
                zoneLogs.addAll(new GetOneFileLogs().getFileLogs(item.getAbsolutePath(), word, dateFrom, dateTo));
            }
        return zoneLogs;
    }

     private boolean isLogFile(File item){
         return item.getName().contains(".log") && (item.getName().contains("Server") || item.getName().contains("domain"));
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

     private  List<String> getClusterServerNames(String clusterName)  {
        String filePath = DOMAIN_PATH + "\\config\\config.xml";
        File f = new File(filePath);
        HashMap<String, String> map = new HashMap<String, String>();
        try(BufferedReader fin = new BufferedReader(new FileReader(f))) {
            String line;
            boolean startBlock = false;
            Pattern patternServer = Pattern.compile("<name>(.*?)</name>");
            Pattern patternCluster = Pattern.compile("<cluster>(.*?)</cluster>");
            String key = null;
            String value;
            while ((line = fin.readLine()) != null) {
                if (line.contains("<server>")) {
                    startBlock = true;
                }
                if (startBlock) {
                    Matcher matcherServer = patternServer.matcher(line);
                    Matcher matcherCluster = patternCluster.matcher(line);
                    if (matcherServer.find()) key = matcherServer.group(1);
                    if (matcherCluster.find()) {
                        value = matcherCluster.group(1);
                        map.put(key, value);
                        startBlock = false;
                    }
                }
                if (line.equals("</server>")) {
                    startBlock = false;
                }
            }
        } catch (FileNotFoundException e) {
            logger.error("FileNotFoundException in getClusterServerNames");
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("IOException in getClusterServerNames");
            e.printStackTrace();
        }

        return getClusterServers(clusterName,map);
    }

     private List<String> getClusterServers(String value, HashMap<String,String> map){
        List<String> serverNames = new ArrayList<String>();
        for (Map.Entry<String,String> entry : map.entrySet()) {
            if(entry.getValue().equals(value)) {
                serverNames.add(entry.getKey());
            }
        }
        return serverNames;
    }

}
