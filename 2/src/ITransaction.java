
import java.util.List;

public interface ITransaction {
    public void insertAll(List<Transaction> transactions);
    public void insert(Transaction transaction) ;
    public  List<Transaction> selectAll();
    public  Transaction select(int transactionId);
    public void delete(int transactionId);
    public void update(Transaction transaction);

}
