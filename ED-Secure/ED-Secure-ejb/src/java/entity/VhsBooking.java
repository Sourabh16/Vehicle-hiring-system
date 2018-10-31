package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sourabh
 */
@Entity
@Table(name = "VHS_BOOKING")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VhsBooking.findAll", query = "SELECT v FROM VhsBooking v")
    , @NamedQuery(name = "VhsBooking.findByBookingid", query = "SELECT v FROM VhsBooking v WHERE v.bookingid = :bookingid")
    , @NamedQuery(name = "VhsBooking.findByBookingdate", query = "SELECT v FROM VhsBooking v WHERE v.bookingdate = :bookingdate")
    , @NamedQuery(name = "VhsBooking.findByBookingtime", query = "SELECT v FROM VhsBooking v WHERE v.bookingtime = :bookingtime")
    , @NamedQuery(name = "VhsBooking.findByPickuplocation", query = "SELECT v FROM VhsBooking v WHERE v.pickuplocation = :pickuplocation")
    , @NamedQuery(name = "VhsBooking.findByHireduration", query = "SELECT v FROM VhsBooking v WHERE v.hireduration = :hireduration")
    , @NamedQuery(name = "VhsBooking.findByReturndate", query = "SELECT v FROM VhsBooking v WHERE v.returndate = :returndate")
    , @NamedQuery(name = "VhsBooking.findByReturntime", query = "SELECT v FROM VhsBooking v WHERE v.returntime = :returntime")})
public class VhsBooking implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "BOOKINGID")
    private Integer bookingid;
    @Size(max = 20)
    @Column(name = "BOOKINGDATE")
    private String bookingdate;
    @Size(max = 20)
    @Column(name = "BOOKINGTIME")
    private String bookingtime;
    @Size(max = 30)
    @Column(name = "PICKUPLOCATION")
    private String pickuplocation;
    @Size(max = 5)
    @Column(name = "HIREDURATION")
    private String hireduration;
    @Size(max = 20)
    @Column(name = "RETURNDATE")
    private String returndate;
    @Size(max = 20)
    @Column(name = "RETURNTIME")
    private String returntime;
    @JoinColumn(name = "CUSTOMERID", referencedColumnName = "CUSTOMERID")
    @ManyToOne
    private VhsCustomer customerid;
    @JoinColumn(name = "VEHICLENUMBER", referencedColumnName = "VEHICLENUMBER")
    @ManyToOne
    private VhsVehicle vehiclenumber;

    public VhsBooking() {
    }

    public VhsBooking(Integer bookingid, String bookingdate, String bookingtime, String pickuplocation, String hireduration, String returndate, String returntime, VhsCustomer customerid, VhsVehicle vehiclenumber) {
        this.bookingid = bookingid;
        this.bookingdate = bookingdate;
        this.bookingtime = bookingtime;
        this.pickuplocation = pickuplocation;
        this.hireduration = hireduration;
        this.returndate = returndate;
        this.returntime = returntime;
        this.customerid = customerid;
        this.vehiclenumber = vehiclenumber;
    }

    
    
    public VhsBooking(Integer bookingid) {
        this.bookingid = bookingid;
    }

    public Integer getBookingid() {
        return bookingid;
    }

    public void setBookingid(Integer bookingid) {
        this.bookingid = bookingid;
    }

    public String getBookingdate() {
        return bookingdate;
    }

    public void setBookingdate(String bookingdate) {
        this.bookingdate = bookingdate;
    }

    public String getBookingtime() {
        return bookingtime;
    }

    public void setBookingtime(String bookingtime) {
        this.bookingtime = bookingtime;
    }

    public String getPickuplocation() {
        return pickuplocation;
    }

    public void setPickuplocation(String pickuplocation) {
        this.pickuplocation = pickuplocation;
    }

    public String getHireduration() {
        return hireduration;
    }

    public void setHireduration(String hireduration) {
        this.hireduration = hireduration;
    }

    public String getReturndate() {
        return returndate;
    }

    public void setReturndate(String returndate) {
        this.returndate = returndate;
    }

    public String getReturntime() {
        return returntime;
    }

    public void setReturntime(String returntime) {
        this.returntime = returntime;
    }

    public VhsCustomer getCustomerid() {
        return customerid;
    }

    public void setCustomerid(VhsCustomer customerid) {
        this.customerid = customerid;
    }

    public VhsVehicle getVehiclenumber() {
        return vehiclenumber;
    }

    public void setVehiclenumber(VhsVehicle vehiclenumber) {
        this.vehiclenumber = vehiclenumber;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bookingid != null ? bookingid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VhsBooking)) {
            return false;
        }
        VhsBooking other = (VhsBooking) object;
        if ((this.bookingid == null && other.bookingid != null) || (this.bookingid != null && !this.bookingid.equals(other.bookingid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.VhsBooking[ bookingid=" + bookingid + " ]";
    }
    
}
