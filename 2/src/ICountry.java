import java.util.List;
public interface ICountry   {

     public void insertAll(List<Country> countries) ;
     public  void insert(Country country) ;
     public List<Country> selectAll();
     public Country select(int countryId);
     public void delete(int countryId);
     public  void update(Country country);
}
