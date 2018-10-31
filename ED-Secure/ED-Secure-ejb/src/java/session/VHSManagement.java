package session;

import Utility.DateUtility;
import entity.BookingDTO;
import entity.CustomerDTO;
import entity.VehicleDTO;
import entity.VhsBooking;
import entity.VhsCustomer;
import entity.VhsVehicle;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Sourabh
 */
@DeclareRoles({"ED-APP-ADMIN", "ED-APP-USERS"})
@Stateless(name = "VHSManagement")
public class VHSManagement implements VHSManagementRemote {

    @EJB
    private bookingFacadeLocal bookingFacade;

    @EJB
    private vehicleFacadeLocal vehicleFacade;

    @EJB
    private customerFacadeLocal customerFacade;

    private VhsVehicle vehicleDTO2Entity(VehicleDTO vehicleDTO) {
        if (vehicleDTO == null) {
            // just in case
            return null;
        }

        String vehicleNumber = vehicleDTO.getVehicleNumber().toUpperCase();
        String vehicleType = vehicleDTO.getVehicleType();
        String vehicleName = vehicleDTO.getVehicleName();
        String registrationYear = vehicleDTO.getRegistrationYear();
        String fuelType = vehicleDTO.getFuelType();

        VhsVehicle vehicle = new VhsVehicle(vehicleNumber, vehicleType, vehicleName, registrationYear, fuelType);

        return vehicle;
    }

    private VhsCustomer customerDTO2Entity(CustomerDTO customerDTO) {
        if (customerDTO == null) {
            // just in case
            return null;
        }

        String customerId = customerDTO.getCustomerId();
        String address = customerDTO.getAddress();
        String customerName = customerDTO.getCustomerName();
        String customerType = customerDTO.getCustomerType();
        String email = customerDTO.getEmail();
        String password = customerDTO.getPassword();
        String phone = customerDTO.getPhone();
        boolean active = customerDTO.getActive();

//        VhsCustomer customer = new VhsCustomer(Integer.parseInt(customerId), customerName, phone, address, email, password, customerType, active);
        VhsCustomer customer = new VhsCustomer(customerId, customerName, phone, address, email, password, customerType, active);

        return customer;
    }

    private VhsCustomer customerDTO2EntityFrRegistration(CustomerDTO customerDTO) {
        if (customerDTO == null) {
            // just in case
            return null;
        }

        String address = customerDTO.getAddress();
        String customerName = customerDTO.getCustomerName();
        String customerType = customerDTO.getCustomerType();
        String email = customerDTO.getEmail();
        String password = customerDTO.getPassword();
        String phone = customerDTO.getPhone();
        boolean active = customerDTO.getActive();

        VhsCustomer customer = new VhsCustomer(customerName, phone, address, email, password, customerType, active);

        return customer;
    }

    private VhsBooking bookingDTO2Entity(BookingDTO bookingDTO) {
        if (bookingDTO == null) {
            // just in case
            return null;
        }

        String bookingDate = bookingDTO.getBookingDate();
        int bookingId = Integer.parseInt(bookingDTO.getBookingId());
        String bookingTime = bookingDTO.getBookingTime();
        String customerId = bookingDTO.getCustomerId();
        String vehicleNumber = bookingDTO.getVehicleNumber();
        String hireDuartion = bookingDTO.getHireDuration();
        String pickupLocation = bookingDTO.getPickupLocation();
        String returnDate = bookingDTO.getReturnDate();
        String returnTime = bookingDTO.getReturnTime();

        VhsCustomer tempCustomer = customerFacade.find(customerId);
        VhsVehicle tempVehicle = vehicleFacade.find(vehicleNumber);

        VhsBooking booking = new VhsBooking(bookingId, bookingDate, bookingTime, pickupLocation, hireDuartion, returnDate, returnTime, tempCustomer, tempVehicle);

        return booking;
    }

