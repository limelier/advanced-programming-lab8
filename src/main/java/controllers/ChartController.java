package controllers;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;
import db.Database;
import exceptions.NonExistentAlbumException;
import exceptions.SaveFailedError;
import objects.Album;
import objects.Chart;
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

    public void save(Chart chart) throws NonExistentAlbumException {
        AlbumController albums = AlbumController.getInstance();
        for (ObjectId albumId : chart.getAlbumIds()) {
            if (!albums.exists(albumId)) {
                throw new NonExistentAlbumException();
            }
        }

        Document doc = new Document("name", chart.getName())
                .append("albums", chart.getAlbumIds());

        InsertOneResult result = collection.insertOne(doc);
        BsonValue insertedId = result.getInsertedId();
        if (insertedId != null) {
            ObjectId id = insertedId.asObjectId().getValue();
            chart.setId(id);
        } else {
            throw new SaveFailedError();
        }
    }

    public List<Album> getAlbums(Chart chart) {
        AlbumController albumController = AlbumController.getInstance();
        List<Album> albumList = new ArrayList<>();
        for (ObjectId albumId : chart.getAlbumIds()) {
            albumList.add(albumController.findById(albumId));
        }
        return albumList;
    }

    public Chart getByName(String name) {
        Document result = collection.find(eq("name", name)).first();
        if (result == null) {
            return null;
        }
        return new Chart(result);
    }
}
