package tempBeans;

/**
 *
 * @author Sourabh
 */
public class overdueVehicles {

String bookingId,bookingDate,bookingTime,pickupLocation,hireduration,vehicleDetails, customerDetails;

    public overdueVehicles() {
    }

    public overdueVehicles(String bookingId, String bookingDate, String bookingTime, String pickupLocation, String hireduration, String vehicleDetails, String customerDetails) {
        this.bookingId = bookingId;
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
        this.pickupLocation = pickupLocation;
        this.hireduration = hireduration;
        this.vehicleDetails = vehicleDetails;
        this.customerDetails = customerDetails;
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

    public String getHireduration() {
        return hireduration;
    }

    public void setHireduration(String hireduration) {
        this.hireduration = hireduration;
    }

    public String getVehicleDetails() {
        return vehicleDetails;
    }

    public void setVehicleDetails(String vehicleDetails) {
        this.vehicleDetails = vehicleDetails;
    }

    public String getCustomerDetails() {
        return customerDetails;
    }

    public void setCustomerDetails(String customerDetails) {
        this.customerDetails = customerDetails;
    }


}
