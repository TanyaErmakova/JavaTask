
public class CreditCard  {
    private int creditCardId;
    private int accountId;
    private double cashAmount;

    public CreditCard(int creditCardId, int accountId, double cashAmount) {
        this.creditCardId = creditCardId;
        this.accountId = accountId;
        this.cashAmount = cashAmount;
    }

    public CreditCard(int accountId, double cashAmount) {
        this.accountId = accountId;
        this.cashAmount = cashAmount;
    }

    public CreditCard() {
    }

    public int getCreditCardId() {
        return creditCardId;
    }

    public void setCreditCardId(int creditCardId) {
        this.creditCardId = creditCardId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public double getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(double cashAmount) {
        this.cashAmount = cashAmount;
    }

}
