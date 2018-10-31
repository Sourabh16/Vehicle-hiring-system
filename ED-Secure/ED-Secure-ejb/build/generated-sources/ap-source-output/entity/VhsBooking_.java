package entity;

import entity.VhsCustomer;
import entity.VhsVehicle;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-07T06:53:58")
@StaticMetamodel(VhsBooking.class)
public class VhsBooking_ { 

    public static volatile SingularAttribute<VhsBooking, String> pickuplocation;
    public static volatile SingularAttribute<VhsBooking, VhsVehicle> vehiclenumber;
    public static volatile SingularAttribute<VhsBooking, String> bookingtime;
    public static volatile SingularAttribute<VhsBooking, String> returndate;
    public static volatile SingularAttribute<VhsBooking, String> hireduration;
    public static volatile SingularAttribute<VhsBooking, VhsCustomer> customerid;
    public static volatile SingularAttribute<VhsBooking, String> bookingdate;
    public static volatile SingularAttribute<VhsBooking, Integer> bookingid;
    public static volatile SingularAttribute<VhsBooking, String> returntime;

}