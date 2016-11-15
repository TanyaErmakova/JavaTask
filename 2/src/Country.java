public class Country {

        private int countryId;
    private String countryName;

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Country(String countryName) {
        this.countryName = countryName;
    }

    public Country(int countryId,String countryName) {
        this.countryName = countryName;
        this.countryId = countryId;
    }

    public Country() {
    }

    @Override
    public String toString() {
        return  countryId+","+countryName;
    }

}
