package ma.inpt.esj.Exception;

public class ProfessionnelException extends Exception{
    public ProfessionnelException(String message) {
        super(message);
    }

    public ProfessionnelException(String message, Throwable cause) {
        super(message, cause);
    }
}
