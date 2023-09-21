package ex1.Exception;

public class InvalidPhoneNumberException extends  Exception {
    private String phoneNumber;

    public InvalidPhoneNumberException(String message,String phoneNumber) {
        super(message);
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
