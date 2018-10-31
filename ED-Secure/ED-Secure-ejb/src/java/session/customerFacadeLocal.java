/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.VhsCustomer;
import java.util.ArrayList;
import javax.ejb.Local;

/**
 *
 * @author Sourabh
 */
@Local
public interface customerFacadeLocal {
    
    VhsCustomer find(String customerId);

    public ArrayList<VhsCustomer> findAllCustomers();

    public boolean addCustomer(VhsCustomer customer);

    public ArrayList<VhsCustomer> findAllApprovalPendingCustomers();

    public boolean updateCustomerStatus(VhsCustomer customer);
}
