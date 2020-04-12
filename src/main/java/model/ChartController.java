package model;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;
import db.Database;
import org.bson.BsonValue;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class ChartController {
    private static ChartController instance;
    private final MongoCollection<Document> collection;

    private ChartController() {
        collection = Database
                .getInstance()
                .getDatabase()
                .getCollection("charts");
    }

    public static ChartController getInstance() {
        if (instance == null) {
            instance = new ChartController();
        }

        return instance;
    }

//    public String create(List<String> albumIds) {
//        if (!ArtistController.getInstance().exists(artistId)) {
//            throw new IllegalArgumentException("non-existent artist"); // todo: custom exception
//        }
//
//        Document doc = new Document("name", name)
//                .append("artist_id", new ObjectId(artistId))
//                .append("release_year", releaseYear);
//
//        InsertOneResult result = collection.insertOne(doc);
//        BsonValue insertedId = result.getInsertedId();
//        return insertedId != null ? insertedId.asObjectId().getValue().toString() : null;
//    }
}
