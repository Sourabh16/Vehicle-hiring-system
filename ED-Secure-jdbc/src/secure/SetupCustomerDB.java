/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package secure;

import java.util.ArrayList;

/**
 *
 * @author elau
 */
public class SetupCustomerDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // the database object to access the actual database
        CustomerDB db = new CustomerDB();

        // make sure no name conflicts
        try {
            db.destroyDBTable("VHS_CUSTOMER");
        } catch (Exception ex) {
        }

        // create the database table
        System.out.println("Create an empty database table Customer");
        db.createCustomerDBTable();
        
        System.out.println("Add several static records in the database table");
        
        
        // prepare data
        Customer cust001 = new Customer("1001", "Adam", "1234567890", "1 John Street, Hawthorn",
                "sourabhkolekar16@gmail.com", "11111111", "Budget",true);
        Customer cust002 = new Customer("1002", "Bill", "2345678901", "2 Paul Street, Hawthorn",
                "sourabhkolekar17@gmail.com", "22222222", "Premium", true);
        Customer cust003 = new Customer("1003", "Ceci", "3456789012", "3 Mary Street, Hawthorn",
                "sourabhkolekar16@outlook.com", "33333333", "Budget", true);
        
        // prepare list
        ArrayList<Customer> custList = new ArrayList<>();
        custList.add(cust001);
        custList.add(cust002);
        custList.add(cust003);
        
        // add data to db
        db.addCustomerRecords(custList);
    }
}
