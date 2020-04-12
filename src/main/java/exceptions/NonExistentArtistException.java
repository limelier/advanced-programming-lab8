package exceptions;

public class NonExistentArtistException extends Exception {
    public NonExistentArtistException() {
        super("Attempted to save album referencing artist not in database.");
    }
}
