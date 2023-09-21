package ex1;

import ex1.Exception.InvalidBirthdayException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class Item {
    private static int idItems = 10000;
    private String idItem;
    private String nameItem;
    private String typeItem;
    private long originalSellingPrice;
    private String manufacturer;
    private Date dateOfManufacture;
    private Date expiry;

    public Item(String idItem, String nameItem, String typeItem, long originalSellingPrice,
                String manufacturer, String dateOfManufacture, String expiry) throws InvalidBirthdayException {
        this.idItem = idItem;
        this.nameItem = nameItem;
        this.typeItem = typeItem;
        this.originalSellingPrice = originalSellingPrice;
        this.manufacturer = manufacturer;
         setDateOfManufacture(dateOfManufacture);
        setExpiry(expiry);
    }

    public String getIdItem() {
        return idItem;
    }

    public String getNameItem() {
        return nameItem;
    }

    public String getTypeItem() {
        return typeItem;
    }

    public long getOriginalSellingPrice() {
        return originalSellingPrice;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public Date getDateOfManufacture() {
        return dateOfManufacture;
    }

    public Date getExpiry() {
        return expiry;
    }

    public void setIdItem(String idItem) {
        if (idItem == null) {
            this.idItem = String.valueOf(idItems);
            idItems++;
        } else {
            this.idItem = idItem;
        }
    }

    public static void setIdItems(int idItems) {
        Item.idItems = idItems;
    }

    public void setDateOfManufacture(String dateOfManufacture) throws InvalidBirthdayException {
        var regex = "^\\d{4}-\\d{2}-\\d{2}$";
        var check = checkException(regex, dateOfManufacture);
        if (check) {
            var simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                this.dateOfManufacture = simpleDateFormat.parse(dateOfManufacture);
            } catch (ParseException e) {
                System.out.println(e.getMessage());
            }
        } else {
            throw new InvalidBirthdayException("InvalidDateOfManufacture", dateOfManufacture);
        }
    }

    public void setExpiry(String expiry) throws InvalidBirthdayException {
        var regex = "^\\d{4}-\\d{2}-\\d{2}$";
        var check = checkException(regex, expiry);
        if (check) {
            var simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                this.expiry = simpleDateFormat.parse(expiry);
            } catch (ParseException e) {
                System.out.println(e.getMessage());
            }
        } else {
            throw new InvalidBirthdayException("InvalidDateOfManufacture", expiry);
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
}
