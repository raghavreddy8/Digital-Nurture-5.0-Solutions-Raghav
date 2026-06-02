
import java.sql.*;

public class InsertUpdateJDBCDemo {
    public static void insertStudent(Connection con, String name, int age) throws SQLException {
        String sql = "INSERT INTO students(name, age) VALUES(?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.executeUpdate();
            System.out.println("Inserted student: " + name);
        }
    }

    public static void updateStudentAge(Connection con, int id, int newAge) throws SQLException {
        String sql = "UPDATE students SET age=? WHERE id=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, newAge);
            ps.setInt(2, id);
            ps.executeUpdate();
            System.out.println("Updated student id " + id + " to age " + newAge);
        }
    }

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/students";
        String user = "root";
        String password = "#password";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            insertStudent(con, "Raghav", 22);
            updateStudentAge(con, 1, 25);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
