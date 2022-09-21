package com.example;

import com.example.Logging.LogReader;
import com.example.MongoDB.ConnectToDB;
/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        ConnectToDB.connectToMongo();
    }
}
