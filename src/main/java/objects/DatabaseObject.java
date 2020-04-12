package objects;

import exceptions.NotSavedException;
import org.bson.types.ObjectId;

public abstract class DatabaseObject {
    private ObjectId id = null;

    /**
     * Set the ID of the object in the database.
     *
     * @param id the ID
     */
    public void setId(ObjectId id) {
        this.id = id;
    }

    /**
     * Get the ID of the object in the database.
     *
     * @return the ID
     * @throws NotSavedException the object has not yet been saved to the
     *                           database, and its ID has not been set
     */
    public ObjectId getId() throws NotSavedException {
        if (id != null) {
            return id;
        } else {
            throw new NotSavedException();
        }
    }

    protected String getIdString() {
        return id.toString();
    }
}
