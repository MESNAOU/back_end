package ma.inpt.esj.Exception;

public class MedecinNotFoundException extends Exception{
    public MedecinNotFoundException(String message) {
        super(message);
    }

    public MedecinNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
