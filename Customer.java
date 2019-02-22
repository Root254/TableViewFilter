package populatingdatafromdatabase;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author user
 */
public class Customer {
    SimpleStringProperty name, email, discountCode;
    SimpleIntegerProperty creditLimit, zip, customerID;

    public Customer(Integer customerID, String discountCode, Integer zip, String name, String email, Integer creditLimit) {
        this.customerID = new SimpleIntegerProperty(customerID);
        this.discountCode = new SimpleStringProperty(discountCode);
        this.zip = new SimpleIntegerProperty(zip);
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.creditLimit = new SimpleIntegerProperty(creditLimit);       
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email = new SimpleStringProperty(email);
    }

    public String getDiscountCode() {
        return discountCode.get();
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = new SimpleStringProperty(discountCode);
    }

    public Integer getCreditLimit() {
        return creditLimit.get();
    }

    public void setCreditLimit(Integer creditLimit) {
        this.creditLimit = new SimpleIntegerProperty(creditLimit);
    }

    public Integer getZip() {
        return zip.get();
    }

    public void setZip(Integer zip) {
        this.zip = new SimpleIntegerProperty(zip);
    }

    public Integer getCustomerID() {
        return customerID.get();
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = new SimpleIntegerProperty(customerID);
    }   
}
