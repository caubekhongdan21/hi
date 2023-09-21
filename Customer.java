package ex1;

import ex1.Exception.InvalidBirthdayException;
import ex1.Exception.InvalidEmailException;
import ex1.Exception.InvalidFullNameException;
import ex1.Exception.InvalidPhoneNumberException;

public class Customer extends Person {
    private static int idCustomer = 100000;
    private String idCus;
    private String customerType;
    private int rewardPoints;

    public Customer(String idNum, String fullName, String birthday, String email, String phoneNumber, String idCus, String customerType, int rewardPoints) throws InvalidFullNameException, InvalidEmailException, InvalidBirthdayException, InvalidPhoneNumberException {
        super(idNum, fullName, birthday, email, phoneNumber);
        setIdCus(idCus);
        this.customerType = customerType;
        this.rewardPoints = rewardPoints;
    }

    public String getIdCus() {
        return idCus;
    }

    public String getCustomerType() {
        return customerType;
    }

    public int getRewardPoints() {
        return rewardPoints;
    }

    public void setIdCus(String idCus) {
        if (idCus == null) {
            this.idCus = String.valueOf(idCustomer);
            idCustomer++;
        } else {
            this.idCus = idCus;
        }
    }


}
