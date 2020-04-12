package objects;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.List;

public class Chart extends DatabaseObject {
    private final String name;
    private final List<ObjectId> albumIds;

    public Chart(String name, List<ObjectId> albumIds) {
        this.name = name;
        this.albumIds = albumIds;
    }

    /**
     * Make a Chart object from a database document. Uses unsafe casts - do not supply invalid documents.
     * @param document the chart document
     */
    public Chart(Document document) {
        // turning off warning for unsafe List<ObjectId> cast
        //noinspection unchecked
        this(
                (String) document.get("name"),
                (List<ObjectId>) document.get("albums")
        );
        setId((ObjectId) document.get("_id"));
    }

    public String getName() {
        return name;
    }

    public List<ObjectId> getAlbumIds() {
        return albumIds;
    }

    @Override
    public String toString() {
        return "Chart{" +
                "id='" + getIdString() + '\'' +
                ", name='" + name + '\'' +
                ", albumIds=" + albumIds +
                '}';
    }
}
