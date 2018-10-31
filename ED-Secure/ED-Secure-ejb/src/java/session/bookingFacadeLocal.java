/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.VhsBooking;
import java.util.ArrayList;
import javax.ejb.Local;

/**
 *
 * @author Sourabh
 */
@Local
public interface bookingFacadeLocal {
    
    VhsBooking find(String bookingId);

    public ArrayList<VhsBooking> findAllBookings();

    public boolean addBooking(VhsBooking booking);

    public boolean updateBookingDetails(VhsBooking booking, String returnDate, String returnTime);
}
