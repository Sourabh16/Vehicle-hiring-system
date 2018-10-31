
package secure;

import java.io.Serializable;

public class booking implements Serializable {
    
    private final String bookingDate;
    private final String bookingTime;
    private final String pickupLocation;
    private final String hireDuration;
    private final String customerId;
    private final String vehicleNumber;
    private final String returnDate;
    private final String returnTime;

    public booking(String bookingDate, String bookingTime, String pickupLocation, String hireDuration, String customerId, String vehicleNumber, String returnDate, String returnTime) {
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
        this.pickupLocation = pickupLocation;
        this.hireDuration = hireDuration;
        this.customerId = customerId;
        this.vehicleNumber = vehicleNumber;
        this.returnDate = returnDate;
        this.returnTime = returnTime;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public String getBookingTime() {
        return bookingTime;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public String getHireDuration() {
        return hireDuration;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public String getReturnTime() {
        return returnTime;
    }

    

    
}
