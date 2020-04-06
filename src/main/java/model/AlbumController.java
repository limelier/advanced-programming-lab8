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

public class AlbumController {
    private static AlbumController instance;
    private MongoCollection<Document> collection;

    private AlbumController() {
        collection = Database
                .getInstance()
                .getDatabase()
                .getCollection("albums");
    }

    public static AlbumController getInstance() {
        if (instance == null) {
            instance = new AlbumController();
        }

        return instance;
    }

    public String create(String name, String artistId, int releaseYear) {
        if (!ArtistController.getInstance().exists(artistId)) {
            throw new IllegalArgumentException("non-existent artist"); // todo: custom exception
        }

        Document doc = new Document("name", name)
                .append("artist_id", new ObjectId(artistId))
                .append("release_year", releaseYear);

        InsertOneResult result = collection.insertOne(doc);
        BsonValue insertedId = result.getInsertedId();
        return insertedId != null ? insertedId.asObjectId().getValue().toString() : null;
    }

    public List<Document> findByArtist(String artistId) {
        FindIterable<Document> results = collection.find(eq("artist_id", new ObjectId(artistId)));

        List<Document> resultList = new ArrayList<>();
        for (Document result : results) {
            resultList.add(result);
        }
        return resultList;
    }
}
