package exceptions;

public class NotImplementedException extends Exception{
    public NotImplementedException() {
        System.err.println("The service you want to use not implemented yet");
    }
}
