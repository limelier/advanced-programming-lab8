package objects;

import exceptions.NotSavedException;
import org.bson.types.ObjectId;

public class Artist extends DatabaseObject {
    private final String name;
    private final String country;

    public Artist(String name, String country) {
        this.name = name;
        this.country = country;
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
