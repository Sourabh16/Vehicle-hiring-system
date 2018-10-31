package entity;

import entity.VhsBooking;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-07T06:53:58")
@StaticMetamodel(VhsCustomer.class)
public class VhsCustomer_ { 

    public static volatile SingularAttribute<VhsCustomer, String> customertype;
    public static volatile SingularAttribute<VhsCustomer, String> password;
    public static volatile SingularAttribute<VhsCustomer, String> address;
    public static volatile SingularAttribute<VhsCustomer, String> phone;
    public static volatile SingularAttribute<VhsCustomer, String> customerid;
    public static volatile SingularAttribute<VhsCustomer, Boolean> active;
    public static volatile SingularAttribute<VhsCustomer, String> customername;
    public static volatile SingularAttribute<VhsCustomer, String> email;
    public static volatile CollectionAttribute<VhsCustomer, VhsBooking> vhsBookingCollection;

}