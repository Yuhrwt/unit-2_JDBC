import java.sql.*;

public class part_a {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/testdb"; // your DB name
        String user = "root";
        String password = "yourpassword";

        try {
            // Step 1: Load JDBC driver (optional for modern Java)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Establish connection
            Connection con = DriverManager.getConnection(url, user, password);

            // Step 3: Create statement
            Statement stmt = con.createStatement();

            // Step 4: Execute query
            ResultSet rs = stmt.executeQuery("SELECT EmpID, Name, Salary FROM Employee");

            // Step 5: Process result set
            System.out.println("EmpID\tName\tSalary");
            while (rs.next()) {
                System.out.println(rs.getInt("EmpID") + "\t" + rs.getString("Name") + "\t" + rs.getDouble("Salary"));
            }

            // Step 6: Close connection
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
