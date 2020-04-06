package model;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.CountOptions;
import com.mongodb.client.result.InsertOneResult;
import db.Database;
import org.bson.BsonValue;
import org.bson.Document;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;

public class ArtistController {
    private static ArtistController instance;
    private MongoCollection<Document> collection;

    private ArtistController() {
        collection = Database
                .getInstance()
                .getDatabase()
                .getCollection("artists");
    }

    public static ArtistController getInstance() {
        if (instance == null) {
            instance = new ArtistController();
        }

        return instance;
    }

    public String create(String name, String country) {
        Document doc = new Document("name", name)
                .append("country", country);

        InsertOneResult result = collection.insertOne(doc);
        BsonValue insertedId = result.getInsertedId();
        return insertedId != null ? insertedId.asObjectId().getValue().toString() : null;
    }

    public Document findByName(String name) {
        return collection.find(eq("name", name)).first();
    }

    boolean exists(String id) {
        CountOptions options = new CountOptions();
        options.limit(1);
        try {
            return (collection.countDocuments(eq("_id", new ObjectId(id)), options) > 0);
        } catch (IllegalArgumentException err) {
            return false;
        }
    }
}
