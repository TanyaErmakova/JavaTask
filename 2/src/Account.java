public class Account  {
    private int accountId;
    private String firstName;
    private String lastName;
    private int countryId;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public Account(String firstName, String lastName, int countryId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.countryId = countryId;
    }

    public Account(int accountId, String firstName, String lastName, int countryId) {
        this.accountId = accountId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.countryId = countryId;
    }

    public Account() {
    }


}
