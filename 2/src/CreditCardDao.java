import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 13.11.2016.
 */
public class CreditCardDao implements ICreditCard {
    @Override
    public void insertAll(List<CreditCard> creditCards) {
        Connection con = DBConnection.getConnection();
        PreparedStatement statement= null;
        try {
            statement = con.prepareStatement("INSERT INTO CREDIT_CARDS (CREDIT_CARD_ID, ACCOUNT_ID, CASH_AMOUNT) VALUES (CREDIT_CARDS_SEQ.NEXTVAL,?,?)");
            for (CreditCard creditCard : creditCards) {
                statement.setInt(1, creditCard.getAccountId());
                statement.setDouble(2,creditCard.getCashAmount());
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

    @Override
    public void insert(CreditCard creditCard) {
        Connection con = DBConnection.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement("INSERT INTO CREDIT_CARDS (CREDIT_CARD_ID, ACCOUNT_ID, CASH_AMOUNT) VALUES (CREDIT_CARDS_SEQ.NEXTVAL,?,?)");
            statement.setInt(1, creditCard.getAccountId());
            statement.setDouble(2,creditCard.getCashAmount());
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<CreditCard> selectAll() {
        Connection con = DBConnection.getConnection();
        List<CreditCard> creditCards = new ArrayList<CreditCard>();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM CREDIT_CARDS");
            while (rs.next()){
                creditCards.add(new CreditCard(rs.getInt("CREDIT_CARD_ID"),rs.getInt("ACCOUNT_ID"),rs.getDouble("CASH_AMOUNT")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return creditCards;
    }

    @Override
    public CreditCard select(int creditCardId) {
        Connection con = DBConnection.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        CreditCard creditCard = null;
        try {
            statement = con.prepareStatement("SELECT * FROM CREDIT_CARDS WHERE CREDIT_CARD_ID=?");
            statement.setInt(1,creditCardId);
            rs = statement.executeQuery();
            while (rs.next()) {
                creditCard = new CreditCard(rs.getInt("CREDIT_CARD_ID"),rs.getInt("ACCOUNT_ID"),rs.getDouble("CASH_AMOUNT"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return creditCard;
    }

    @Override
    public void delete(int creditCardId) {
        Connection con = DBConnection.getConnection();
        PreparedStatement statement = null;
        CreditCard creditCard = null;
        try {
            statement = con.prepareStatement("DELETE FROM CREDIT_CARDS WHERE CREDIT_CARD_ID=?");
            statement.setInt(1,creditCardId);
            statement.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(CreditCard creditCard) {
        Connection con = DBConnection.getConnection();
        PreparedStatement statement = null;
        try {
            statement=con.prepareStatement("UPDATE CREDIT_CARDS SET ACCOUNT_ID=?, CASH_AMOUNT=? WHERE CREDIT_CARD_ID=?");
            statement.setInt(1,creditCard.getAccountId());
            statement.setDouble(2,creditCard.getCashAmount());
            statement.setInt(3,creditCard.getCreditCardId());
            statement.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
