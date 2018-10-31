/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import Utility.DateUtility;
import entity.BookingDTO;
import entity.CustomerDTO;
import entity.VehicleDTO;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Properties;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import session.VHSManagementRemote;
import tempBeans.customerReservations;
import tempBeans.overdueVehicles;
import tempBeans.vehicleReservations;

/**
 *
 * @author elau
 */
@Named(value = "myVHSManagedBean")
@ConversationScoped
public class MyVHSManagedBean implements Serializable {

    @EJB
    private VHSManagementRemote VHSManagement;

    @Inject
    private Conversation conversation;

    /*Customer variables*/
    private String customerId;
    private String customerName;
    private String phone;
    private String address;
    private String email;
    private String password;
    private String newPassword;
    private String confirmPassword;
    private String customerType;
    private Boolean active;
    private String oldPassword;

    /*Vehicle variables*/
    private String vehicleNumber;
    private String vehicleType;
    private String vehicleName;
    private String registrationYear;
    private String fuelType;

    /*Booking variables*/
    private String bookingId;
    private String bookingDate;
    private String bookingTime;
    private String pickupLocation;
    private String hireDuration;
    private String returnDate;
    private String returnTime;
    private String confirmReturn;

    private final int BUDGET_TIME_CONSTRAINT_MINUTES = 5 * 60;
    private final int PREMIUM_STANDARD_TIME_CONSTRAINT_MINUTES = 1 * 60;
    private final int PREMIUM_STANDARD_RESERVATION_TIME_CONSTRAINT_HOURS = 20;
    private final int BUDGET_RESERVATION_TIME_CONSTRAINT_HOURS = 15;

    /*Output variables*/
    ArrayList<VehicleDTO> showVehicles = new ArrayList<VehicleDTO>();
    String bookingResponse;
    ArrayList<customerReservations> customerReservations = new ArrayList<customerReservations>();
    ArrayList<vehicleReservations> vehicleReservations = new ArrayList<vehicleReservations>();
    ArrayList<overdueVehicles> overdueVehicles = new ArrayList<overdueVehicles>();
    ArrayList<vehicleReservations> vehicleReservationForReturn = new ArrayList<vehicleReservations>();
    ArrayList<CustomerDTO> customersList = new ArrayList<CustomerDTO>();

    /**
     * Creates a new instance of MyEmpManagedBean
     */
    public MyVHSManagedBean() {
        /*customer*/
        customerId = null;
        customerName = null;
        phone = null;
        address = null;
        email = null;
        oldPassword = null;
        password = null;
        newPassword = null;
        confirmPassword = null;
        customerType = null;
        active = false;

        /*vehicle*/
        vehicleNumber = null;
        vehicleType = null;
        vehicleName = null;
        registrationYear = null;
        fuelType = null;

        /*booking*/
        bookingId = null;
        bookingDate = null;
        bookingTime = null;
        pickupLocation = null;
        hireDuration = null;
        returnDate = null;
        returnTime = null;

        confirmReturn = "";

//        /*output*/
//        showVehicles = new ArrayList<VehicleDTO>();
//        showVehicles = new ArrayList<VehicleDTO>(Arrays.asList(new VehicleDTO("1001", "sfdf", "sfsdf", "sdfsdfsd", "fsdf")));
        bookingResponse = "";
    }

    public ArrayList<CustomerDTO> getCustomersList() {
        return customersList;
    }

    public void setCustomersList(ArrayList<CustomerDTO> customersList) {
        this.customersList = customersList;
    }

    public String getConfirmReturn() {
        return confirmReturn;
    }

    public void setConfirmReturn(String confirmReturn) {
        this.confirmReturn = confirmReturn;
    }

    public ArrayList<vehicleReservations> getVehicleReservationForReturn() {
        return vehicleReservationForReturn;
    }

    public void setVehicleReservationForReturn(ArrayList<vehicleReservations> vehicleReservationForReturn) {
        this.vehicleReservationForReturn = vehicleReservationForReturn;
    }

