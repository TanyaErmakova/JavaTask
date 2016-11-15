import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryDao implements  ICountry {
    @Override
    public  void insertAll(List<Country> countries) {
        Connection con = DBConnection.getConnection();
        PreparedStatement statement= null;
        try {
            statement = con.prepareStatement("INSERT INTO COUNTRIES (COUNTRY_ID, COUNTRY) VALUES (COUNTRIES_SEQ.NEXTVAL,?)");
            for (Country country : countries) {
                statement.setString(1, country.getCountryName());
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void insert(Country country) {
        Connection con = DBConnection.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement("INSERT INTO COUNTRIES (COUNTRY_ID, COUNTRY) VALUES (COUNTRIES_SEQ.NEXTVAL,?)");
            statement.setString(1,country.getCountryName());
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Country> selectAll() {
        Connection con = DBConnection.getConnection();
        List<Country> countries = new ArrayList<Country>();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM COUNTRIES");
            while (rs.next()){
                countries.add(new Country(rs.getInt("COUNTRY_ID"),rs.getString("COUNTRY")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return countries;
    }

    @Override
    public Country select(int countryId) {
        Connection con = DBConnection.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        Country country = null;
        try {
            statement = con.prepareStatement("SELECT * FROM COUNTRIES WHERE COUNTRY_ID=?");
            statement.setInt(1,countryId);
            rs = statement.executeQuery();
            while (rs.next()) {
                country = new Country(rs.getInt("COUNTRY_ID"), rs.getString("COUNTRY"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return country;
    }

    @Override
    public void delete(int countryId) {
        Connection con = DBConnection.getConnection();
        PreparedStatement statement = null;
        Country country = null;
        try {
            statement = con.prepareStatement("DELETE FROM COUNTRIES WHERE COUNTRY_ID=?");
            statement.setInt(1,countryId);
            statement.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Country country) {
        Connection con = DBConnection.getConnection();
        PreparedStatement statement = null;
        try {
            statement=con.prepareStatement("UPDATE COUNTRIES SET COUNTRY=? WHERE COUNTRY_ID=?");
            statement.setString(1,country.getCountryName());
            statement.setInt(2,country.getCountryId());
            statement.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
