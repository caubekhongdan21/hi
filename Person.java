package ex1;

import ex1.Exception.InvalidBirthdayException;
import ex1.Exception.InvalidEmailException;
import ex1.Exception.InvalidFullNameException;
import ex1.Exception.InvalidPhoneNumberException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class Person {
    private String idNum;
    private FullName fullName;
    private Date birthday;
    private String email;
    private String phoneNumber;

    public Person(String idNum, String fullName, String birthday, String email, String phoneNumber)
            throws InvalidFullNameException, InvalidEmailException, InvalidBirthdayException, InvalidPhoneNumberException {
        this.idNum = idNum;
        setFullName(fullName);
        setEmail(email);
        setBirthday(birthday);
        setPhoneNumber(phoneNumber);
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public void setFullName(String fullName) throws InvalidFullNameException {
        var regex = "^[A-Za-z0-9]{9,13}$";
        var check = checkException(regex, fullName);
        if (check) {
            this.fullName = new FullName();
            var infoName = fullName.split("\\s+");
            this.fullName.firstName = infoName[infoName.length - 1];
            this.fullName.lastName = infoName[0];
            var test = "";
            for (int i = 1; i < infoName.length - 1; i++) {
                if (i + 1 != infoName.length - 1) {
                    test += infoName[i] + " ";
                } else {
                    test += infoName[i];
                }
            }
            this.fullName.midName = test;
        } else {
            throw new InvalidFullNameException("InvalidFullName", fullName);
        }
    }

    private boolean checkException(String regex, String test) {
        var pattern = Pattern.compile(regex);
        var matcher = pattern.matcher(test);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    public void setBirthday(String birthday) throws InvalidBirthdayException {
        var regex = "^\\d{4}-\\d{2}-\\d{2}$";
        var check = checkException(regex, birthday);
        if (check) {
            var simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                this.birthday = simpleDateFormat.parse(birthday);
            } catch (ParseException e) {
                System.out.println(e.getMessage());
            }
        } else {
            throw new InvalidBirthdayException("InvalidBirthday", birthday);
        }
    }

    public void setEmail(String email) throws InvalidEmailException {
        var regex = "^[a-zA-Z][a-zA-Z0-9._]*@gmail\\.com$";
        var check = checkException(regex, email);
        if (check) {
            this.email = email;
        } else {
            throw new InvalidEmailException("InvalidEmail", email);
        }
    }

    public void setPhoneNumber(String phoneNumber) throws InvalidPhoneNumberException {
        var regex = "^(08|09|03|04|07)\\d{8}$";
        var check = checkException(regex, phoneNumber);
        if (check) {
            this.phoneNumber = phoneNumber;
        } else {
            throw new InvalidPhoneNumberException("InvalidPhoneNumber", phoneNumber);
        }
    }

    public String getIdNum() {
        return idNum;
    }

    public FullName getFullName() {
        return fullName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    class FullName {
        private String firstName;
        private String midName;
        private String lastName;

        public FullName() {
            this.firstName = "";
            this.midName = "";
            this.lastName = "";
        }

        public String getFirstName() {
            return firstName;
        }

        public String getMidName() {
            return midName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getFullName() {
            return firstName + " " + midName + " " + lastName;
        }
    }
}
