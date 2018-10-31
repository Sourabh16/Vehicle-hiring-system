/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package secure;

/**
 *
 * @author elau
 */
public class SetupBookingDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // the database object to access the actual database
        CustomerDB db = new CustomerDB();

        // make sure no name conflicts
        try {
            db.destroyDBTable("VHS_BOOKING");
        } catch (Exception ex) {
        }

        // create the database table
        System.out.println("Create an empty database table booking");
        db.createBookingDBTable();
        
//        System.out.println("Add several static records in the database table");
//        
//        
//        // prepare data
//        Vehicle vehicle001 = new Vehicle("4WD1MN", "car", "Nissan Micra", "2008","petrol");
//        Vehicle vehicle002 = new Vehicle("UPC129", "car", "Toyota Yaris", "2005","petrol");
//        Vehicle vehicle003 = new Vehicle("L982DH", "van", "Ford Transit", "2000","diesel");
        
        
//        // prepare list
//        ArrayList<Vehicle> vehicleList = new ArrayList<>();
//        vehicleList.add(vehicle001);
//        vehicleList.add(vehicle002);
//        vehicleList.add(vehicle003);
        
        // add data to db
//        db.addVehicleRecords(vehicleList);
    }
}
