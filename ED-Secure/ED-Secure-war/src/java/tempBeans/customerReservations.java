package tempBeans;

/**
 *
 * @author Sourabh
 */
public class customerReservations {

String bookingDate,bookingTime,pickupLocation,hireduration,otherDetails;

    public customerReservations() {
    }

    public customerReservations(String bookingDate, String bookingTime, String pickupLocation, String hireduration, String otherDetails) {
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
        this.pickupLocation = pickupLocation;
        this.hireduration = hireduration;
        this.otherDetails = otherDetails;
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

    public String getOtherDetails() {
        return otherDetails;
    }

    public void setOtherDetails(String otherDetails) {
        this.otherDetails = otherDetails;
    }


}
