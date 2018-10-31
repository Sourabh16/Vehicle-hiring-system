package secure;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author elau
 */
public class CustomerDB {

    // Database parameters for connection - url, username, password
    static String url;
    static String username;
    static String password;

    static final String DB_TABLE = "VHS_CUSTOMER";
    static final String DB_VEHICLE_TABLE = "VHS_VEHICLE";
    static final String DB_BOOKING_TABLE = "VHS_BOOKING";
    static final String DB_PK_CONSTRAINT = "PK_" + DB_TABLE;
    static final String DB_VEHICLE_PK_CONSTRAINT = "PK_" + DB_VEHICLE_TABLE;

    /**
     * constructor using default url, username and password
     */
    public CustomerDB() {
        // set default parameters for Derby and JavaDB
        url = "jdbc:derby://localhost/sun-appserv-samples;create=true";
        username = "APP";
        password = "APP";
    }

    /**
     * getConnecion()
     *
     * @aim Get a connection to the database using the specified info
     */
    public static Connection getConnection()
            throws SQLException, IOException {
        // first, need to set the driver for connection
        // for Derby
        System.setProperty("jdbc.drivers",
                "org.apache.derby.jdbc.ClientDriver");

        // next is to get the connection
        return DriverManager.getConnection(url, username, password);
    }

    /*
     * createCustomerDBTable
     *
     * @aim Use SQL commands to create the database table
     */
    public void createCustomerDBTable() {
        Connection cnnct = null;    // declare a connection object
        Statement stmnt = null;     // declare a statement object

        try {
            // get connection
            cnnct = getConnection();
            // get statement
            stmnt = cnnct.createStatement();

            /**
             * execute query to create a data table with the required fields:
             * EmpId, Name, Tel, Address, Bank_Account (for payroll), Salary,
             * Active (currently employed in the company)
             */
            stmnt.execute("CREATE TABLE " + DB_TABLE
                    + " (customerId CHAR(5) CONSTRAINT " + DB_PK_CONSTRAINT + " PRIMARY KEY,"
                    + " customerName VARCHAR(25), "
                    + " Phone CHAR(10), "
                    + " Address VARCHAR(30), "
                    + " Email VARCHAR(30), "
                    + " Password CHAR(8), "
                    + " customerType CHAR(12), "
                    + " Active BOOLEAN)");
        } catch (SQLException ex) {
            System.err.println("SQL exception:" + ex.toString());
        } catch (IOException ex) {
            System.err.println("IO exception:" + ex.toString());
        } finally {
            // close Statement object
            if (stmnt != null) {
                try {
                    stmnt.close();
                } catch (SQLException e) {
                    System.err.println("SQL exception:" + e.toString());
                }
            }

            // close Connection object
            if (cnnct != null) {
                try {
                    /**
                     * cnnct.close() throws a SQLException, but we cannot
                     * recover at this point
                     */
                    cnnct.close();
                } catch (SQLException sqlEx) {
                    System.err.println("SQL exception:" + sqlEx.toString());
                }
            }
        }
    }

    /*
     * createCustomerDBTable
     *
     * @aim Use SQL commands to create the database table
     */
    public void createVehicleDBTable() {
        Connection cnnct = null;    // declare a connection object
        Statement stmnt = null;     // declare a statement object

        try {
            // get connection
            cnnct = getConnection();
            // get statement
            stmnt = cnnct.createStatement();

            /**
             * execute query to create a data table with the required fields:
             * EmpId, Name, Tel, Address, Bank_Account (for payroll), Salary,
             * Active (currently employed in the company)
             */
            stmnt.execute("CREATE TABLE " + DB_VEHICLE_TABLE
                    + " (vehicleNumber VARCHAR(10) CONSTRAINT " + DB_VEHICLE_PK_CONSTRAINT + " PRIMARY KEY,"
                    + " vehicleType VARCHAR(10), "
                    + " vehicleName VARCHAR(20), "
                    + " registrationYear VARCHAR(5), "
                    + " fuelType VARCHAR(10))");
        } catch (SQLException ex) {
            // do nothing
        } catch (IOException ex) {
            // do nothing
        } finally {
            // close Statement object
            if (stmnt != null) {
                try {
                    stmnt.close();
                } catch (SQLException e) {
                    // do nothing
                }
            }

            // close Connection object
            if (cnnct != null) {
                try {
                    /**
                     * cnnct.close() throws a SQLException, but we cannot
                     * recover at this point
                     */
                    cnnct.close();
                } catch (SQLException sqlEx) {
                    // do nothing
                }
            }
        }
    }

    /*
     * createBookingDBTable
     *
     * @aim Use SQL commands to create the database table
     */
    public void createBookingDBTable() {
        Connection cnnct = null;    // declare a connection object
        Statement stmnt = null;     // declare a statement object

        try {
            // get connection
            cnnct = getConnection();
            // get statement
            stmnt = cnnct.createStatement();

            /**
             * execute query to create a data table with the required fields:
             * EmpId, Name, Tel, Address, Bank_Account (for payroll), Salary,
             * Active (currently employed in the company)
             */
            stmnt.execute("CREATE TABLE " + DB_BOOKING_TABLE
                    + " (bookingID INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                    + " bookingDate VARCHAR(20) ,"
                    + " bookingTime VARCHAR(20), "
                    + " pickupLocation VARCHAR(30), "
                    + " hireDuration VARCHAR(5), "
                    + " customerId CHAR(5), "
                    + " vehicleNumber VARCHAR(10), "
                    + " returnDate VARCHAR(20), "
                    + " returnTime VARCHAR(20)) ");
        } catch (SQLException ex) {
            System.err.println("SQL exception:" + ex.toString());
        } catch (IOException ex) {
            System.err.println("IO exception:" + ex.toString());
        } finally {
            // close Statement object
            if (stmnt != null) {
                try {
                    stmnt.close();
                } catch (SQLException e) {
                    System.err.println("SQL exception:" + e.toString());
                }
            }

            // close Connection object
            if (cnnct != null) {
                try {
                    /**
                     * cnnct.close() throws a SQLException, but we cannot
                     * recover at this point
                     */
                    cnnct.close();
                } catch (SQLException sqlEx) {
                    System.err.println("SQL exception:" + sqlEx.toString());
                }
            }
        }
    }

