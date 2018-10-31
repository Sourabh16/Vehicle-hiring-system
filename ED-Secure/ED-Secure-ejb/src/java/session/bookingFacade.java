package session;

import entity.VhsBooking;
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
public class bookingFacade implements bookingFacadeLocal {

    @PersistenceContext(unitName = "ED-Secure-ejbPU")
    private EntityManager em;

    public bookingFacade() {
    }

    protected EntityManager getEntityManager() {
        return em;
    }

    private void create(VhsBooking entity) {
        em.persist(entity);
        
    }

    private void edit(VhsBooking entity) {
        em.merge(entity);
    }

    private void remove(VhsBooking entity) {
        em.remove(em.merge(entity));
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public VhsBooking find(String bookingId) {
        return em.find(VhsBooking.class, bookingId);
    }
    
    public VhsBooking findToCalulateCharges(int bookingId) {
        return em.find(VhsBooking.class, bookingId);
    }

    @Override
    public ArrayList<VhsBooking> findAllBookings() {
        Query query = em.createQuery("SELECT v FROM VhsBooking v");
//        ArrayList<VhsVehicle> tempResultList = (ArrayList<VhsVehicle>) (Collection<VhsVehicle>) 
        ArrayList<VhsBooking> tempResultList = new ArrayList<>(query.getResultList());
        return tempResultList;
    }

    @Override
    public boolean addBooking(VhsBooking booking) {
    
        boolean result=false;
        try {
            System.out.println(booking.toString());
            create(booking);
            result=true;            
        } catch (Exception e) {
            System.out.println("session.bookingFacade.addBooking()"+e.toString());
        }
        return result;
    }

    @Override
    public boolean updateBookingDetails(VhsBooking booking, String returnDate, String returnTime) {
        VhsBooking e = findToCalulateCharges(booking.getBookingid());
        e.setReturndate(returnDate);
        e.setReturntime(returnTime);

        // check again - just to play it safe
        if (e == null) {
            return false;
        }

        // no need to update the primary key - empId
        em.merge(e);
        return true;
    }
}
