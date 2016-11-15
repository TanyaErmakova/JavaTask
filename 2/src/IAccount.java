import java.util.List;
public interface IAccount  {
    public void insertAll(List<Account> accounts);
    public   void insert(Account account);
    public  List<Account> selectAll();
    public  Account select(int accountId);
    public  void delete(int accountId);
    public void update(Account account);
}
