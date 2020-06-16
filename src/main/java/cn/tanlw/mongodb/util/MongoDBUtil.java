package cn.tanlw.mongodb.util;

import com.mongodb.MongoClient;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import org.bson.BsonDocument;
import org.bson.conversions.Bson;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriterSettings;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

public class MongoDBUtil {
    public static String toJsonString(List<Bson> bsons){
        if(CollectionUtils.isEmpty(bsons)){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bsons.size(); i++) {
            sb.append(bsons.get(i)
                    .toBsonDocument(BsonDocument.class, MongoClient.getDefaultCodecRegistry())
                    .toJson(new JsonWriterSettings(JsonMode.SHELL)));
            sb.append("\r\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        List<Bson> age = Arrays.asList(Aggregates.match(Filters.and(Filters.eq("age", "24"))));
        System.out.println(toJsonString(age));
    }
}
