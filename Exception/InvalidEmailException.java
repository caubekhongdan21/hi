package ex1.Exception;

public class InvalidEmailException extends  Exception{
    private String email;

    public InvalidEmailException(String message, String email) {
        super(message);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
