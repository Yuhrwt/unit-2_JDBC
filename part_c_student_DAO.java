import java.sql.*;

public class part_c_student_DAO {
    private Connection con;

    public part_c_student_DAO() throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "root", "yourpassword");
    }

    public void addStudent(part_c_student s) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO Student VALUES (?, ?, ?, ?)");
        ps.setInt(1, s.getStudentID());
        ps.setString(2, s.getName());
        ps.setString(3, s.getDepartment());
        ps.setDouble(4, s.getMarks());
        ps.executeUpdate();
    }

    public void viewStudents() throws SQLException {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM Student");
        System.out.println("ID\tName\tDepartment\tMarks");
        while (rs.next()) {
            System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getDouble(4));
        }
    }

    public void updateStudent(int id, double marks) throws SQLException {
        PreparedStatement ps = con.prepareStatement("UPDATE Student SET Marks=? WHERE StudentID=?");
        ps.setDouble(1, marks);
        ps.setInt(2, id);
        ps.executeUpdate();
    }

    public void deleteStudent(int id) throws SQLException {
        PreparedStatement ps = con.prepareStatement("DELETE FROM Student WHERE StudentID=?");
        ps.setInt(1, id);
        ps.executeUpdate();
    }
}
