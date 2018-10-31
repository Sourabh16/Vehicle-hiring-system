package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Sourabh
 */
@Entity
@Table(name = "VHS_VEHICLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VhsVehicle.findAll", query = "SELECT v FROM VhsVehicle v")
    , @NamedQuery(name = "VhsVehicle.findByVehiclenumber", query = "SELECT v FROM VhsVehicle v WHERE v.vehiclenumber = :vehiclenumber")
    , @NamedQuery(name = "VhsVehicle.findByVehicletype", query = "SELECT v FROM VhsVehicle v WHERE v.vehicletype = :vehicletype")
    , @NamedQuery(name = "VhsVehicle.findByVehiclename", query = "SELECT v FROM VhsVehicle v WHERE v.vehiclename = :vehiclename")
    , @NamedQuery(name = "VhsVehicle.findByRegistrationyear", query = "SELECT v FROM VhsVehicle v WHERE v.registrationyear = :registrationyear")
    , @NamedQuery(name = "VhsVehicle.findByFueltype", query = "SELECT v FROM VhsVehicle v WHERE v.fueltype = :fueltype")})
public class VhsVehicle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "VEHICLENUMBER")
    private String vehiclenumber;
    @Size(max = 10)
    @Column(name = "VEHICLETYPE")
    private String vehicletype;
    @Size(max = 20)
    @Column(name = "VEHICLENAME")
    private String vehiclename;
    @Size(max = 5)
    @Column(name = "REGISTRATIONYEAR")
    private String registrationyear;
    @Size(max = 10)
    @Column(name = "FUELTYPE")
    private String fueltype;
    @OneToMany(mappedBy = "vehiclenumber")
    private Collection<VhsBooking> vhsBookingCollection;

    public VhsVehicle() {
    }

    public VhsVehicle(String vehiclenumber, String vehicletype, String vehiclename, String registrationyear, String fueltype) {
        this.vehiclenumber = vehiclenumber;
        this.vehicletype = vehicletype;
        this.vehiclename = vehiclename;
        this.registrationyear = registrationyear;
        this.fueltype = fueltype;
    }
    
    

    public VhsVehicle(String vehiclenumber) {
        this.vehiclenumber = vehiclenumber;
    }

    public String getVehiclenumber() {
        return vehiclenumber;
    }

    public void setVehiclenumber(String vehiclenumber) {
        this.vehiclenumber = vehiclenumber;
    }

    public String getVehicletype() {
        return vehicletype;
    }

    public void setVehicletype(String vehicletype) {
        this.vehicletype = vehicletype;
    }

    public String getVehiclename() {
        return vehiclename;
    }

    public void setVehiclename(String vehiclename) {
        this.vehiclename = vehiclename;
    }

    public String getRegistrationyear() {
        return registrationyear;
    }

    public void setRegistrationyear(String registrationyear) {
        this.registrationyear = registrationyear;
    }

    public String getFueltype() {
        return fueltype;
    }

    public void setFueltype(String fueltype) {
        this.fueltype = fueltype;
    }

    @XmlTransient
    public Collection<VhsBooking> getVhsBookingCollection() {
        return vhsBookingCollection;
    }

    public void setVhsBookingCollection(Collection<VhsBooking> vhsBookingCollection) {
        this.vhsBookingCollection = vhsBookingCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vehiclenumber != null ? vehiclenumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VhsVehicle)) {
            return false;
        }
        VhsVehicle other = (VhsVehicle) object;
        if ((this.vehiclenumber == null && other.vehiclenumber != null) || (this.vehiclenumber != null && !this.vehiclenumber.equals(other.vehiclenumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.VhsVehicle[ vehiclenumber=" + vehiclenumber + " ]";
    }
    
}
