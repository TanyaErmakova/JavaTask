
public class Transaction {
    private int trasactionId;
    private int fromCreditCardId;
    private int toCreditCardId;
    private double transactionAmount;

    public Transaction(int trasactionId, int fromCreditCardId, int toCreditCardId, double transactionAmount) {
        this.trasactionId = trasactionId;
        this.fromCreditCardId = fromCreditCardId;
        this.toCreditCardId = toCreditCardId;
        this.transactionAmount = transactionAmount;

    }

    public Transaction(int fromCreditCardId, int toCreditCardId, double transactionAmount) {
        this.fromCreditCardId = fromCreditCardId;
        this.toCreditCardId = toCreditCardId;
        this.transactionAmount = transactionAmount;
    }

    public Transaction() {
    }

    public int getFromCreditCardId() {
        return fromCreditCardId;
    }

    public void setFromCreditCardId(int fromCreditCardId) {
        this.fromCreditCardId = fromCreditCardId;
    }

    public int getTrasactionId() {
        return trasactionId;
    }

    public void setTrasactionId(int trasactionId) {
        this.trasactionId = trasactionId;
    }

    public int getToCreditCardId() {
        return toCreditCardId;
    }

    public void setToCreditCardId(int toCreditCardId) {
        this.toCreditCardId = toCreditCardId;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }



}
