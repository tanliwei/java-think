package cn.tanlw.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.function.Consumer;

/**
 * https://www.programcreek.com/java-api-examples/?class=com.mongodb.client.MongoCollection&method=insertOne
 * @Creator Tan Liwei
 * @Date 2019/6/24 22:59
 */
public class Demo {
    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient("mongodb", 17017);
        System.out.println("Database:");
        mongoClient.listDatabaseNames().forEach((Consumer<? super String>) System.out::println);

        System.out.println("Collections:");
        MongoDatabase test = mongoClient.getDatabase("test");
        test.listCollectionNames().forEach((Consumer<? super String>) System.out::println);

        insert(test);


    }

    private static void insert(MongoDatabase test) {
        test.createCollection("customers");
        MongoCollection<Document> customers = test.getCollection("customers");
        Document document = new Document();
        document.put("name", "Daniel");
        document.put("wife", "Meirong");
        customers.insertOne(document);

    }
}