    public ArrayList<overdueVehicles> getOverdueVehicles() {
        return overdueVehicles;
    }

    public void setOverdueVehicles(ArrayList<overdueVehicles> overdueVehicles) {
        this.overdueVehicles = overdueVehicles;
    }

    public ArrayList<vehicleReservations> getVehicleReservations() {
        return vehicleReservations;
    }

    public void setVehicleReservations(ArrayList<vehicleReservations> vehicleReservations) {
        this.vehicleReservations = vehicleReservations;
    }

    public ArrayList<customerReservations> getCustomerReservations() {
        return customerReservations;
    }

    public void setCustomerReservations(ArrayList<customerReservations> customerReservations) {
        this.customerReservations = customerReservations;
    }

    public String getBookingResponse() {
        return bookingResponse;
    }

    public void setBookingResponse(String bookingResponse) {
        this.bookingResponse = bookingResponse;
    }

    public ArrayList<VehicleDTO> getShowVehicles() {
        return showVehicles;
    }

    public void setShowVehicles(ArrayList<VehicleDTO> showVehicles) {
        this.showVehicles = showVehicles;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getRegistrationYear() {
        return registrationYear;
    }

    public void setRegistrationYear(String registrationYear) {
        this.registrationYear = registrationYear;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getHireDuration() {
        return hireDuration;
    }

    public void setHireDuration(String hireDuration) {
        this.hireDuration = hireDuration;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getReturnTime() {
        return returnTime;
    }

    /**
     * getter setter*
     */
    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public void startConversation() {
        conversation.begin();
    }

    public void endConversation() {
        conversation.end();
    }

//    @PostConstruct
    public void showVehiclesFromDb() {

        showVehicles = VHSManagement.showAvailableVehicles();
        System.out.println("showVehicles:" + showVehicles.toString());
    }

    public void reserveVehicle() {

        String result = "";
        try {
//            showAvailableVehicles();

//            int userId = IOUtility.getInteger("Please enter User id for reservation");
            int userId = Integer.parseInt(customerId);
//            String vehicleNumber=this.vehicleNumber;

            if (VHSManagement.checkUserId(userId)) {
                CustomerDTO customer = VHSManagement.getUserInfoFromUserId(userId);

//                String vehicleNumber = IOUtility.getString("Please enter vehicle Registration number for reservation:").toUpperCase();
                if (VHSManagement.checkVehicleNumber(vehicleNumber)) {
                    VehicleDTO vehicle = VHSManagement.getVehicleInfoFromVehicleNo(vehicleNumber);

//                    LocalDate preferredDate = DateUtility.convertStringToDate(IOUtility.getString("Please enter preferred Date for vehicle reservation (in dd-MM-yyyy format)"));
                    LocalDate preferredDate = DateUtility.convertStringToDate(bookingDate);

                    long daysBetweenDates = DateUtility.daysBetweenDates(DateUtility.getCurrentDate(), preferredDate);
                    if (daysBetweenDates > 30) {
                        result = "Error: Reservation of vehicle on possible before 30 days";
                    } else if (daysBetweenDates < 0) {
//                        System.out.println("Error: Reservation for previous days not possible");
                        result = "Error: Reservation for previous days not possible";
                    } else {
//                        LocalTime preferredTime = DateUtility.convertStringToTime(IOUtility.getString("Please enter preferred time for vehicle reservation (in 24 hr HH:mm format)"));
                        LocalTime preferredTime = DateUtility.convertStringToTime(bookingTime);

                        LocalTime beforeCondition = DateUtility.convertStringToTime("04:00");
                        LocalTime afterCondition = DateUtility.convertStringToTime("00:00");
                        if (preferredTime.isBefore(beforeCondition) && preferredTime.isAfter(afterCondition)) {
                            result = "Reservation is not available between 12.00 am to 4.00 am";
                        } else {

                            LocalDateTime combinedDateTime = LocalDateTime.of(preferredDate, preferredTime);
                            long timeBetween = DateUtility.timeBetweenDateTimes(DateUtility.getCurrentDateTime(), combinedDateTime);

//                            int preferredDurationForHire = IOUtility.getInteger("Enter hours for which vehicle to be hired");
                            int preferredDurationForHire = Integer.parseInt(hireDuration);

                            if (VHSManagement.checkAvailabilityForSpecificVehicle(vehicleNumber, preferredDate, preferredTime, preferredDurationForHire)) {
                                String customerCategory = customer.getCustomerType();
                                if (customerCategory.equalsIgnoreCase("premium")
                                        || customerCategory.equalsIgnoreCase("standard")) {
                                    if (timeBetween < PREMIUM_STANDARD_TIME_CONSTRAINT_MINUTES) {
                                        result = "Reservation is not available before 1 Hour for premium/Standard customers";
                                    } else if (preferredDurationForHire > PREMIUM_STANDARD_RESERVATION_TIME_CONSTRAINT_HOURS) {
                                        result = "Reservation can not available for time more than 20 Hours for premium/Standard customers";
                                    } else {
                                        result = addReservationData(customer, vehicle, preferredDate, preferredTime, preferredDurationForHire);
                                    }
                                } else if (customerCategory.equalsIgnoreCase("budget")) {
                                    if (timeBetween < BUDGET_TIME_CONSTRAINT_MINUTES) {
                                        result = "Reservation is not available before 5 Hours for budget customers";
                                    } else if (preferredDurationForHire > BUDGET_RESERVATION_TIME_CONSTRAINT_HOURS) {
                                        result = "Reservation can not available for time more than 15 Hours for budget customers";
                                    } else {
                                        result = addReservationData(customer, vehicle, preferredDate, preferredTime, preferredDurationForHire);
                                    }
                                }
                            } else {
                                result = "This vehicle is not available for given Date and Time..Please choose another date and time for vehicle";
                            }
                        }
                    }
                } else {
                    result = "Vehicle Number not exist..Please try again";
                }
            } else {
                result = "UserId not exist..Please try again";
            }
        } catch (NumberFormatException e) {
            result = "Exception:" + e + " Customer id must be a number";
        } catch (ParseException e) {
            result = "Exception:" + e + "Error validating booking date and booking time";
        }
        bookingResponse = result;
    }

    private String addReservationData(CustomerDTO customer, VehicleDTO vehicle, LocalDate preferredDate, LocalTime preferredTime, int preferredDurationForHire) {
        String response = "";
        try {
            String pickLocation = pickupLocation;
            boolean result = VHSManagement.AddNeReservation(preferredDate, preferredTime, pickLocation, preferredDurationForHire, customer, vehicle);
            if (result) {
//                vhsService.updateVehicleStatus(vehicle, false);
                response = "Booking has been done successfully";
            } else {
                response = "error occurred while booking..try again..!!!";
            }
        } catch (Exception e) {
            response = "Exception: " + e;
        }
        return response;
    }

    public void showCustomerReservations() {

        String resultCustReservation = "";
        try {
//            int userId = IOUtility.getInteger("Enter the customerId to check reservations");
            int userId = Integer.parseInt(customerId);
            if (VHSManagement.checkUserId(userId)) {
                ArrayList<BookingDTO> result = VHSManagement.getAllHireRecordForCustomer(userId);

                if (!result.isEmpty()) {
//                    System.out.println("Reservation data for customer ID " + userId);
//                    System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
//                    System.out.format("%-24s%-24s%-24s%-32s%-32s", "Date of Reservation", "Reservation Time", "Pickup Location", "Reservation Duration", "Vehicle details");
//                    System.out.println();
//                    System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                    for (BookingDTO data : result) {

                        VehicleDTO tempVehicleDTO = VHSManagement.getVehicleInfoFromVehicleNo(data.getVehicleNumber());

                        customerReservations tempCustReservations = new customerReservations(data.getBookingDate(), data.getBookingTime(),
                                data.getPickupLocation(), data.getHireDuration() + " Hrs", tempVehicleDTO.getVehicleNumber() + " - " + tempVehicleDTO.getVehicleName());

//                        System.out.format("%-24s%-24s%-24s%-32s%-32s", data.getBookingDate(), data.getBookingTime(),
//                                data.getPickupLocation(), data.getHireDuration() + " Hrs", tempVehicleDTO.getVehicleNumber() + " - " + tempVehicleDTO.getVehicleName());
//                        System.out.println();
                        customerReservations.add(tempCustReservations);
                    }
                } else {
                    resultCustReservation = "No vehicle hiring data available for the given customer";
                }
            } else {
                resultCustReservation = "Given userId not exist in the system";
            }
        } catch (NumberFormatException e) {
            resultCustReservation = "Exception: " + e + " Customer id must be integer";
        }
        bookingResponse = resultCustReservation;
    }

    public void showVehicleReservations() {

        String response = "";

        try {
//            String vehicleNumber = IOUtility.getString("Enter the vehicle number to check reservations").toUpperCase();
            if (VHSManagement.checkVehicleNumber(vehicleNumber)) {
                ArrayList<BookingDTO> result = VHSManagement.getAllHireRecordForVehicle(vehicleNumber);

                if (!result.isEmpty()) {
//                    System.out.println("Reservation data for vehicle number " + vehicleNumber);
//                    System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
//                    System.out.format("%-24s%-24s%-24s%-24s%-32s%-32s", "Date of Reservation", "Reservation Time", "Pickup Location", "Reservation Duration", "Vehicle details", "Customer details");
//                    System.out.println();
//                    System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                    for (BookingDTO data : result) {

                        VehicleDTO tempVehicleDTO = VHSManagement.getVehicleInfoFromVehicleNo(data.getVehicleNumber());
                        CustomerDTO tempCustomerDTO = VHSManagement.getUserInfoFromUserId(Integer.parseInt(data.getCustomerId()));

                        vehicleReservations tempReservations = new vehicleReservations(data.getBookingDate(), data.getBookingTime(), data.getPickupLocation(),
                                data.getHireDuration() + " Hrs", tempVehicleDTO.getVehicleNumber() + " - " + tempVehicleDTO.getVehicleName(),
                                tempCustomerDTO.getCustomerId() + " - " + tempCustomerDTO.getCustomerName() + " - " + tempCustomerDTO.getCustomerType());

                        vehicleReservations.add(tempReservations);

//                        System.out.format("%-24s%-24s%-24s%-24s%-32s%-32s", data.getBookingDate(), data.getBookingTime(), data.getPickupLocation(),
//                                data.getHireDuration() + " Hrs", tempVehicleDTO.getVehicleNumber() + " - " + tempVehicleDTO.getVehicleName(),
//                                tempCustomerDTO.getCustomerId() + " - " + tempCustomerDTO.getCustomerName() + " - " + tempCustomerDTO.getCustomerType());
//                        System.out.println();
                    }
                } else {
                    response = "No Hiring data available for this vehicle";
                }
            } else {
                response = "Given vehicle number not exist in the system";
            }
        } catch (NumberFormatException e) {
            response = "Number format Exception: " + e;
        } catch (Exception ex) {
            response = "exception:" + ex;
        }
        bookingResponse = response;
    }

    public void showNotReturnedVehicles() {
        String response = "";
        try {
            ArrayList<BookingDTO> dueVehicles = VHSManagement.getOverDueVehicles();
//            System.out.println("Due vehicles (Which have not returned yet) List:");
//            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
//            System.out.format("%-24s%-24s%-24s%-24s%-24s%-32s%-32s", "Booking id", "Date of Reservation", "Reservation Time", "Pickup Location", "Reservation Duration", "Vehicle details", "Customer details");
//            System.out.println();
//            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            for (BookingDTO data : dueVehicles) {

                VehicleDTO tempVehicleDTO = VHSManagement.getVehicleInfoFromVehicleNo(data.getVehicleNumber());
                CustomerDTO tempCustomerDTO = VHSManagement.getUserInfoFromUserId(Integer.parseInt(data.getCustomerId()));

                overdueVehicles tempVehicles = new overdueVehicles(data.getBookingId(), data.getBookingDate(), data.getBookingTime(), data.getPickupLocation(),
                        data.getHireDuration() + " Hrs", tempVehicleDTO.getVehicleNumber() + " - " + tempVehicleDTO.getVehicleName(),
                        tempCustomerDTO.getCustomerId() + " - " + tempCustomerDTO.getCustomerName() + " - " + tempCustomerDTO.getCustomerType());

                overdueVehicles.add(tempVehicles);
//                System.out.format("%-24s,%-24s%-24s%-24s%-24s%-32s%-32s", data.getBookingId(), data.getBookingDate(), data.getBookingTime(), data.getPickupLocation(),
//                        data.getHireDuration() + " Hrs", tempVehicleDTO.getVehicleNumber() + " - " + tempVehicleDTO.getVehicleName(),
//                        tempCustomerDTO.getCustomerId() + " - " + tempCustomerDTO.getCustomerName() + " - " + tempCustomerDTO.getCustomerType());
//                System.out.println();
            }
//            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        } catch (Exception e) {
            response = "Exception: " + e;
//            System.out.println("---------------------------------------------------------------------------------------------");
        }
        bookingResponse = response;
    }

