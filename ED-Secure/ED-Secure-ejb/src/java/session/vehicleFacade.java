package session;

import entity.VhsVehicle;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Sourabh
 */
@Stateless
public class vehicleFacade implements vehicleFacadeLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "ED-Secure-ejbPU")
    private EntityManager em;

    public vehicleFacade() {
    }

    protected EntityManager getEntityManager() {
        return em;
    }

    private void create(VhsVehicle entity) {
        em.persist(entity);
    }

    private void edit(VhsVehicle entity) {
        em.merge(entity);
    }

    private void remove(VhsVehicle entity) {
        em.remove(em.merge(entity));
    }

    @Override
    public VhsVehicle find(String vehicleNumber) {
        return em.find(VhsVehicle.class, vehicleNumber);
    }

    @Override
    public ArrayList<VhsVehicle> findAllVehicles() {
        Query query = em.createQuery("SELECT v FROM VhsVehicle v");
//        ArrayList<VhsVehicle> tempResultList = (ArrayList<VhsVehicle>) (Collection<VhsVehicle>) 
        ArrayList<VhsVehicle> tempResultList = new ArrayList<>(query.getResultList());
        return tempResultList;
    }

}
