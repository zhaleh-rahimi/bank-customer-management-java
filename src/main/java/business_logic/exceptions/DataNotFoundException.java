package business_logic.exceptions;

/**
 * Created by DOTIN SCHOOL 4 on 9/28/2016.
 */
public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException(String message) {
        super(message);
    }
}
