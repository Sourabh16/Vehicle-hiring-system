
package secure;

import java.io.Serializable;

public class Vehicle implements Serializable {
    
    private final String vehicleType;
    private final String vehicleNumber;
    private final String vehicleName;
    private final String registrationYear;
    private final String fuelType;

    public Vehicle(String vehicleNumber, String vehicleType, String vehicleName, String registrationYear, 
            String fuelType) {
        this.vehicleType = vehicleType;
        this.vehicleNumber = vehicleNumber;
        this.vehicleName = vehicleName;
        this.registrationYear = registrationYear;
        this.fuelType = fuelType;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public String getRegistrationYear() {
        return registrationYear;
    }
    
    public String getFuelType() {
        return fuelType;
    }
}
