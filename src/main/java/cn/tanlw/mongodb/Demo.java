package cn.tanlw.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoBulkWriteException;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * https://www.programcreek.com/java-api-examples/?class=com.mongodb.client.MongoCollection&method=insertOne
 *
 * https://stackoverflow.com/questions/23004921/how-to-check-if-element-exists-using-a-lambda-expression
 * db.getCollection('customers').createIndex({name: 1}, {unique: true, dropDups: true}) caused com.mongodb.MongoWriteException
 *
 * https://docs.mongodb.com/manual/reference/method/db.collection.createIndex/#db.collection.createIndex
 * db.getCollection('customers').getIndexes()
 *
 * Query an Array for an Element
 * https://docs.mongodb.com/manual/tutorial/query-arrays/
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
        delete(test);
        find(test);
    }

    private static void find(MongoDatabase test) {
        MongoCollection<Document> customers = test.getCollection("customers");
        FindIterable<Document> age = customers.find(Filters.gt("age", "25"));
        System.out.println("age > 25");
        age.forEach((Consumer<? super Document>) System.out::println);

        FindIterable<Document> basketball = customers.find(Filters.eq("hobbies","basketball"));
        System.out.println("hobbies contains: basketball");
        basketball.forEach((Consumer<? super Document>) System.out::println);

//        db.getCollection('customers').find({
//                hobbies: {
//            $elemMatch: {
//                $eq: "basketball"
//            }}})

    }

    private static void delete(MongoDatabase test) {
        MongoCollection<Document> customers = test.getCollection("customers");
        customers.deleteMany(Filters.eq("name","Daniel"));
    }

    private static void insert(MongoDatabase test) {

        if(!existsCollection(test)){
            test.createCollection("customers");
        }
        MongoCollection<Document> customers = test.getCollection("customers");
        customers.deleteMany(new BasicDBObject());
        Document document = new Document();
        document.put("name", "Daniel");
        document.put("age", "31");
        //org.bson.codecs.configuration.CodecConfigurationException: Can't find a codec for class [Ljava.lang.String;.
//        document.put("hobbies", "computer,basketball,movie,music".split(","));
        document.put("hobbies", Arrays.asList("computer,basketball,movie,music".split(",")));

        Document document2 = new Document();
        document2.put("name", "Jack");
        document2.put("age", "21");
        document2.put("hobbies", Arrays.asList("fishing,basketball,movie".split(",")));

        Document document3 = new Document();
        document3.put("name", "Tom");
        document3.put("age", "28");
        document3.put("hobbies", Arrays.asList("soccer,food,basketball,run".split(",")));

        Document document4 = new Document();
        document4.put("name", "Lucy");
        document4.put("age", "24");
        document4.put("hobbies", Arrays.asList("sing,food,run".split(",")));

        List documentList = new ArrayList();
        documentList.add(document2);
        documentList.add(document3);
        documentList.add(document4);
        try {
            customers.insertOne(document);
            customers.insertMany(documentList);
        } catch (MongoBulkWriteException w){
            w.printStackTrace();
        }
    }

    private static boolean existsCollection(MongoDatabase test) {
        MongoCursor<String> iterator = test.listCollectionNames().iterator();
        while (iterator.hasNext()){
            if(iterator.next().equalsIgnoreCase("customers")){
                return true;
            }
        }
        return false;
    }
}
