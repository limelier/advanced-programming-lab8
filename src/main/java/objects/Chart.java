package objects;

import org.bson.types.ObjectId;

import java.util.List;

public class Chart {
    private final String name;
    private final List<ObjectId> albumIds;

    public Chart(String name, List<ObjectId> albumIds) {
        this.name = name;
        this.albumIds = albumIds;
    }

    public String getName() {
        return name;
    }

    public List<ObjectId> getAlbumIds() {
        return albumIds;
    }
}
