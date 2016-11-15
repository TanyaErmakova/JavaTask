import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class Runner {
    public static void main (String[] args) {
        try {
            CountryDao countryDao = new CountryDao();
            AccountDao accountDao = new AccountDao();
            CreditCardDao creditCardDao = new CreditCardDao();
            TransactionDao transactionDao = new TransactionDao();
            countryDao.insertAll(readCountries("src/external_files/countries.csv"));
            accountDao.insertAll(gAccounts("src/external_files/owners.csv"));
            creditCardDao.insertAll(gCreditCards(1000, 100, 100000));
            transactionDao.insertAll(gTransactions(1000, 1, 50000));
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            DBConnection.close();
        }
    }

    public static List<Country> readCountries(String input){
        File csv = new File(input);
        List<Country> countries = new ArrayList<Country>();
        try {
            Scanner scanner = new Scanner(csv);
            while (scanner.hasNext()){
                countries.add(new Country(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return countries;
    }

    public static List<Account> gAccounts(String input){
        File csv = new File(input);
        List<Account> accounts = new ArrayList<Account>();
        CountryDao countryDao = new CountryDao();
        List<Country> countries = countryDao.selectAll();
        Random randomGenerator = new Random();
        randomGenerator.nextInt(countries.size());
        try {
            Scanner scanner = new Scanner(csv);
            while (scanner.hasNext()){
                String line = scanner.nextLine();
                String[] parse = line.split(";");
                int rnd = countries.get(randomGenerator.nextInt(countries.size())).getCountryId();
                accounts.add(new Account(parse[0],parse[1],rnd));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    public static List<CreditCard> gCreditCards(int count, double min, double max){
        List<CreditCard> creditCards = new ArrayList<CreditCard>();
        AccountDao accountDao = new AccountDao();
        List<Account> accounts = accountDao.selectAll();
        Random randomGenerator = new Random();
        for (int i=0;i<count;i++){
            int accountId = accounts.get(randomGenerator.nextInt(accounts.size())).getAccountId();
            Double cashAmount = randomGenerator.nextDouble() ;
            creditCards.add(new CreditCard(accountId,cashAmount));
        }
        return creditCards;
    }

    public static List<Transaction> gTransactions(int count, double min, double max){
        List<Transaction> transactions = new ArrayList<Transaction>();
        CreditCardDao creditCardDao = new CreditCardDao();
        List<CreditCard> creditCards = creditCardDao.selectAll();
        Random randomGenerator = new Random();
        for (int i=0;i<count;i++){
            int fromCreditCardId = creditCards.get(randomGenerator.nextInt(creditCards.size())).getCreditCardId();
            int toCreditCardId = creditCards.get(randomGenerator.nextInt(creditCards.size())).getCreditCardId();
            Double transactionAmount = randomGenerator.nextDouble() ;
            if (fromCreditCardId!=toCreditCardId) {
                transactions.add(new Transaction(fromCreditCardId,toCreditCardId,transactionAmount));
            } else {
                i--;
            }
        }
        return transactions;
    }
}