    public void returnVehicle() {
        String returnVehicleResult = "";

        try {

//            int customerId = IOUtility.getInteger("Please enter customer ID");
            if (VHSManagement.checkUserId(Integer.parseInt(customerId))) {
//                    LocalDate hiredDate = DateUtility.convertStringToDate(IOUtility.getString("Enter hired date for the vehicle (enter in dd-MM-yyyy format)"));
//                    LocalTime hiredTime = DateUtility.convertStringToTime(IOUtility.getString("Enter hired time for the vehicle (enter in HH:mm 24 Hr format)"));

                String vehicleBookingId = bookingId;

//                    Reservation reservation = vhsService.findReservedVehicle(customerId, vehicleNumber, hiredDate, hiredTime);
                BookingDTO reservation = VHSManagement.findReservedVehicle(Integer.parseInt(customerId), vehicleBookingId);

                LocalDate hiredDate = LocalDate.parse(reservation.getBookingDate());
                LocalTime hiredTime = LocalTime.parse(reservation.getBookingTime());
                LocalDateTime combinedDateTime = LocalDateTime.of(hiredDate, hiredTime);

                VehicleDTO tempVehicleDTO = VHSManagement.getVehicleInfoFromVehicleNo(reservation.getVehicleNumber());
                CustomerDTO tempCustomerDTO = VHSManagement.getUserInfoFromUserId(Integer.parseInt(reservation.getCustomerId()));

                vehicleReservations tempVehicleReservations = new vehicleReservations(reservation.getBookingDate(), reservation.getBookingTime(), reservation.getPickupLocation(),
                        reservation.getHireDuration() + " Hrs", tempVehicleDTO.getVehicleNumber() + " - " + tempVehicleDTO.getVehicleName(),
                        tempCustomerDTO.getCustomerName() + " - " + tempCustomerDTO.getCustomerType());

                vehicleReservationForReturn.add(tempVehicleReservations);

//                System.out.println("Reservation details:");
//                System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
//                System.out.format("%-24s%-24s%-24s%-24s%-32s%-32s", "Date of Reservation", "Reservation Time", "Pickup Location", "Reservation Duration", "Vehicle details", "Customer details");
//                System.out.println();
//                System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
//                System.out.format("%-24s%-24s%-24s%-24s%-32s%-32s", reservation.getBookingDate(), reservation.getBookingTime(), reservation.getPickupLocation(),
//                        reservation.getHireDuration() + " Hrs", tempVehicleDTO.getVehicleNumber() + " - " + tempVehicleDTO.getVehicleName(),
//                        tempCustomerDTO.getCustomerName() + " - " + tempCustomerDTO.getCustomerType());
//                System.out.println();
//                System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                // can be used as returning date and time can be taken manually
                //                    LocalDate returningDate = DateUtility.convertStringToDate(IOUtility.getString("Please enter returning date (enter in dd-MM-yyyy format)"));
                //                    LocalTime returningTime = DateUtility.convertStringToTime(IOUtility.getString("Please enter returning time (enter in HH:mm 24 Hr format)"));
                // used as returning date and time can be taken automatically from the system
//                String confirm = IOUtility.getString("confirm returning shown vehicle? (type y/n)");
                String confirm = confirmReturn;
                if (confirm.equalsIgnoreCase("y") || confirm.equalsIgnoreCase("yes")) {
                    String chargesForHire = calculateChargesForHire(combinedDateTime, reservation);

                    returnVehicleResult = "Vehicle has been returned. Charges for hiring vehicle are : $" + chargesForHire;
//                    System.out.println("---------------------------------------------------------------------------------------------");
                } else if (confirm.equalsIgnoreCase("n") || confirm.equalsIgnoreCase("no")) {
                    returnVehicleResult = "Returning vehicle cancelled by User";
                } else {
                    returnVehicleResult = "Invalid selection...Please choose y/n for returning details";
                }
            } else {
                returnVehicleResult = "Given customer Id not found..try again..!!!";
            }
        } catch (Exception e) {
            returnVehicleResult = "Exception: " + e;
        }
        bookingResponse = returnVehicleResult;
    }

