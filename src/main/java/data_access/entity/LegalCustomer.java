package data_access.entity;

/**
 * Created by DOTIN SCHOOL 4 on 9/28/2016.
 */
public class LegalCustomer {
    private String companyName;
    private String registrationDate;
    private String economicCode;
    private Integer customerId;

    public String getCompanyName() {
        return companyName;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public String getEconomicCode() {
        return economicCode;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setEconomicCode(String economicCode) {
        this.economicCode = economicCode;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return getCompanyName() + ", " + getRegistrationDate() + ", " + getEconomicCode() ;
    }
}
