package entity;

import entity.VhsBooking;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-07T06:53:58")
@StaticMetamodel(VhsVehicle.class)
public class VhsVehicle_ { 

    public static volatile SingularAttribute<VhsVehicle, String> vehicletype;
    public static volatile SingularAttribute<VhsVehicle, String> registrationyear;
    public static volatile SingularAttribute<VhsVehicle, String> vehiclenumber;
    public static volatile SingularAttribute<VhsVehicle, String> fueltype;
    public static volatile SingularAttribute<VhsVehicle, String> vehiclename;
    public static volatile CollectionAttribute<VhsVehicle, VhsBooking> vhsBookingCollection;

}