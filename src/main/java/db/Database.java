package db;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;

import com.mongodb.client.MongoDatabase;

public class Database {
    private static Database instance;

    private MongoClient client;
    private MongoDatabase database;

    private Database() {
        client = MongoClients.create("mongodb://dba:sql@localhost:27017");
        database = client.getDatabase("lab8");
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }

        return instance;
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public void close() {
        client.close();
    }
}