    String calculateChargesForHire(LocalDateTime combinedDateTime, BookingDTO reservation) throws IOException {
        LocalDate returningDate = DateUtility.getCurrentDate();
        LocalTime returningTime = DateUtility.getCurrentTime();

        LocalDateTime combinedReturnDateTime = LocalDateTime.of(returningDate, returningTime);

        int reservedTime = Integer.parseInt(reservation.getHireDuration().trim());
        Long timeBetween = DateUtility.hoursBetweenDateTimes(combinedDateTime, combinedReturnDateTime);

        String chargesForHire;
        chargesForHire = getChargesForHire(reservation, returningDate, returningTime, reservedTime, timeBetween);
        return chargesForHire;
    }

    private String getChargesForHire(BookingDTO reservation, LocalDate returningDate, LocalTime returningTime, int reservedTime, Long timeBetween) throws IOException {
        String chargesForHire = "";
        try {
            if (timeBetween <= reservedTime) {
                //right time
                chargesForHire = VHSManagement.returnVehicleAndCalculateCharges(0L, reservedTime, reservation, returningDate, returningTime) + " Without any penalty (right time delivery)";
            } else if (timeBetween == (reservedTime + 1)) {         // no charges for up to exceeding one hour
                //luck time
                chargesForHire = VHSManagement.returnVehicleAndCalculateCharges(0L, reservedTime, reservation, returningDate, returningTime) + " Without any penalty (one hour grace period)";
            } else {                                                    // penalty extra time = time elapsed - reserved time
                // penalty
                chargesForHire = VHSManagement.returnVehicleAndCalculateCharges(timeBetween - (reservedTime + 1), reservedTime, reservation, returningDate, returningTime) + " With penalty charges";

            }
        } catch (Exception e) {
            chargesForHire = "Exception occurred:" + e;
        }
        return chargesForHire;
    }

