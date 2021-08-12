package persistence;
public class SQLRelatedException extends Exception {
    String message;
    public SQLRelatedException(String message)
    {
       this.message=message;
    }
}
