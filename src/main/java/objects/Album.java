package objects;

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