    private VehicleDTO vehicleEntity2DTO(VhsVehicle vehicle) {
        if (vehicle == null) {
            // just in case
            return null;
        }
        VehicleDTO vehicleDTO = new VehicleDTO(
                vehicle.getVehiclenumber(),
                vehicle.getVehicletype(),
                vehicle.getVehiclename(),
                vehicle.getRegistrationyear(),
                vehicle.getFueltype()
        );

        return vehicleDTO;
    }

    private CustomerDTO customerEntity2DTO(VhsCustomer customer) {
        if (customer == null) {
            // just in case
            return null;
        }
        CustomerDTO customerDTO = new CustomerDTO(String.valueOf(customer.getCustomerid()), customer.getCustomername(), customer.getPhone(),
                customer.getAddress(), customer.getAddress(), customer.getPassword(), customer.getCustomertype(), customer.getActive());

        return customerDTO;
    }

    private BookingDTO bookingEntity2DTO(VhsBooking booking) {
        if (booking == null) {
            // just in case
            return null;
        }
        VhsCustomer customer = booking.getCustomerid();
        String customerId = String.valueOf(customer.getCustomerid());
        VhsVehicle vehicle = booking.getVehiclenumber();
        String vehicleNumber = vehicle.getVehiclenumber();

        BookingDTO bookingDTO = new BookingDTO(String.valueOf(booking.getBookingid()), booking.getBookingdate(), booking.getBookingtime(),
                booking.getPickuplocation(), booking.getHireduration(), customerId, vehicleNumber,
                booking.getReturndate(),
                booking.getBookingtime());

        return bookingDTO;
    }

    @Override
    public ArrayList<VehicleDTO> showAvailableVehicles() {

        ArrayList<VehicleDTO> vehicleDTOList = getAllVehicleDataFromDB();
        return vehicleDTOList;
    }

    /**
     * returns all vehicle data from vehicle database
     *
     * @return vehicle database in form of ArrayList of vehicleDTO
     */
    private ArrayList<VehicleDTO> getAllVehicleDataFromDB() {
        ArrayList<VehicleDTO> tempVehicleDTOList = new ArrayList<>();

        ArrayList<VhsVehicle> dbVehicles = vehicleFacade.findAllVehicles();

        for (VhsVehicle dbVehicle : dbVehicles) {

            VehicleDTO tempDTO = vehicleEntity2DTO(dbVehicle);
            tempVehicleDTOList.add(tempDTO);
        }
        return tempVehicleDTOList;
    }

    /**
     * returns all customer data from Customer database
     *
     * @return customer database in form of ArrayList of customerDTO
     */
    private ArrayList<CustomerDTO> getAllCustomerDataFromDB() {
        ArrayList<CustomerDTO> tempCustomerDTOList = new ArrayList<>();

        ArrayList<VhsCustomer> dbCustomerList = customerFacade.findAllCustomers();

        for (VhsCustomer dbCustomer : dbCustomerList) {

            CustomerDTO tempDTO = customerEntity2DTO(dbCustomer);
            tempCustomerDTOList.add(tempDTO);
        }
        return tempCustomerDTOList;
    }

    /**
     * returns all booking data from booking database
     *
     * @return booking database in form of ArrayList of bookingDTO
     */
    private ArrayList<BookingDTO> getAllBookingDataFromDB() {
        ArrayList<BookingDTO> dtoList = new ArrayList<>();

        ArrayList<VhsBooking> dbBookingsList = bookingFacade.findAllBookings();

        for (VhsBooking dbBooking : dbBookingsList) {

            BookingDTO tempDTO = bookingEntity2DTO(dbBooking);
            dtoList.add(tempDTO);
        }
        return dtoList;
    }

    @Override
    public boolean checkUserId(int userId) {
        ArrayList<CustomerDTO> customersList = getAllCustomerDataFromDB();
        boolean value = false;
        for (CustomerDTO customer : customersList) {
            if (customer.getCustomerId().equalsIgnoreCase(String.valueOf(userId))) {
                value = true;
            }
        }
        return value;
    }

