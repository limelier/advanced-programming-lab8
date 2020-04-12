package exceptions;

public class NonExistentAlbumException extends Exception {
    public NonExistentAlbumException() {
        super("Attempted to save chart referencing album not in database.");
    }
}
