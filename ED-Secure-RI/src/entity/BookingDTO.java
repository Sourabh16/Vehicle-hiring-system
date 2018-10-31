/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author elau
 */
public class BookingDTO implements Serializable {
    
    String bookingId;
    String bookingDate;
    String bookingTime;
    String pickupLocation;
    String hireDuration;
    String customerId;
    String vehicleNumber;
    String returnDate;
    String returnTime;
    private int vehicleHourlyRate=20;

    
    public BookingDTO() {
    }

    
    
    public BookingDTO(String bookingId, String bookingDate, String bookingTime, String pickupLocation, String hireDuration, String customerId, String vehicleNumber, String returnDate, String returnTime) {
        this.bookingId = bookingId;
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
        this.pickupLocation = pickupLocation;
        this.hireDuration = hireDuration;
        this.customerId = customerId;
        this.vehicleNumber = vehicleNumber;
        this.returnDate = returnDate;
        this.returnTime = returnTime;
    }

    public String getBookingId() {
        return bookingId;
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

    public int getVehicleHourlyRate() {
        return vehicleHourlyRate;
    }
    
    

    @Override
    public String toString() {
        return "BookingDTO{" + "bookingId=" + bookingId + ", bookingDate=" + bookingDate + ", bookingTime=" + bookingTime + ", pickupLocation=" + pickupLocation + ", hireDuration=" + hireDuration + ", customerId=" + customerId + ", vehicleNumber=" + vehicleNumber + ", returnDate=" + returnDate + ", returnTime=" + returnTime + '}';
    }

    
}
