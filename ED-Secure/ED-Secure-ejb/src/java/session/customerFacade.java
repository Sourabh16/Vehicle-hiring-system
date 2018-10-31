package session;

import entity.VhsCustomer;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author Sourabh
 */
@Stateless
public class customerFacade implements customerFacadeLocal {

    @PersistenceContext(unitName = "ED-Secure-ejbPU")
    private EntityManager em;

    Logger log;
    public customerFacade() {
    }

    protected EntityManager getEntityManager() {
        return em;
    }

    private void create(VhsCustomer entity) {
        try {
            System.out.println("Entity: "+entity.toString());
            
            em.persist(entity);
        } catch (ConstraintViolationException e) {
            log.log(Level.SEVERE, "Exception: ");
            e.getConstraintViolations().forEach(err -> log.log(Level.SEVERE, err.toString()));
        }
    }

    private void edit(VhsCustomer entity) {
        em.merge(entity);
    }

    private void remove(VhsCustomer entity) {
        em.remove(em.merge(entity));
    }

    @Override
    public VhsCustomer find(String customerId) {
        return em.find(VhsCustomer.class, customerId);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public ArrayList<VhsCustomer> findAllCustomers() {
        Query query = em.createQuery("SELECT v FROM VhsCustomer v");
//        ArrayList<VhsVehicle> tempResultList = (ArrayList<VhsVehicle>) (Collection<VhsVehicle>) 
        ArrayList<VhsCustomer> tempResultList = new ArrayList<>(query.getResultList());
        return tempResultList;
    }

    @Override
    public boolean addCustomer(VhsCustomer customer) {
        try {
            create(customer);
            return true;
        } catch (Exception e) {
            System.err.println("exception while creating new customer:" +e);
            return false;
        }
    }

    @Override
    public ArrayList<VhsCustomer> findAllApprovalPendingCustomers() {
        
        boolean status=Boolean.FALSE;
        Query query = em.createQuery("SELECT v FROM VhsCustomer v WHERE v.active = :active");
        query.setParameter("active", status);
//        ArrayList<VhsVehicle> tempResultList = (ArrayList<VhsVehicle>) (Collection<VhsVehicle>) 
        ArrayList<VhsCustomer> tempResultList = new ArrayList<>(query.getResultList());
        return tempResultList;    
    }

    @Override
    public boolean updateCustomerStatus(VhsCustomer customer) {
        
        VhsCustomer e = find(customer.getCustomerid());
        e.setActive(Boolean.TRUE);

        // check again - just to play it safe
        if (e == null) {
            return false;
        }

        // no need to update the primary key - empId
        em.merge(e);
        return true;
    }
}
