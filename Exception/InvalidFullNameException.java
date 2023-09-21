package ex1.Exception;

public class InvalidFullNameException extends  Exception{
    private String fullName;

    public InvalidFullNameException(String message, String fullName) {
        super(message);
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }
}
