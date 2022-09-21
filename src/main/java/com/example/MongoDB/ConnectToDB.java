package com.example.MongoDB;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ConnectToDB {
    private static MongoDatabase database;
    private static final String USER_NAME = "sampleUser";
    private static final String DATABASE_NAME = "myDb";
    private static final String PASSWORD = "password";

    public static void connectToMongo() {

        // Creating a Mongo client
        MongoClient mongo = new MongoClient("localhost", 27017);
        //Creating Credentials
        final MongoCredential credential = MongoCredential.createCredential(USER_NAME, DATABASE_NAME, PASSWORD.toCharArray());
        System.out.println("Connected to the database successfully");
        // Accessing the database
        database = mongo.getDatabase(DATABASE_NAME);
    }


    /*public static MongoCollection<Document> getCollection(String collectionName) {
        return database.getCollection(collectionName);
    }*/

    public static Document createDocument(Map<String, Object> data) {
        return new Document(data);
    }

    public static void insertOneData(String collectionName, Document document) {
        database.getCollection(collectionName).insertOne(document);
    }

    public static void insertManyData(String collectionName, List<Document> documentList) {
        database.getCollection(collectionName).insertMany(documentList);
    }

    private static void updateDocument(String collectionName) {
        System.out.println("********************************************************************\n" + "Before Updating");
        FindIterable<Document> iterDoc = database.getCollection(collectionName).find();
        int i = 1;
        // Getting the iterator
        for (Document document : iterDoc) {
            System.out.println(document);
            i++;
        }
        database.getCollection(collectionName).updateOne(Filters.all("Author", "Sahand"), Updates.set("co-Author", "Again Sahand"));
        System.out.println("********************************************************************\n" + "After Updating");
        iterDoc = database.getCollection(collectionName).find();
        i = 1;
        // Getting the iterator
        for (Document document : iterDoc) {
            System.out.println(document);
            i++;
        }
    }

    public static List<Document> getLogsByType(String collectionName, String type) {
        List<Document> docs = new ArrayList<>();
        // Getting the iterable object
        FindIterable<Document> iterDoc = database.getCollection(collectionName).find(Filters.eq("level", type));
        int i = 1;
        // Getting the iterator
        for (Document document : iterDoc) {
            docs.add(document);
            i++;
        }
        return docs;
    }

    public static List<Document> getLogsByNanos(String collectionName, String nanos) {
        List<Document> docs = new ArrayList<>();
        // Getting the iterable object
        FindIterable<Document> iterDoc = database.getCollection(collectionName).find(Filters.eq("nanos", nanos));
        int i = 1;
        // Getting the iterator
        for (Document document : iterDoc) {
            docs.add(document);
            i++;
        }
        return docs;
    }

    public static List<Document> getLogsByField(String collectionName, String field, String value) {
        List<Document> docs = new ArrayList<>();
        // Getting the iterable object
        FindIterable<Document> iterDoc = database.getCollection(collectionName).find(Filters.eq(field, value));
        int i = 1;
        // Getting the iterator
        for (Document document : iterDoc) {
            docs.add(document);
            i++;
        }
        return docs;
    }

    public static void createIndex(String collectionName, String fieldName, int order) {
        if (order == 1) {
            database.getCollection(collectionName).createIndex(Indexes.ascending(fieldName));
        } else {
            database.getCollection(collectionName).createIndex(Indexes.descending(fieldName));
        }
    }
}