import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDao implements IAccount {


    @Override
    public void insertAll(List<Account> accounts) {
        Connection con = DBConnection.getConnection();
                PreparedStatement statement= null;
        try {
            statement = con.prepareStatement("INSERT INTO ACCOUNTS (ACCOUNT_ID, FIRST_NAME,LAST_NAME,COUNTRY_ID) VALUES (ACCOUNTS_SEQ.NEXTVAL,?,?,?)");
            for (Account account : accounts) {
                statement.setString(1, account.getFirstName());
                statement.setString(2,account.getLastName());
                statement.setInt(3,account.getCountryId());
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void insert(Account account) {
        Connection con = DBConnection.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement("INSERT INTO ACCOUNTS (ACCOUNT_ID, FIRST_NAME,LAST_NAME,COUNTRY_ID) VALUES (ACCOUNTS_SEQ.NEXTVAL,?,?,?)");
            statement.setString(1, account.getFirstName());
            statement.setString(2,account.getLastName());
            statement.setInt(3,account.getCountryId());
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public List<Account> selectAll() {
        Connection con = DBConnection.getConnection();
        List<Account> accounts = new ArrayList<Account>();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM ACCOUNTS");
            while (rs.next()){
                accounts.add(new Account(rs.getInt("ACCOUNT_ID"),rs.getString("FIRST_NAME"),rs.getString("LAST_NAME"),rs.getInt("COUNTRY_ID")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return accounts;
    }

    @Override
    public Account select(int accountId) {
        Connection con = DBConnection.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        Account account = null;
        try {
            statement = con.prepareStatement("SELECT * FROM ACCOUNTS WHERE ACCOUNT_ID=?");
            statement.setInt(1,accountId);
            rs = statement.executeQuery();

            while (rs.next()) {
                account = new Account(rs.getInt("ACCOUNT_ID"),rs.getString("FIRST_NAME"),rs.getString("LAST_NAME"),rs.getInt("COUNTRY_ID"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return account;
    }

    @Override
    public void delete(int accountId) {
        Connection con = DBConnection.getConnection();
        PreparedStatement statement = null;
        Account account = null;
        try {
            statement = con.prepareStatement("DELETE FROM ACCOUNTS WHERE ACCOUNT_ID=?");
            statement.setInt(1,accountId);
            statement.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Account account) {
        Connection con = DBConnection.getConnection();
        PreparedStatement statement = null;
        try {
            statement=con.prepareStatement("UPDATE ACCOUNTS SET FIRST_NAME=?, LAST_NAME=?,COUNTRY_ID=? WHERE ACCOUNT_ID=?");
            statement.setString(1,account.getFirstName());
            statement.setString(2,account.getLastName());
            statement.setInt(3,account.getCountryId());
            statement.setInt(4,account.getAccountId());
            statement.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
