package cn.tanlw.mongodb;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AggregateDemo {
    @Test
    public void aggregateDemo(){

        MongoClient mongoClient = new MongoClient("mongodb", 17017);
        MongoDatabase test = mongoClient.getDatabase("test");
        ArrayList<Document> customers = 
                test.getCollection("customers").aggregate(buildPipleLine()).into(new ArrayList<>());
        customers.stream().forEach(System.out::println);
        Gson gson = new Gson();
        Employee employee = gson.fromJson(customers.get(0).toJson(), Employee.class);
        System.out.println(employee.getName()+" age:"+employee.getAge());
    }

    private List<? extends Bson> buildPipleLine() {
        return Arrays.asList(Aggregates.match(Filters.and(Filters.eq("age","24"))));
    }
}
