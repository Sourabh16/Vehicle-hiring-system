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
@Table(name = "VHS_CUSTOMER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VhsCustomer.findAll", query = "SELECT v FROM VhsCustomer v")
    , @NamedQuery(name = "VhsCustomer.findByCustomerid", query = "SELECT v FROM VhsCustomer v WHERE v.customerid = :customerid")
    , @NamedQuery(name = "VhsCustomer.findByCustomername", query = "SELECT v FROM VhsCustomer v WHERE v.customername = :customername")
    , @NamedQuery(name = "VhsCustomer.findByPhone", query = "SELECT v FROM VhsCustomer v WHERE v.phone = :phone")
    , @NamedQuery(name = "VhsCustomer.findByAddress", query = "SELECT v FROM VhsCustomer v WHERE v.address = :address")
    , @NamedQuery(name = "VhsCustomer.findByEmail", query = "SELECT v FROM VhsCustomer v WHERE v.email = :email")
    , @NamedQuery(name = "VhsCustomer.findByPassword", query = "SELECT v FROM VhsCustomer v WHERE v.password = :password")
    , @NamedQuery(name = "VhsCustomer.findByCustomertype", query = "SELECT v FROM VhsCustomer v WHERE v.customertype = :customertype")
    , @NamedQuery(name = "VhsCustomer.findByActive", query = "SELECT v FROM VhsCustomer v WHERE v.active = :active")})
public class VhsCustomer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "CUSTOMERID")
    private String customerid;
    @Size(max = 25)
    @Column(name = "CUSTOMERNAME")
    private String customername;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 10)
    @Column(name = "PHONE")
    private String phone;
    @Size(max = 30)
    @Column(name = "ADDRESS")
    private String address;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 30)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 8)
    @Column(name = "PASSWORD")
    private String password;
    @Size(max = 12)
    @Column(name = "CUSTOMERTYPE")
    private String customertype;
    @Column(name = "ACTIVE")
    private Boolean active;
    @OneToMany(mappedBy = "customerid")
    private Collection<VhsBooking> vhsBookingCollection;

    public VhsCustomer() {
    }

    public VhsCustomer(String customerid, String customername, String phone, String address, String email, String password, String customertype, Boolean active) {
        this.customerid = customerid;
        this.customername = customername;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.password = password;
        this.customertype = customertype;
        this.active = active;
    }

    public VhsCustomer(String customername, String phone, String address, String email, String password, String customertype, Boolean active) {
        this.customername = customername;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.password = password;
        this.customertype = customertype;
        this.active = active;
    }

    
    
    
    public VhsCustomer(String customerid) {
        this.customerid = customerid;
    }

    

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
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

    public String getCustomertype() {
        return customertype;
    }

    public void setCustomertype(String customertype) {
        this.customertype = customertype;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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
        hash += (customerid != null ? customerid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VhsCustomer)) {
            return false;
        }
        VhsCustomer other = (VhsCustomer) object;
        if ((this.customerid == null && other.customerid != null) || (this.customerid != null && !this.customerid.equals(other.customerid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.VhsCustomer[ customerid=" + customerid + " ]";
    }
    
}
