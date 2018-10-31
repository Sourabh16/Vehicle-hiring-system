package entity;

import java.io.Serializable;

/**
 *
 * @author Sourabh
 */
public class VehicleDTO implements Serializable {
    
    String vehicleNumber;
    String vehicleType;
    String vehicleName;
    String registrationYear;
    String fuelType;

    public VehicleDTO() {
    }

    
    
    public VehicleDTO(String vehicleNumber, String vehicleType, String vehicleName, String registrationYear, String fuelType) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.vehicleName = vehicleName;
        this.registrationYear = registrationYear;
        this.fuelType = fuelType;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getVehicleType() {
        return vehicleType;
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

    @Override
    public String toString() {
        return "VehicleDTO{" + "vehicleNumber=" + vehicleNumber + ", vehicleType=" + vehicleType + ", vehicleName=" + vehicleName + ", registrationYear=" + registrationYear + ", fuelType=" + fuelType + '}';
    }
    
    
}
