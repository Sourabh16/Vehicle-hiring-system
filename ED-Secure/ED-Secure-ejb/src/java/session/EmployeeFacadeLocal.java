package session;

import entity.Employee;
import javax.ejb.Local;

/**
 *
 * @author elau
 */
@Local
public interface EmployeeFacadeLocal {

    Employee find(String id);

    boolean hasEmployee(String empId);
    
    boolean addEmployee(Employee employee);
    
    boolean updateEmployeeDetails(Employee employee);
    
    boolean updatePassword(String empId, String password, String oldPassword);

    boolean deleteEmployee(String empId);
    
    boolean removeEmployee(String empId);

}
