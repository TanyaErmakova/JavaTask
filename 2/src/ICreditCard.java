
import java.util.List;

public interface ICreditCard  {
    public  void insertAll(List<CreditCard> creditCards);
    public  void insert(CreditCard creditCard) ;
    public  List<CreditCard> selectAll();
    public  CreditCard select(int creditCardId);
    public  void delete(int creditCardId);
    public void update(CreditCard creditCard);
}
