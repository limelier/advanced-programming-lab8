package controllers;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.CountOptions;
import com.mongodb.client.result.InsertOneResult;
import db.Database;
import exceptions.NonExistentArtistException;
import exceptions.SaveFailedError;
import objects.Album;
import objects.Artist;
import org.bson.BsonValue;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class AlbumController {
    private static AlbumController instance;
    private final MongoCollection<Document> collection;

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

    public void save(Album album) throws NonExistentArtistException {
        if (!ArtistController.getInstance().exists(album.getArtistId())) {
            throw new NonExistentArtistException();
        }

        Document doc = new Document("name", album.getName())
                .append("artist_id", album.getArtistId())
                .append("release_year", album.getReleaseYear());

        InsertOneResult result = collection.insertOne(doc);
        BsonValue insertedId = result.getInsertedId();
        if (insertedId != null) {
            ObjectId id = insertedId.asObjectId().getValue();
            album.setId(id);
        } else {
            throw new SaveFailedError();
        }
    }

    public List<Album> findByArtist(ObjectId artistId) {
        FindIterable<Document> results = collection.find(eq("artist_id", artistId));

        List<Album> resultList = new ArrayList<>();
        for (Document result : results) {
            Album album = new Album(result);
            album.setId((ObjectId) result.get("_id"));
            resultList.add(album);
        }
        return resultList;
    }

    boolean exists(ObjectId albumId) {
        CountOptions options = new CountOptions();
        options.limit(1);
        try {
            return (collection.countDocuments(eq("_id", albumId), options) > 0);
        } catch (IllegalArgumentException err) {
            return false;
        }
    }

    public Album findById(ObjectId albumId) {
        Document result = collection.find(eq("_id", albumId)).first();
        if (result == null) {
            return null;
        }
        return new Album(result);
    }
}