    /**
     * fetches all customer details from database using customer id
     *
     * @param userId customer id
     * @return customer information
     */
    @Override
    public CustomerDTO getUserInfoFromUserId(int userId) {
        // get the employee
        VhsCustomer customer = customerFacade.find(String.valueOf(userId));

        if (customer == null) {
            // not found - no such user
            return null;
        } else {
            CustomerDTO tempCustomerDTO = customerEntity2DTO(customer);
            return tempCustomerDTO;
        }
    }

    /**
     * validates vehicle number
     *
     * @param vehicleNumber input vehicle number
     * @return vehicle number validated
     */
    @Override
    public boolean checkVehicleNumber(String vehicleNumber) {
        ArrayList<VehicleDTO> List = getAllVehicleDataFromDB();
        boolean value = false;
        for (VehicleDTO vehicle : List) {
            if (vehicle.getVehicleNumber().equalsIgnoreCase(vehicleNumber)) {
                value = true;
            }
        }
        return value;
    }

    @Override
    public VehicleDTO getVehicleInfoFromVehicleNo(String vehicleNumber) {
        // get the employee
        VhsVehicle vehicle = vehicleFacade.find(String.valueOf(vehicleNumber));

        if (vehicle == null) {
            // not found - no such user
            return null;
        } else {
            VehicleDTO tempvehDTO = vehicleEntity2DTO(vehicle);
            return tempvehDTO;
        }
    }

