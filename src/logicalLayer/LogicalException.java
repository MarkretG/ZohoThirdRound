package logicalLayer;
public class LogicalException extends Exception{
    int errorCode;
    String message;
    public LogicalException(String message,int errorCode)
    {
        this.message=message;
        this.errorCode=errorCode;
    }
}
