package persistence;
public class ConnectionNotFoundException extends  Exception {
    String message;
    public ConnectionNotFoundException(String message)
    {
        this.message=message;
    }
}