    public void newRegisterRequestCustomer() {
        String result = "";
        try {
            boolean status = VHSManagement.addNewCustomerRequestCustomer(customerName, phone, address, email, password, customerType);
            if (status) {
                result = "registration request has been sent to the operator. Please wait for approval..Thank you..!!";
            } else {
                result = "problem while registration..Please try again later..!!";
            }

        } catch (Exception e) {
            result = "exception occurred:" + e;
        }
        bookingResponse = result;
    }

    public void validatePasswordPair(FacesContext context,
            UIComponent componentToValidate,
            Object pwdValue) throws ValidatorException {

        // get password
        String pwd = (String) pwdValue;

        // get confirm password
        UIInput cnfPwdComponent = (UIInput) componentToValidate.getAttributes().get("cnfpwd");
        String cnfPwd = (String) cnfPwdComponent.getSubmittedValue();

        System.out.println("password : " + pwd);
        System.out.println("confirm password : " + cnfPwd);

        if (!pwd.equals(cnfPwd)) {
            FacesMessage message = new FacesMessage(
                    "Password and Confirm Password are not the same! They must be the same.");
            throw new ValidatorException(message);
        }
    }

    public void fetchAllPendingRequests() {
        customersList = VHSManagement.findAllPendingCustomerRequests();
    }

    
    private static final String mailSender = "sourabhkolekar18@gmail.com";
    private static final String mailPass = "sourabh@google18";
    private static final String mailSubject = "User account activated for VHS service";
    private static final String mailBody = "Hello user..Your account has been set to active. You can now communicate to operator regarding vehicle hiring..!!";
    
