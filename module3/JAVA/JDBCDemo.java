import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class JDBCDemo {
    private final static String URL="jdbc:mysql://localhost:3306/students"; 
    private final static String USERNAME="root"; 
    private final static String PASSWORD="#password";

    public static void main(String[] args) throws SQLException{
        try{
            Connection con=DriverManager.getConnection(URL,USERNAME,PASSWORD);
            Statement stmt=con.createStatement();
            stmt.execute("USE Upskilling;");
            ResultSet rs=stmt.executeQuery("SELECT user_id,full_name FROM Users;");

            while(rs.next()){
                System.err.println(rs.getInt("user_id")+","+rs.getString("full_name"));
            }   
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
