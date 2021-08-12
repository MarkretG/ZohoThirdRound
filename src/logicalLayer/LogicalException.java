package logicalLayer;
public class LogicalException extends Exception{
    String message;
    public LogicalException(String message)
    {
        this.message=message;
    }
}
