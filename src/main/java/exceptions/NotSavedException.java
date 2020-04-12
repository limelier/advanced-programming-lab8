package exceptions;

public class NotSavedException extends Exception {
    public NotSavedException() {
        super("Attempted to get ID of object that was not saved to the database.");
    }
}