    /**
     * destroyDBTable()
     *
     * @aim Remove the database table
     */
    public void destroyDBTable(String tableName) {
        Connection cnnct = null;
        Statement stmnt = null;

        try {
            // get connection
            cnnct = getConnection();
            // get statement
            stmnt = cnnct.createStatement();

            // execute action query to destroy a data table
            stmnt.execute("DROP TABLE " + tableName);
        } catch (SQLException ex) {
            // do nothing

            System.err.println("SQL exception:" + ex.toString());

        } catch (IOException ex) {
            System.err.println("IO exception:" + ex.toString());
            // do nothing
        } finally {
            // close Statement object
            if (stmnt != null) {
                try {
                    stmnt.close();
                } catch (SQLException e) {
                    System.err.println("SQL exception:" + e.toString());
                }
            }

            // close Connection object
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                    System.err.println("SQL exception:" + sqlEx.toString());
                    // do nothing
                }
            }
        }
    }

    /**
     * addRecord()
     *
     * @aim Add a record into the database table
     */
    public void addCustomerRecords(ArrayList<Customer> customerList) {

        Connection cnnct = null;

        // create a PreparedStatement object
        PreparedStatement pStmnt = null;

        try {
            // get connection
            cnnct = getConnection();

            // precompiled query statement
            String preQueryStatement = "INSERT INTO " + DB_TABLE
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            for (Customer customer : customerList) {

                // get statement
                pStmnt = cnnct.prepareStatement(preQueryStatement);

                // set individual parameters at corresponding positions
                pStmnt.setString(1, customer.getCustomerId());
                pStmnt.setString(2, customer.getCustomerName());
                pStmnt.setString(3, customer.getPhone());
                pStmnt.setString(4, customer.getAddress());
                pStmnt.setString(5, customer.getEmail());
                pStmnt.setString(6, customer.getPassword());
                pStmnt.setString(7, customer.getCustomerType());
                pStmnt.setBoolean(8, customer.isActive());

                /*
                 * execute update query to add record into the data table
                 * with four fields: CustId, Name, Tel, Age
                 *
                 * will return number of records added
                 */
                int rowCount = pStmnt.executeUpdate();

                /*
                 * rowCount should be 1 because 1 record is added
                 *
                 * throws exception if not
                 */
                if (rowCount == 0) {
                    throw new SQLException("Cannot insert records!");
                }
            }
        } catch (SQLException ex) {
            System.err.println("SQL exception:" + ex.toString());
        } catch (IOException ex) {
            System.err.println("SQL exception:" + ex.toString());
        } finally {
            // close Prepared Statement object
            if (pStmnt != null) {
                try {
                    pStmnt.close();
                } catch (SQLException e) {
                    System.err.println("SQL exception:" + e.toString());
                }
            }

            // close Connection object
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                    System.err.println("SQL exception:" + sqlEx.toString());
                }
            }
        }
    }

    /**
     * addRecord()
     *
     * @aim Add a record into the database table
     */
    public void addVehicleRecords(ArrayList<Vehicle> vehicleList) {

        Connection cnnct = null;

        // create a PreparedStatement object
        PreparedStatement pStmnt = null;

        try {
            // get connection
            cnnct = getConnection();

            // precompiled query statement
            String preQueryStatement = "INSERT INTO " + DB_VEHICLE_TABLE
                    + " VALUES (?, ?, ?, ?, ?)";

            for (Vehicle vehicle : vehicleList) {

                // get statement
                pStmnt = cnnct.prepareStatement(preQueryStatement);

                // set individual parameters at corresponding positions
                pStmnt.setString(1, vehicle.getVehicleNumber());
                pStmnt.setString(2, vehicle.getVehicleType());
                pStmnt.setString(3, vehicle.getVehicleName());
                pStmnt.setString(4, vehicle.getRegistrationYear());
                pStmnt.setString(5, vehicle.getFuelType());


                /*
                 * execute update query to add record into the data table
                 * with four fields: CustId, Name, Tel, Age
                 *
                 * will return number of records added
                 */
                int rowCount = pStmnt.executeUpdate();

                /*
                 * rowCount should be 1 because 1 record is added
                 *
                 * throws exception if not
                 */
                if (rowCount == 0) {
                    throw new SQLException("Cannot insert records in vehicle db!");
                }
            }
        } catch (SQLException ex) {
            // do nothing

            System.err.println("SQL exception:" + ex.toString());

        } catch (IOException ex) {
            System.err.println("IO exception:" + ex.toString());
            // do nothing
        } finally {
            // close Prepared Statement object
            if (pStmnt != null) {
                try {
                    pStmnt.close();
                } catch (SQLException e) {
                    System.err.println("SQL exception:" + e.toString());
                }
            }

            // close Connection object
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                    System.err.println("SQL exception:" + sqlEx.toString());
                }
            }
        }
    }
}
