package persistence;
public class PersistenceException extends Exception {
    String message;
    public PersistenceException(String message)
    {
        this.message=message;
    }
}
