package entity;

import java.io.Serializable;

/**
 *
 * @author Sourabh
 */
public class CustomerDTO implements Serializable {
    
    String customerId;
    String customerName;
    String phone;
    String address;
    String email;
    String password;
    String customerType;
    Boolean active;

    public CustomerDTO(String customerName, String phone, String address, String email, String password, String customerType, Boolean active) {
        this.customerName = customerName;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.password = password;
        this.customerType = customerType;
        this.active = active;
    }

    
    
    
    public CustomerDTO(String customerId, String customerName, String phone, String address, String email, String password, String customerType, Boolean active) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.password = password;
        this.customerType = customerType;
        this.active = active;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCustomerType() {
        return customerType;
    }

    public Boolean getActive() {
        return active;
    }


    
    
}
