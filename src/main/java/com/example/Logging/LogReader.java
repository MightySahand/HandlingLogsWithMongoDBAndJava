package com.example.Logging;

import com.example.Log;
import com.example.MongoDB.ConnectToDB;
import org.bson.Document;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class LogReader {
    private static StringBuilder stringBuilder = new StringBuilder();
    private static String COLLECTION_NAME = "logs";
    private static List<Log> logs = new ArrayList<>();

    public static void ReadFile() {
        try {
            FileInputStream fstream = new FileInputStream("C:\\Users\\Sahand Farshian\\Desktop\\Logs\\new.log");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            /* read log line by line */
            while ((strLine = br.readLine()) != null) {
                addLogToRawLogData(strLine);
            }
            fstream.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void persistData() {
        List<Document> docs = new ArrayList<>();
        logs.parallelStream().forEach(log -> docs.add(Document.parse(String.valueOf(new JSONObject(log)))));
        ConnectToDB.insertManyData(COLLECTION_NAME, docs);
    }

    public static void addLogToRawLogData(String s) {
        if (s.equals("<log>")
                | s.equals("<!DOCTYPE log SYSTEM \"logger.dtd\">")
                | s.equals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>")
                | s.equals("</log>")
                | s.equals("<record>")
                | s.equals(">")
        ) {
            return;
        } else if (s.equals("</record>")) {
//            rawLogData.add(stringBuilder.toString());
            rawDataToLogObject(stringBuilder.toString());
            stringBuilder.delete(0, stringBuilder.length());
        } else {
            stringBuilder.append(s);
        }
    }

    public static void rawDataToLogObject(String rawData) {
        List<String> data = parseRawData(rawData);
        logs.add(new Log(data.get(0), data.get(1), data.get(2), data.get(3), data.get(4), data.get(5), data.get(6), data.get(7), data.get(8), data.get(9)));
    }

    private static List<String> parseRawData(String rawData) {
//        System.out.println("raw Data " + rawData);
        List<String> lines = Arrays.asList(rawData.trim().split("\\s+"));
//        System.out.println("Lines *******" + lines.get(0).toString());
        lines = lines.stream().map(
                line -> line.substring(line.indexOf('>') + 1, line.indexOf("</"))
        ).collect(Collectors.toList());
        return lines;
    }

    public static String printLogObjects() {
        return logs.toString();
    }

    public static List<Document> getLogsByType(String type) {
        return ConnectToDB.getLogsByType(COLLECTION_NAME, type);
    }
    public static List<Document> getLogsByNanos(String nanos) {
        return ConnectToDB.getLogsByNanos(COLLECTION_NAME, nanos);
    }
    public static List<Document> getLogsByField(String field, String value) {
        return ConnectToDB.getLogsByField(COLLECTION_NAME, field, value);
    }
    public static void createIndex(String fieldName, int order){
        // -1 -> descending ,   1 -> Ascending
        ConnectToDB.createIndex(COLLECTION_NAME, fieldName, order);
    }
}
