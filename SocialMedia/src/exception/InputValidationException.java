package exception;

public class InputValidationException extends RuntimeException{
    public InputValidationException(String msg)
    {
        super(msg);
    }
}
