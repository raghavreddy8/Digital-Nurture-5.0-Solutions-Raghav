import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionHandlingDemo{
    private static final String URL = "jdbc:mysql://localhost:3306/upskilling";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "raghavr";

    // Method to transfer money between two accounts
    public static void transferMoney(Connection con, int fromId, int toId, double amount) throws SQLException {
        String debitSql = "UPDATE accounts SET balance = balance - ? WHERE id = ?";
        String creditSql = "UPDATE accounts SET balance = balance + ? WHERE id = ?";

        con.setAutoCommit(false); 
        try (PreparedStatement debit = con.prepareStatement(debitSql);
             PreparedStatement credit = con.prepareStatement(creditSql)) {

            // debit from sender
            debit.setDouble(1, amount);
            debit.setInt(2, fromId);
            debit.executeUpdate();

            // credit to receiver
            credit.setDouble(1, amount);
            credit.setInt(2, toId);
            credit.executeUpdate();

            con.commit(); 
            System.out.println("Transferred " + amount + " from account " + fromId + " to account " + toId);

        } catch (SQLException e) {
            con.rollback();
            System.out.println("Transfer failed, rolled back.");
        } finally {
            con.setAutoCommit(true); 
        }
    }

    // Method to show balances
    public static void showBalances(Connection con) throws SQLException {
        String sql = "SELECT id, name, balance FROM accounts";
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " - " +
                                   rs.getString("name") + " - Balance: " +
                                   rs.getDouble("balance"));
            }
        }
    }

    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            System.out.println("Before transfer:");
            showBalances(con);

            transferMoney(con, 1, 2, 200.0); 

            System.out.println("After transfer:");
            showBalances(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
