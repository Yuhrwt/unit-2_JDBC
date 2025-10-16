import java.sql.*;
import java.util.Scanner;

public class part_b {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/testdb";
        String user = "root";
        String password = "yourpassword";

        try (Connection con = DriverManager.getConnection(url, user, password);
             Scanner sc = new Scanner(System.in)) {

            con.setAutoCommit(false);
            int choice;

            do {
                System.out.println("\n--- Product Management ---");
                System.out.println("1. Add Product");
                System.out.println("2. View Products");
                System.out.println("3. Update Product");
                System.out.println("4. Delete Product");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter ProductID, Name, Price, Quantity: ");
                        int id = sc.nextInt();
                        String name = sc.next();
                        double price = sc.nextDouble();
                        int qty = sc.nextInt();

                        PreparedStatement ps1 = con.prepareStatement(
                                "INSERT INTO Product VALUES(?, ?, ?, ?)");
                        ps1.setInt(1, id);
                        ps1.setString(2, name);
                        ps1.setDouble(3, price);
                        ps1.setInt(4, qty);
                        ps1.executeUpdate();
                        con.commit();
                        System.out.println("Product Added!");
                        break;

                    case 2:
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery("SELECT * FROM Product");
                        System.out.println("ID\tName\tPrice\tQuantity");
                        while (rs.next()) {
                            System.out.println(rs.getInt(1) + "\t" +
                                    rs.getString(2) + "\t" +
                                    rs.getDouble(3) + "\t" +
                                    rs.getInt(4));
                        }
                        break;

                    case 3:
                        System.out.print("Enter ProductID to update: ");
                        int upId = sc.nextInt();
                        System.out.print("Enter new Price and Quantity: ");
                        double newPrice = sc.nextDouble();
                        int newQty = sc.nextInt();

                        PreparedStatement ps2 = con.prepareStatement(
                                "UPDATE Product SET Price=?, Quantity=? WHERE ProductID=?");
                        ps2.setDouble(1, newPrice);
                        ps2.setInt(2, newQty);
                        ps2.setInt(3, upId);
                        ps2.executeUpdate();
                        con.commit();
                        System.out.println("Product Updated!");
                        break;

                    case 4:
                        System.out.print("Enter ProductID to delete: ");
                        int delId = sc.nextInt();

                        PreparedStatement ps3 = con.prepareStatement(
                                "DELETE FROM Product WHERE ProductID=?");
                        ps3.setInt(1, delId);
                        ps3.executeUpdate();
                        con.commit();
                        System.out.println("Product Deleted!");
                        break;

                    case 5:
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("Invalid choice!");
                }

            } while (choice != 5);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
