import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class TransactionDao implements ITransaction {
    @Override
    public void insertAll(List<Transaction> transactions) {
        Connection con = DBConnection.getConnection();
        PreparedStatement statement= null;
        try {
            statement = con.prepareStatement("INSERT INTO TRANSACTIONS (TRANSACTION_ID, FROM_CREDIT_CARD_ID, TO_CREDIT_CARD_ID, TRANSACTION_AMOUNT) VALUES (TRANSACTIONS_SEQ.NEXTVAL,?,?,?)");
            for (Transaction transaction : transactions) {
                statement.setInt(1, transaction.getFromCreditCardId());
                statement.setInt(2,transaction.getToCreditCardId());
                statement.setDouble(3,transaction.getTransactionAmount());
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void insert(Transaction transaction) {
        Connection con = DBConnection.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement("INSERT INTO TRANSACTIONS (TRANSACTION_ID, FROM_CREDIT_CARD_ID, TO_CREDIT_CARD_ID, TRANSACTION_AMOUNT) VALUES (TRANSACTIONS_SEQ.NEXTVAL,?,?,?)");
            statement.setInt(1, transaction.getFromCreditCardId());
            statement.setInt(2,transaction.getToCreditCardId());
            statement.setDouble(3,transaction.getTransactionAmount());
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Transaction> selectAll() {
        Connection con = DBConnection.getConnection();
        List<Transaction> transactions = new ArrayList<Transaction>();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM TRANSACTIONS");
            while (rs.next()){
                transactions.add(new Transaction(rs.getInt("TRANSACTION_ID"),rs.getInt("FROM_CREDIT_CARD_ID"),rs.getInt("TO_CREDIT_CARD_ID"),rs.getDouble("TRANSACTION_AMOUNT")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return transactions;
    }

    @Override
    public Transaction select(int transactionId) {
        Connection con = DBConnection.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        Transaction transaction = null;
        try {
            statement = con.prepareStatement("SELECT * FROM TRANSACTIONS WHERE TRANSACTION_ID=?");
            statement.setInt(1,transactionId);
            rs = statement.executeQuery();
            while (rs.next()) {
                transaction = new Transaction(rs.getInt("TRANSACTION_ID"),rs.getInt("FROM_CREDIT_CARD_ID"),rs.getInt("TO_CREDIT_CARD_ID"),rs.getDouble("TRANSACTION_AMOUNT"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return transaction;
    }

    @Override
    public void delete(int transactionId) {
        Connection con = DBConnection.getConnection();
        PreparedStatement statement = null;
        Transaction transaction = null;
        try {
            statement = con.prepareStatement("DELETE FROM TRANSACTIONS WHERE TRANSACTION_ID=?");
            statement.setInt(1,transactionId);
            statement.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Transaction transaction) {
        Connection con = DBConnection.getConnection();
        PreparedStatement statement = null;
        try {
            statement=con.prepareStatement("UPDATE TRANSACTIONS SET FROM_CREDIT_CARD_ID=?,TO_CREDIT_CARD_ID=?,TRANSACTION_AMOUNT=? WHERE TRANSACTION_ID=?");
            statement.setInt(1,transaction.getFromCreditCardId());
            statement.setInt(2,transaction.getToCreditCardId());
            statement.setDouble(3,transaction.getTransactionAmount());
            statement.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
