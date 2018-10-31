package Utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class used for maintaining frequently used functions and static final declarations.
 *
 * @author Sourabh Kolekar
 */
public class CommonFunctions {

    public static final String COMPANY_NAME = "Demo Company";
    public static final int BUDGET_TIME_CONSTRAINT_MINUTES = 5 * 60;
    public static final int PREMIUM_STANDARD_TIME_CONSTRAINT_MINUTES = 1 * 60;
    public static final int PREMIUM_STANDARD_RESERVATION_TIME_CONSTRAINT_HOURS = 20;
    public static final int BUDGET_RESERVATION_TIME_CONSTRAINT_HOURS = 15;
    public static final String premium = "Premium";
    public static final String budget = "Budget";
    public static final String standard = "Standard";
    public static final String FILE_CUSTOMERS_TXT = "Customers.txt";
    public static final String FILE_VEHICLES_TXT = "Vehicles.txt";
    public static final String FILE_BOOKING_TXT = "booking.txt";
    public static final String FILE_ERROR_LOGGER_TXT = "ErrorLog.txt";
    private static final String PATTERN_DD_MM_YYYY = "dd-MM-yyyy";


    public static final String EXCEPTION_ALERT = "Exception Alert";
    public static final String RETURN_VEHICLE = "Return Vehicle";
    public static final String CHECK_VEHICLE_AVAILABILITY = "Check Availability";
    public static final String CHECK_HIRES = "Check Hires";
    public static final String AVAILABLE_VEHICLES = "vehicles";
    public static final String RESERVE_VEHICLE = "Reserve Vehicle";
    public static final String HIRES_FOR_CUSTOMER = "Hires for customer";
    public static final String HIRES_FOR_VEHICLE = "Hires for vehicle";
    public static final String OVERDUE_VEHICLES = "Overdue vehicles";
    public static final String CONFIRM_RESERVATION = "Confirm  Reservation";
    public static final String RETURN_VEHICLES = "Return vehicles";
    public static final String[] columnsAvailableVehicles = {"type", "Registration number", "Model Name", "Year of Registration", "Fuel Type", "Other details"};
    public static final String[] columnsCustomerHires = {"Date of Reservation", "Reservation Time", "Pickup Location", "Reservation Duration", "Vehicle details"};
    public static final String[] columnVehicleHires = {"Booking Id","Date of Reservation", "Reservation Time", "Pickup Location", "Reservation Duration", "Vehicle details", "Customer details"};


    private static final String JDBC_MYSQL_LOCALHOST = "jdbc:mysql://localhost:3306/";
    public static final String JDBC_MYSQL_VHS_URL = JDBC_MYSQL_LOCALHOST + "vhs";



    /**
     * validates given input value against Regex to check for name validation
     *
     * @param userName Input user name
     * @return returns true if validation is correct, false otherwise
     */
    public static boolean validateName(String userName) {
        Pattern pattern = Pattern.compile("^[a-zA-Z\\s]*$");
        Matcher matcher = pattern.matcher(userName);
        return matcher.matches();
    }

    /**
     * checks for empty value given in parameter
     *
     * @param parameter user input parameter
     * @return true if parameter is empty, false otherwise
     */
    public static boolean isEmpty(String parameter) {
        return parameter != null && parameter.isEmpty();
    }


}
