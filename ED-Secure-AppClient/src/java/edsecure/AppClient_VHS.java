/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edsecure;

import Utility.CommonFunctions;
import Utility.DateUtility;
import Utility.IOUtility;
import entity.BookingDTO;
import entity.CustomerDTO;
import entity.VehicleDTO;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.ejb.EJB;
import session.VHSManagementRemote;

/**
 *
 * @author elau
 */
public class AppClient_VHS {

    @EJB
    private static VHSManagementRemote VHSManagement;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            AppClient_VHS client = new AppClient_VHS();
            client.run();
        } catch (Exception e) {
            System.out.println("global exception: " + e);
        }

    }

    void run() {
        while (true) {
            switch (consoleMenu()) {
                case 1:
                    showAvailableVehicles();
                    break;
                case 2:
                    reserveVehicle();
                    break;
                case 3:
                    showCustomerReservations();
                    break;
                case 4:
                    showVehicleReservations();
                    break;
                case 5:
                    showNotReturnedVehicles();
                    break;
                case 6:
                    returnVehicle();
                    break;
                case 7:
                    System.out.println("Thank you for using Vehicle Hiring System");
                    return;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }

    private int consoleMenu() {
        System.out.println("1) Show vehicles");
        System.out.println("2) Reserve a vehicle for a customer");
        System.out.println("3) Show hires for a customer");
        System.out.println("4) Show hires for a vehicle");
        System.out.println("5) Show not returned (overdue) vehicles");
        System.out.println("6) Return vehicle from hire");
        System.out.println("7) Exit");
        return IOUtility.getInteger("Select option:");
    }

    void showAvailableVehicles() {
        try {
            ArrayList<VehicleDTO> vehicleResult = VHSManagement.showAvailableVehicles();
            System.out.println("List of vehicles:");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.format("%-16s%-32s%-24s%-24s%-16s", "type", "Registration number", "Model Name", "Year of Registration", "Fuel Type");
            System.out.println();
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            for (VehicleDTO vehicle : vehicleResult) {

                System.out.format("%-16s%-32s%-24s%-24s%-16s", vehicle.getVehicleType(), vehicle.getVehicleNumber(), vehicle.getVehicleName(),
                        vehicle.getRegistrationYear(), vehicle.getFuelType());
                System.out.println();
            }
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            System.out.println("---------------------------------------------------------------------------------------------");
        }
    }

    void reserveVehicle() {

        try {
            showAvailableVehicles();

            int userId = IOUtility.getInteger("Please enter User id for reservation");

            if (VHSManagement.checkUserId(userId)) {
                CustomerDTO customer = VHSManagement.getUserInfoFromUserId(userId);

                String vehicleNumber = IOUtility.getString("Please enter vehicle Registration number for reservation:").toUpperCase();

                if (VHSManagement.checkVehicleNumber(vehicleNumber)) {
                    VehicleDTO vehicle = VHSManagement.getVehicleInfoFromVehicleNo(vehicleNumber);

                    LocalDate preferredDate = DateUtility.convertStringToDate(IOUtility.
                            getString("Please enter preferred Date for vehicle reservation (in dd-MM-yyyy format)"));

                    long daysBetweenDates = DateUtility.daysBetweenDates(DateUtility.getCurrentDate(), preferredDate);
                    if (daysBetweenDates > 30) {
                        System.out.println("Error: Reservation of vehicle on possible before 30 days");
                    } else if (daysBetweenDates < 0) {
                        System.out.println("Error: Reservation for previous days not possible");
                    } else {
                        LocalTime preferredTime = DateUtility.convertStringToTime(IOUtility.
                                getString("Please enter preferred time for vehicle reservation (in 24 hr HH:mm format)"));

                        LocalTime beforeCondition = DateUtility.convertStringToTime("04:00");
                        LocalTime afterCondition = DateUtility.convertStringToTime("00:00");
                        if (preferredTime.isBefore(beforeCondition) && preferredTime.isAfter(afterCondition)) {
                            System.out.println("Reservation is not available between 12.00 am to 4.00 am");
                        } else {

                            LocalDateTime combinedDateTime = LocalDateTime.of(preferredDate, preferredTime);
                            long timeBetween = DateUtility.timeBetweenDateTimes(DateUtility.getCurrentDateTime(), combinedDateTime);

                            int preferredDurationForHire = IOUtility.getInteger("Enter hours for which vehicle to be hired");

                            if (VHSManagement.checkAvailabilityForSpecificVehicle(vehicleNumber, preferredDate, preferredTime, preferredDurationForHire)) {
                                String customerCategory = customer.getCustomerType();
                                if (customerCategory.equalsIgnoreCase(CommonFunctions.premium)
                                        || customerCategory.equalsIgnoreCase(CommonFunctions.standard)) {
                                    if (timeBetween < CommonFunctions.PREMIUM_STANDARD_TIME_CONSTRAINT_MINUTES) {
                                        System.out.println("Reservation is not available before 1 Hour for premium/Standard customers");
                                    } else if (preferredDurationForHire > CommonFunctions.PREMIUM_STANDARD_RESERVATION_TIME_CONSTRAINT_HOURS) {
                                        System.out.println("Reservation can not available for time more than 20 Hours for premium/Standard customers");
                                    } else {
                                        addReservationData(customer, vehicle, preferredDate, preferredTime, preferredDurationForHire);
                                    }
                                } else if (customerCategory.equalsIgnoreCase(CommonFunctions.budget)) {
                                    if (timeBetween < CommonFunctions.BUDGET_TIME_CONSTRAINT_MINUTES) {
                                        System.out.println("Reservation is not available before 5 Hours for budget customers");
                                    } else if (preferredDurationForHire > CommonFunctions.BUDGET_RESERVATION_TIME_CONSTRAINT_HOURS) {
                                        System.out.println("Reservation can not available for time more than 15 Hours for budget customers");
                                    } else {
                                        addReservationData(customer, vehicle, preferredDate, preferredTime, preferredDurationForHire);
                                    }
                                }
                            } else {
                                System.out.println("This vehicle is not available for given Date and Time..Please choose another date and time for vehicle");
                            }
                        }
                    }
                } else {
                    System.out.println("Vehicle Number not exist..Please try again");
                }
            } else {
                System.out.println("UserId not exist..Please try again");
            }
        } catch (Exception e) {
            System.out.println("Exception:" + e);
            System.out.println("---------------------------------------------------------------------------------------------");
        }
    }

    private void addReservationData(CustomerDTO customer, VehicleDTO vehicle, LocalDate preferredDate, LocalTime preferredTime, int preferredDurationForHire) {
        try {
            String pickUpLocation = IOUtility.getString("Please enter pickup location");
            boolean result = VHSManagement.AddNeReservation(preferredDate, preferredTime, pickUpLocation, preferredDurationForHire, customer, vehicle);
            if (result) {
//                vhsService.updateVehicleStatus(vehicle, false);
                System.out.println("Booking has been done successfully");
            } else {
                System.out.println("error occurred while booking..try again..!!!");
            }
            System.out.println("--------------------------------------------------------------------------------------------------------------------------");
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            System.out.println("---------------------------------------------------------------------------------------------");
        }
    }

    void showCustomerReservations() {

        try {
            int userId = IOUtility.getInteger("Enter the customerId to check reservations");
            if (VHSManagement.checkUserId(userId)) {
                ArrayList<BookingDTO> result = VHSManagement.getAllHireRecordForCustomer(userId);

                if (!result.isEmpty()) {
                    System.out.println("Reservation data for customer ID " + userId);
                    System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    System.out.format("%-24s%-24s%-24s%-32s%-32s", "Date of Reservation", "Reservation Time", "Pickup Location", "Reservation Duration", "Vehicle details");
                    System.out.println();
                    System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                    for (BookingDTO data : result) {

                        VehicleDTO tempVehicleDTO = VHSManagement.getVehicleInfoFromVehicleNo(data.getVehicleNumber());
                        System.out.format("%-24s%-24s%-24s%-32s%-32s", data.getBookingDate(), data.getBookingTime(),
                                data.getPickupLocation(), data.getHireDuration() + " Hrs", tempVehicleDTO.getVehicleNumber() + " - " + tempVehicleDTO.getVehicleName());
                        System.out.println();
                    }
                } else {
                    System.out.println("No vehicle hiring data available for the given customer");
                }
                System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            } else {
                System.out.println("Given userId not exist in the system");
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            System.out.println("---------------------------------------------------------------------------------------------");
        }
    }

    void showVehicleReservations() {
        try {
            String vehicleNumber = IOUtility.getString("Enter the vehicle number to check reservations").toUpperCase();
            if (VHSManagement.checkVehicleNumber(vehicleNumber)) {
                ArrayList<BookingDTO> result = VHSManagement.getAllHireRecordForVehicle(vehicleNumber);

                if (!result.isEmpty()) {
                    System.out.println("Reservation data for vehicle number " + vehicleNumber);
                    System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    System.out.format("%-24s%-24s%-24s%-24s%-32s%-32s", "Date of Reservation", "Reservation Time", "Pickup Location", "Reservation Duration", "Vehicle details", "Customer details");
                    System.out.println();
                    System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                    for (BookingDTO data : result) {

                        VehicleDTO tempVehicleDTO = VHSManagement.getVehicleInfoFromVehicleNo(data.getVehicleNumber());
                        CustomerDTO tempCustomerDTO = VHSManagement.getUserInfoFromUserId(Integer.parseInt(data.getCustomerId()));

                        System.out.format("%-24s%-24s%-24s%-24s%-32s%-32s", data.getBookingDate(), data.getBookingTime(), data.getPickupLocation(),
                                data.getHireDuration() + " Hrs", tempVehicleDTO.getVehicleNumber() + " - " + tempVehicleDTO.getVehicleName(),
                                tempCustomerDTO.getCustomerId() + " - " + tempCustomerDTO.getCustomerName() + " - " + tempCustomerDTO.getCustomerType());
                        System.out.println();
                    }
                } else {
                    System.out.println("No Hiring data available for this vehicle");
                }
                System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            } else {
                System.out.println("Given vehicle number not exist in the system");
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            System.out.println("---------------------------------------------------------------------------------------------");

        }
    }

    void showNotReturnedVehicles() {

        try {
            ArrayList<BookingDTO> dueVehicles = VHSManagement.getOverDueVehicles();
            System.out.println("Due vehicles (Which have not returned yet) List:");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.format("%-24s%-24s%-24s%-24s%-24s%-32s%-32s", "Booking id", "Date of Reservation", "Reservation Time", "Pickup Location", "Reservation Duration", "Vehicle details", "Customer details");
            System.out.println();
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            for (BookingDTO data : dueVehicles) {

                VehicleDTO tempVehicleDTO = VHSManagement.getVehicleInfoFromVehicleNo(data.getVehicleNumber());
                CustomerDTO tempCustomerDTO = VHSManagement.getUserInfoFromUserId(Integer.parseInt(data.getCustomerId()));

                System.out.format("%-24s,%-24s%-24s%-24s%-24s%-32s%-32s", data.getBookingId(), data.getBookingDate(), data.getBookingTime(), data.getPickupLocation(),
                        data.getHireDuration() + " Hrs", tempVehicleDTO.getVehicleNumber() + " - " + tempVehicleDTO.getVehicleName(),
                        tempCustomerDTO.getCustomerId() + " - " + tempCustomerDTO.getCustomerName() + " - " + tempCustomerDTO.getCustomerType());
                System.out.println();
            }
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            System.out.println("---------------------------------------------------------------------------------------------");
        }

    }

    void returnVehicle() {
        try {
            int customerId = IOUtility.getInteger("Please enter customer ID");
            if (VHSManagement.checkUserId(customerId)) {
//                    LocalDate hiredDate = DateUtility.convertStringToDate(IOUtility.getString("Enter hired date for the vehicle (enter in dd-MM-yyyy format)"));
//                    LocalTime hiredTime = DateUtility.convertStringToTime(IOUtility.getString("Enter hired time for the vehicle (enter in HH:mm 24 Hr format)"));

                String vehicleBookingId = IOUtility.getString("Please enter booking id");

//                    Reservation reservation = vhsService.findReservedVehicle(customerId, vehicleNumber, hiredDate, hiredTime);
                BookingDTO reservation = VHSManagement.findReservedVehicle(customerId, vehicleBookingId);

                LocalDate hiredDate = LocalDate.parse(reservation.getBookingDate());
                LocalTime hiredTime = LocalTime.parse(reservation.getBookingTime());
                LocalDateTime combinedDateTime = LocalDateTime.of(hiredDate, hiredTime);

                VehicleDTO tempVehicleDTO = VHSManagement.getVehicleInfoFromVehicleNo(reservation.getVehicleNumber());
                CustomerDTO tempCustomerDTO = VHSManagement.getUserInfoFromUserId(Integer.parseInt(reservation.getCustomerId()));

                System.out.println("Reservation details:");
                System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.format("%-24s%-24s%-24s%-24s%-32s%-32s", "Date of Reservation", "Reservation Time", "Pickup Location", "Reservation Duration", "Vehicle details", "Customer details");
                System.out.println();
                System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.format("%-24s%-24s%-24s%-24s%-32s%-32s", reservation.getBookingDate(), reservation.getBookingTime(), reservation.getPickupLocation(),
                        reservation.getHireDuration() + " Hrs", tempVehicleDTO.getVehicleNumber() + " - " + tempVehicleDTO.getVehicleName(),
                        tempCustomerDTO.getCustomerName() + " - " + tempCustomerDTO.getCustomerType());
                System.out.println();
                System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                // can be used as returning date and time can be taken manually
                //                    LocalDate returningDate = DateUtility.convertStringToDate(IOUtility.getString("Please enter returning date (enter in dd-MM-yyyy format)"));
                //                    LocalTime returningTime = DateUtility.convertStringToTime(IOUtility.getString("Please enter returning time (enter in HH:mm 24 Hr format)"));
                // used as returning date and time can be taken automatically from the system
                String confirm = IOUtility.getString("confirm returning shown vehicle? (type y/n)");
                if (confirm.equalsIgnoreCase("y")) {
                    String chargesForHire = calculateChargesForHire(combinedDateTime, reservation);

                    System.out.println("Vehicle has been returned. Charges for hiring vehicle are : $" + chargesForHire);
                    System.out.println("Thank you for using the VHS");
                    System.out.println("---------------------------------------------------------------------------------------------");
                } else if (confirm.equalsIgnoreCase("n")) {
                    System.out.println("Returning vehicle cancelled by User");
                } else {
                    System.out.println("Invalid selection...Please choose y/n for returning details");
                }
            } else {
                System.out.println("Given customer Id not found..try again..!!!");
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            System.out.println("---------------------------------------------------------------------------------------------");
        }
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
            System.out.println("Exception occurred:" + e);
        }
        return chargesForHire;
    }
    
}


