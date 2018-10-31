
package session;

import entity.BookingDTO;
import entity.CustomerDTO;
import entity.VehicleDTO;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.ejb.Remote;

/**
 *
 * @author Sourabh
 */
@Remote
public interface VHSManagementRemote {
    
    ArrayList<VehicleDTO> showAvailableVehicles();

    public boolean checkUserId(int userId);

    public CustomerDTO getUserInfoFromUserId(int userId);

    public boolean checkVehicleNumber(String vehicleNumber);

    public VehicleDTO getVehicleInfoFromVehicleNo(String vehicleNumber);

    public boolean checkAvailabilityForSpecificVehicle(String vehicleNumber, LocalDate preferredDate, LocalTime preferredTime, int preferredDurationForHire);

    public boolean AddNeReservation(LocalDate preferredDate, LocalTime preferredTime, String pickUpLocation, int preferredDurationForHire, CustomerDTO customer, VehicleDTO vehicle);

    public ArrayList<BookingDTO> getAllHireRecordForCustomer(int userId);

    public ArrayList<BookingDTO> getAllHireRecordForVehicle(String vehicleNumber);

    public ArrayList<BookingDTO> getOverDueVehicles();

    public BookingDTO findReservedVehicle(int customerId, String vehicleBookingId);

    public String returnVehicleAndCalculateCharges(Long i, int reservedTime, BookingDTO reservation, LocalDate returningDate, LocalTime returningTime);
    
    public boolean addNewCustomerRequestCustomer(String customerName,String phone,String address, String email,String password);

    public ArrayList<CustomerDTO> findAllPendingCustomerRequests();

    public boolean addNewCustomerRequestCustomer(String customerName, String phone, String address, String email, String password, String customerType);

    public String approvePendingRequest(String customerId);

}
