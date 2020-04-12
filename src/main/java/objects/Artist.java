package objects;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Artist extends DatabaseObject {
    private final String name;
    private final String country;

    public Artist(String name, String country) {
        this.name = name;
        this.country = country;
    }

    /**
     * Make an Artist object from a database document. Uses unsafe casts - do not supply invalid documents.
     * @param document the artist document
     */
    public Artist(Document document) {
        this(
                (String) document.get("name"),
                (String) document.get("country")
        );
        setId((ObjectId) document.get("_id"));
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id='" + getIdString() + '\'' +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
