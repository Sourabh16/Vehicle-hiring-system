package session;

import entity.VhsVehicle;
import java.util.ArrayList;
import javax.ejb.Local;

/**
 *
 * @author Sourabh
 */
@Local
public interface vehicleFacadeLocal {
    
    VhsVehicle find(String vehicleNumber);
    ArrayList<VhsVehicle> findAllVehicles();
    
}
