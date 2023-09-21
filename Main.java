package ex1;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import ex1.Exception.InvalidBirthdayException;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {

    private static final String USER = "sa";
    private static final String PASS = "123";
    private static final String SERVER_NAME = "21AK22-COM\\SQLEXPRESS";
    private static final String DATABASE_NAME = "quanlybanhangtrongsieuthi";
    private static final int PORT = 1433;


    public static void main(String[] args) {
        ArrayList<Item> items = readItemFromSQLServer();
        var input = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n\n\n\t\t MENU");
            System.out.println("1 : Add Item IN SQL");
            System.out.println("2 : Update INFORMATION Item IN SQL");
            System.out.println("3 : Delete Item in sql");
            System.out.println("4 : Show Item");
            System.out.print("choice : ");
            choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1:
                    int result = insertItemToSql(input);
                    if (result > 0) {
                        System.out.println("Thực hiện thành công lệnh truy vấn. " +
                                result + " hàng đã được thêm vào trong bảng CSDL.");
                    } else {
                        System.out.println("Thực hiện câu lệnh truy vấn thất bại!");
                    }
                    break;
                case 2:
                    System.out.print("idItem : ");
                    var idItem = input.nextLine();
                    System.out.print("Manufactures : ");
                    var manufactures = input.nextLine();
                    int result1 = updateDataUsingStatement(idItem, manufactures);
                    if (result1 > 0) {
                        System.out.println("Cập nhật Item thành công!");
                        var item = getItemById(idItem);
                        showAItem(item);
                    } else {
                        System.out.println("Cập nhật Item thất bại!");
                    }
                    break;
                case 3:
                    break;
                case 4:
                    if (items.size() > 0) {
                        showItems(items);
                    } else {
                        System.out.println("False");
                    }
                    break;
            }

        } while (choice != 0);

    }

    private static Item getItemById(String idItem) {
        return null;
    }

    private static int updateDataUsingStatement(String idItem, String manufactures) {
        var ds = configDataSource();
        try (var conn = ds.getConnection()) {
            var sql = "UPDATE dbo.Item SET Manufactures = ? WHERE ItemId = ? ";
            var prepareStm = conn.prepareStatement(sql);
            prepareStm.setString(1, manufactures);
            prepareStm.setString(2, idItem);
            return prepareStm.executeUpdate();
        } catch (SQLServerException e) {
            e.printStackTrace();
            return -1;
        } catch (SQLException e) {
           e.printStackTrace();
           return -1;
        }
    }

    private static int insertItemToSql(Scanner input) {
        var simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SQLServerDataSource ds = configDataSource();
        System.out.print("idItem : ");
        var idItem = input.nextLine();
        System.out.print("nameItem : ");
        var nameItem = input.nextLine();
        System.out.print("typeItem : ");
        var typeItem = input.nextLine();
        System.out.print("manufacturer : ");
        var manufacturer = input.nextLine();

        System.out.print("dateOfManufacture : ");
        Date dateOfManufacture = null;
        try {
            dateOfManufacture = simpleDateFormat.parse(input.nextLine());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        System.out.print("expiry : ");
        Date expiry = null;
        try {
            expiry = simpleDateFormat.parse(input.nextLine());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        System.out.print("originalSellingPrice : ");
        var originalSellingPrice = input.nextLong();

        try (var conn = ds.getConnection()) {
            var sql = "INSERT INTO dbo.Item(ItemId,NameItem,TypeItem,OriginalSellingPrice,Manufactures,DateOfManufacture,expiry) " +
                    "VALUES(?,?,?,?,?,?,?)";
            var prepareSTM = conn.prepareStatement(sql);
            prepareSTM.setString(1, idItem);
            prepareSTM.setString(2, nameItem);
            prepareSTM.setString(3, typeItem);
            prepareSTM.setLong(4, originalSellingPrice);
            prepareSTM.setString(5, manufacturer);
            prepareSTM.setDate(6, new java.sql.Date(dateOfManufacture.getTime()));
            prepareSTM.setDate(7, new java.sql.Date(expiry.getTime()));
            return prepareSTM.executeUpdate();
        } catch (SQLServerException e) {
            e.printStackTrace();
            return -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private static void showItems(ArrayList<Item> items) {
        System.out.printf("%-30s%-30s%-30s%-30s%-30s%-30s%-30s\n",
                "idItem", "nameItem", "typeItem", "originalSellingPrice", "manufacturer",
                "dateOfManufacture", "expiry");
        for (var i : items) {
            showAItem(i);
        }
    }

    private static void showAItem(Item i) {
        var simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.printf("%-30s%-30s%-30s%-30d%-30s%-30s%-30s\n",
                i.getIdItem(), i.getNameItem(), i.getTypeItem(),
                i.getOriginalSellingPrice(), i.getManufacturer(),
                simpleDateFormat.format(i.getDateOfManufacture()),
                simpleDateFormat.format(i.getExpiry()));
    }

    private static ArrayList<Item> readItemFromSQLServer() {
        ArrayList<Item> items = new ArrayList<>();
        SQLServerDataSource dataSource = configDataSource();
        try (var conn = dataSource.getConnection()) {
            var sql = "SELECT * FROM dbo.Item";
            var ps = conn.prepareStatement(sql);
            var resultSet = ps.executeQuery();
            while (resultSet.next()) {
                var idItem = resultSet.getString(1);
                var nameItem = resultSet.getString(2);
                var typeItem = resultSet.getString(3);
                var originalSellingPrice = resultSet.getLong(4);
                var manufacturer = resultSet.getString(5);
                var dateOfManufacture = resultSet.getString(6);
                var expiry = resultSet.getString(7);
                var item = new Item(idItem, nameItem, typeItem, originalSellingPrice, manufacturer, dateOfManufacture, expiry);
                items.add(item);
            }
        } catch (SQLServerException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (InvalidBirthdayException e) {
            throw new RuntimeException(e);
        }
        return items;
    }


    private static SQLServerDataSource configDataSource() {
        SQLServerDataSource dataSource = new SQLServerDataSource();
        dataSource.setUser(USER);
        dataSource.setPassword(PASS);
        dataSource.setServerName(SERVER_NAME);
        dataSource.setDatabaseName(DATABASE_NAME);
        dataSource.setPortNumber(PORT);
        dataSource.setEncrypt(false);
        return dataSource;
    }

}
