package exceptions;

public class SaveFailedError extends Error {
    public SaveFailedError() {
        super("Saving an object failed due to a database problem.");
    }
}