    public void approvePendingCustomers() {
        String operationresult = "";
        try {
            operationresult = VHSManagement.approvePendingRequest(customerId);
            if(operationresult.equalsIgnoreCase("customer status changed successfully"))
            {
//               operationresult= sendMail(mailSender, mailPass, email.trim(), mailSubject, mailBody);
            }
        } catch (Exception e) {
         
            operationresult="exce:"+e;
        }
        bookingResponse=operationresult;
    }
    
    
    
    

    private static String sendMail(String from, String pass, String to, String subject, String body) {
        
        String response="";
        Properties properties = System.getProperties();
        String host = "smtp.gmail.com";
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.user", from);
        properties.put("mail.smtp.password", pass);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(properties);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress toAddress = new InternetAddress(to);

//            // To get the array of addresses
//            for (int i = 0; i < to.length; i++) {
//                toAddress[i] = new InternetAddress(to[i]);
//            }
//
//            for (int i = 0; i < toAddress.length; i++) {
//                }
            message.addRecipient(Message.RecipientType.TO, toAddress);

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (AddressException ae) {
            response="addressException:"+ae;
        } catch (MessagingException me) {
            response="messageException:"+me;
        }
        return response;
    }

    

//    public String addEmployee() {
//
//        // check empId is null
//        if (isNull(empId)) {
//            return "debug";
//        }
//
//        // all information seems to be valid
//        // try add the employee
//        EmployeeDTO empDTO = new EmployeeDTO(empId, name, phone,
//                address, email, password, appGroup, bnkAccId, salary, active);
//        boolean result = employeeManagement.addEmployee(empDTO);
//        if (result) {
//            return "success";
//        } else {
//            return "failure";
//        }
//    }
//
//    public String setEmployeeDetailsForChange() {
//        // check empId is null
//        if (isNull(empId) || conversation == null) {
//            return "debug";
//        }
//
//        if (!employeeManagement.hasEmployee(empId)) {
//            return "failure";
//        }
//
//        // note the startConversation of the conversation
//        startConversation();
//
//        // get employee details
//        return setEmployeeDetails();
//    }
//
//    public String changeEmployee() {
//        // check empId is null
//        if (isNull(empId)) {
//            return "debug";
//        }
//
//        EmployeeDTO empDTO = new EmployeeDTO(empId, name, phone,
//                address, email, password, appGroup, bnkAccId, salary, active);
//        boolean result = employeeManagement.updateEmpolyeeDetails(empDTO);
//
//        // note the endConversation of the conversation
//        endConversation();
//
//        if (result) {
//            return "success";
//        } else {
//            return "failure";
//        }
//    }
//
//    public void validateNewPassword(FacesContext context,
//            UIComponent componentToValidate, Object value)
//            throws ValidatorException {
//
//        // get new password
//        String oldPwd = (String) value;
//
//        // get old password
//        UIInput newPwdComponent = (UIInput) componentToValidate.getAttributes().get("newpwd");
//        String newPwd = (String) newPwdComponent.getSubmittedValue();
//
//        if (oldPwd.equals(newPwd)) {
//            FacesMessage message = new FacesMessage(
//                    "Old Password and New Password are the same! They must be different.");
//            throw new ValidatorException(message);
//        }
//
//    }
//
//    public void validatePasswordPair(FacesContext context,
//            UIComponent componentToValidate,
//            Object pwdValue) throws ValidatorException {
//
//        // get password
//        String pwd = (String) pwdValue;
//
//        // get confirm password
//        UIInput cnfPwdComponent = (UIInput) componentToValidate.getAttributes().get("cnfpwd");
//        String cnfPwd = (String) cnfPwdComponent.getSubmittedValue();
//
//        System.out.println("password : " + pwd);
//        System.out.println("confirm password : " + cnfPwd);
//
//        if (!pwd.equals(cnfPwd)) {
//            FacesMessage message = new FacesMessage(
//                    "Password and Confirm Password are not the same! They must be the same.");
//            throw new ValidatorException(message);
//        }
//    }
//
//    public void validateNewPasswordPair(FacesContext context,
//            UIComponent componentToValidate,
//            Object newValue) throws ValidatorException {
//
//        // get new password
//        String newPwd = (String) newValue;
//
//        // get confirm password
//        UIInput cnfPwdComponent = (UIInput) componentToValidate.getAttributes().get("cnfpwd");
//        String cnfPwd = (String) cnfPwdComponent.getSubmittedValue();
//
//        System.out.println("new password : " + newPwd);
//        System.out.println("confirm password : " + cnfPwd);
//
//        if (!newPwd.equals(cnfPwd)) {
//            FacesMessage message = new FacesMessage(
//                    "New Password and Confirm New Password are not the same! They must be the same.");
//            throw new ValidatorException(message);
//        }
//    }
//
//    public String changeEmployeePassword() {
//        // check empId is null
//        if (isNull(empId)) {
//            return "debug";
//        }
//
//        // newPassword and confirmPassword are the same - checked by the validator during input to JSF form
//        boolean result = employeeManagement.updateEmployeePassword(empId, newPassword, oldPassword);
//
//        System.out.println("result = " + result);
//
//        if (result) {
//            return "success";
//        } else {
//            return "failure";
//        }
//    }
//
//    public String deleteEmployee() {
//        // check empId is null
//        if (isNull(empId)) {
//            return "debug";
//        }
//
//        boolean result = employeeManagement.deleteEmployee(empId);
//
//        if (result) {
//            return "success";
//        } else {
//            return "failure";
//        }
//
//    }
//
//    public String displayEmployee() {
//        // check empId is null
//        if (isNull(empId) || conversation == null) {
//            return "debug";
//        }
//
//        return setEmployeeDetails();
//    }
//
//    private boolean isNull(String s) {
//        return (s == null);
//    }
//
//    private String setEmployeeDetails() {
//
//        if (isNull(empId) || conversation == null) {
//            return "debug";
//        }
//
//        EmployeeDTO e = employeeManagement.getEmployeeDetails(empId);
//
//        if (e == null) {
//            // no such employee
//            return "failure";
//        } else {
//            // found - set details for display
//            this.empId = e.getEmpid();
//            this.name = e.getName();
//            this.phone = e.getPhone();
//            this.address = e.getAddress();
//            this.email = e.getEmail();
//            this.password = e.getPassword();
//            this.appGroup = e.getAppGroup();
//            this.bnkAccId = e.getBnkAccId();
//            this.salary = e.getSalary();
//            this.active = e.isActive();
//            return "success";
//        }
//    }
//
//    private boolean validAddEmployeeInfo() {
//        return (empId != null && name != null && phone != null && address != null
//                && email != null && password != null && appGroup != null
//                && bnkAccId != null && salary > 0.0);
//    }
}
