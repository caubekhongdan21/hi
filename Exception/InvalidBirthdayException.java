package ex1.Exception;

public class InvalidBirthdayException extends  Exception{
    private String birthday;

    public InvalidBirthdayException(String message, String birthday) {
        super(message);
        this.birthday = birthday;
    }

    public String getBirthday() {
        return birthday;
    }
}
