
package secure;

import java.io.Serializable;

public class Customer implements Serializable {
    
    private final String customerId;
    private final String customerName;
    private final String phone;
    private final String address;
    private final String email;
    private final String password;
    private final String customerType;
    private final boolean active; 

    public Customer(String empid, String name, String phone, String address, 
            String email, String password, String appGroup,
            boolean active) {
        this.customerId = empid;
        this.customerName = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.password = password;
        this.customerType = appGroup;
        this.active = active;       // whether the customer is currently active
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


    public boolean isActive() {
        return active;
    }

}
