package model;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.CountOptions;
import com.mongodb.client.result.InsertOneResult;
import db.Database;
import exceptions.SaveFailedError;
import objects.Artist;
import org.bson.BsonValue;
import org.bson.Document;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;

public class ArtistController {
    private static ArtistController instance;
    private final MongoCollection<Document> collection;

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

    public void save(Artist artist) {
        Document doc = new Document("name", artist.getName())
                .append("country", artist.getCountry());

        InsertOneResult result = collection.insertOne(doc);
        BsonValue insertedId = result.getInsertedId();
        if (insertedId != null) {
            ObjectId id = insertedId.asObjectId().getValue();
            artist.setId(id);
        } else {
            throw new SaveFailedError();
        }
    }

    public Artist findByName(String name) {
        Document result = collection.find(eq("name", name)).first();
        if (result == null) {
            return null;
        }
        Artist artist = new Artist(
                (String) result.get("name"),
                (String) result.get("country")
        );
        artist.setId((ObjectId) result.get("_id"));
        return artist;
    }

    boolean exists(ObjectId artistId) {
        CountOptions options = new CountOptions();
        options.limit(1);
        try {
            return (collection.countDocuments(eq("_id", artistId), options) > 0);
        } catch (IllegalArgumentException err) {
            return false;
        }
    }
}
