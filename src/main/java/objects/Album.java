package objects;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Album extends DatabaseObject {
    private final ObjectId artistId;
    private final String name;
    private final int releaseYear;

    public Album(ObjectId artistId, String name, int releaseYear) {
        this.artistId = artistId;
        this.name = name;
        this.releaseYear = releaseYear;
    }

    /**
     * Make an Album object from a database document. Uses unsafe casts - do not supply invalid documents.
     * @param document the album document
     */
    public Album(Document document) {
        this(
                (ObjectId) document.get("artist_id"),
                (String) document.get("name"),
                (Integer) document.get("release_year")
        );
        setId((ObjectId) document.get("_id"));
    }

    public ObjectId getArtistId() {
        return artistId;
    }

    public String getName() {
        return name;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id='" + getIdString() + '\'' +
                ", artistId=" + artistId +
                ", name='" + name + '\'' +
                ", releaseYear=" + releaseYear +
                '}';
    }
}