    /**
     * checks if given vehicle is available for requested Date and requested
     * Time
     *
     * @param vehicleNumber number of requested vehicle
     * @param requestedDate date of requested vehicle
     * @param requestedTime time of requested vehicle
     * @param preferredDurationForHire requested time period of requested
     * vehicle
     * @return availability in true or false
     */
    @Override
    @RolesAllowed({"ED-APP-ADMIN"})
    public boolean checkAvailabilityForSpecificVehicle(String vehicleNumber, LocalDate requestedDate, LocalTime requestedTime, int preferredDurationForHire) {
        ArrayList<BookingDTO> reservationList = getAllBookingDataFromDB();

//        ArrayList<Reservation> reservationList = company.getReservationList();
        LocalDateTime requestedDateTime = LocalDateTime.of(requestedDate, requestedTime);
        for (BookingDTO reservation : reservationList) {
            if (reservation.getVehicleNumber().equalsIgnoreCase(vehicleNumber)) {
                try {
                    LocalDate allottedPickupDate = LocalDate.parse(reservation.getBookingDate());
                    LocalTime allottedPickupTime = LocalTime.parse(reservation.getBookingTime());
                    int allottedResPeriod = Integer.parseInt(reservation.getHireDuration());
                    LocalDateTime allottedDateTime = LocalDateTime.of(allottedPickupDate, allottedPickupTime);
                    LocalDateTime endingDateTime = allottedDateTime.plusHours(allottedResPeriod);
                    if (requestedDateTime.isAfter(allottedDateTime) && requestedDateTime.isBefore(endingDateTime)) {
                        return false;
                    }
                } catch (Exception ex) {
                    Logger.getLogger(VHSManagement.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return true;

    }

    @Override
    @RolesAllowed({"ED-APP-ADMIN"})
    public boolean AddNeReservation(LocalDate preferredDate, LocalTime preferredTime, String pickUpLocation, int preferredDurationForHire, CustomerDTO customer,
            VehicleDTO vehicle) {

        boolean result = false;

        try {
            VhsBooking booking = new VhsBooking();
            booking.setBookingdate(DateUtility.convertLocalDateToString(preferredDate));
            booking.setBookingtime(DateUtility.convertLocalTimeToString(preferredTime));
            booking.setPickuplocation(pickUpLocation);
            booking.setHireduration(String.valueOf(preferredDurationForHire));
            VhsCustomer vhsCustomer = customerDTO2Entity(customer);
            booking.setCustomerid(vhsCustomer);
            VhsVehicle vhsVehicle = vehicleDTO2Entity(vehicle);
            booking.setVehiclenumber(vhsVehicle);

            // add one
            return bookingFacade.addBooking(booking);
        } catch (Exception e) {
            System.err.println("Exception occurred while adding new booking:" + e.toString());
        }
        return result;

    }

    /**
     * returns all hiring records for given customer
     *
     * @param userId customer Id
     * @return ArrayList of all reservations against customer
     */
    @Override
    @RolesAllowed({"ED-APP-ADMIN"})
    public ArrayList<BookingDTO> getAllHireRecordForCustomer(int userId) {
        ArrayList<BookingDTO> reservations = getAllBookingDataFromDB();

        ArrayList<BookingDTO> tempReservations = new ArrayList<>();
        try {
            for (BookingDTO reservation : reservations) {
                String customerId = reservation.getCustomerId();
                if (customerId.equalsIgnoreCase(String.valueOf(userId))) {
                    tempReservations.add(reservation);
                }
            }
        } catch (Exception e) {
            System.out.println("Exception:" + e);
        }
        return tempReservations;
    }

    /**
     * returns all hiring record for the vehicle
     *
     * @param vehicleNumber vehicle number provided by user
     * @return ArrayList of Reservations for vehicle
     */
    @Override
    @RolesAllowed({"ED-APP-ADMIN"})
    public ArrayList<BookingDTO> getAllHireRecordForVehicle(String vehicleNumber) {
        ArrayList<BookingDTO> reservations = getAllBookingDataFromDB();

        ArrayList<BookingDTO> tempReservations = new ArrayList<>();
        try {
            for (BookingDTO reservation : reservations) {
                String dbvehicleNumber = reservation.getVehicleNumber();
                if (dbvehicleNumber.equalsIgnoreCase(vehicleNumber)) {
                    tempReservations.add(reservation);
                }
            }
        } catch (Exception e) {
            System.out.println("Exception:" + e);
        }
        return tempReservations;
    }

    @Override
    @RolesAllowed({"ED-APP-ADMIN"})
    public ArrayList<BookingDTO> getOverDueVehicles() {
        ArrayList<BookingDTO> reservationsList = getAllBookingDataFromDB();
        ArrayList<BookingDTO> tempReservationsList = new ArrayList<>();
        try {

            for (BookingDTO reservation : reservationsList) {
                LocalDate localDtBookingDate = LocalDate.parse(reservation.getBookingDate());
                LocalTime localTmBookingTime = LocalTime.parse(reservation.getBookingTime());
                LocalDateTime localDateTime = LocalDateTime.of(localDtBookingDate, localTmBookingTime);
                LocalDateTime idealReturnTime = localDateTime.plusHours(Long.parseLong(reservation.getHireDuration()));

                if (DateUtility.timeBetweenDateTimes(DateUtility.getCurrentDateTime(), idealReturnTime) < 0
                        && reservation.getReturnDate() == null) {
                    tempReservationsList.add(reservation);              //late      =  current time > return time
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(VHSManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
//        company.sortReservations(tempReservationsList);
        return tempReservationsList;
    }

    /**
     * returns reservation for particular vehicle and customer
     *
     * @param customerId Customer Id
     * @param vehicleBookingId booking id of vehicle
     * @return reservation details
     */
    @Override
    public BookingDTO findReservedVehicle(int customerId, String vehicleBookingId) {
        BookingDTO requiredReservation = new BookingDTO();
        try {
            ArrayList<BookingDTO> reservationList = getAllBookingDataFromDB();
            for (BookingDTO reservation : reservationList) {
                if (reservation.getCustomerId().equalsIgnoreCase(String.valueOf(customerId))
                        && reservation.getBookingId().equalsIgnoreCase(vehicleBookingId)) {
                    requiredReservation = reservation;
                }
            }
        } catch (Exception e) {
            System.out.println("Exception:" + e);
        }
        return requiredReservation;
    }

    /**
     * calculates charges and modifies record of reservation for given vehicle
     *
     * @param extraHours hours that are exceeded for reservation
     * @param reservedTime time for which vehicle was reserved
     * @param reserve Reservation details for the vehicle
     * @param returningDate returning date
     * @param returningTime returning time
     * @return charges in double value for the reservation
     * @throws IOException {@link IOException} in case of error occurrence for
     * reading the reservation file
     */
    @Override
    @RolesAllowed({"ED-APP-ADMIN"})
    public String returnVehicleAndCalculateCharges(Long extraHours, int reservedTime, BookingDTO reserve, LocalDate returningDate, LocalTime returningTime) {
        String returnDate = DateUtility.convertLocalDateToString(returningDate);
        String returnTime = DateUtility.convertLocalTimeToString(returningTime);

        // charges= hourly rate * hours + extraHours * hourly rate
        double chargesForVehicleHire = (reservedTime * reserve.getVehicleHourlyRate())
                + (extraHours * reserve.getVehicleHourlyRate());

        updateBookingData(returnDate, returnTime, reserve);

        return String.valueOf(chargesForVehicleHire);
    }

    private void updateBookingData(String returnDate, String returnTime, BookingDTO bookingDTO) {

        // convert to entity class
        VhsBooking booking = this.bookingDTO2Entity(bookingDTO);
        // update details
        boolean result = bookingFacade.updateBookingDetails(booking, returnDate, returnTime);
    }

    @Override
    public boolean addNewCustomerRequestCustomer(String customerName, String phone, String address, String email, String password) {

//        CustomerDTO tempCustomerDTO=new CustomerDTO(customerName, phone, address, email, password, "NA", Boolean.FALSE);
        String tempCustId = randomString(4);
        CustomerDTO tempCustomerDTO = new CustomerDTO(tempCustId, customerName, phone, address, email, password, "NA", Boolean.FALSE);

        // convert to entity
//        VhsCustomer customer = this.customerDTO2EntityFrRegistration(tempCustomerDTO);
        VhsCustomer customer = this.customerDTO2Entity(tempCustomerDTO);
        // add one
        return customerFacade.addCustomer(customer);

    }

    static final String AB = "0123456789";
    static SecureRandom rnd = new SecureRandom();

    String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return sb.toString();
    }

    @Override
    public ArrayList<CustomerDTO> findAllPendingCustomerRequests() {

        ArrayList<CustomerDTO> tempCustomerDTOList = new ArrayList<>();

        ArrayList<VhsCustomer> dbCustomerList = customerFacade.findAllApprovalPendingCustomers();

        for (VhsCustomer dbCustomer : dbCustomerList) {

            CustomerDTO tempDTO = customerEntity2DTO(dbCustomer);
            tempCustomerDTOList.add(tempDTO);
        }
        return tempCustomerDTOList;
    }

    @Override
    public boolean addNewCustomerRequestCustomer(String customerName, String phone, String address, String email, String password, String customerType) {
        String tempCustId = randomString(4);
        CustomerDTO tempCustomerDTO = new CustomerDTO(tempCustId, customerName, phone, address, email, password, customerType, Boolean.FALSE);

        // convert to entity
//        VhsCustomer customer = this.customerDTO2EntityFrRegistration(tempCustomerDTO);
        VhsCustomer customer = this.customerDTO2Entity(tempCustomerDTO);
        // add one
        return customerFacade.addCustomer(customer);
    }

    @Override
    public String approvePendingRequest(String customerId) {

        String result = "";
        try {
            CustomerDTO tempCustomerDTO = getUserInfoFromUserId(Integer.parseInt(customerId));
            // convert to entity class
            VhsCustomer customer = this.customerDTO2Entity(tempCustomerDTO);
            // update details
            boolean operationResult = customerFacade.updateCustomerStatus(customer);

            if (operationResult) {
                result = "customer status changed successfully";
            }
        } catch (NumberFormatException numberFormatException) {
            result = "NumberException:" + numberFormatException;
        } catch (Exception e) {
            result = "Exception:" + e;
        }
        return result;
    }

}
