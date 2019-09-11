package atm_database_reader;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DataSource {

    private String databaseName;

    /**
     * @param databaseName the name of the customer file
     */
    public DataSource(String databaseName) {
        this.databaseName = databaseName;
    }

    /**
     * Reads the customer numbers and pins
     * and initializes the bank accounts.
     */
    public Map<Integer, Customer> readCustomers() throws IOException {
        Map<Integer, Customer> customers = new HashMap<Integer, Customer>();
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:"+this.databaseName;
            Connection conn = DriverManager.getConnection(dbURL);
            if (conn != null){
                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
                String query = "Select * from customers";
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                while(resultSet.next()){
                    int number = resultSet.getInt(1);
                    int pin = resultSet.getInt(2);
                    double currentBalance = resultSet.getDouble(3);
                    Customer c = new Customer(number, pin, currentBalance);
                    customers.put(c.getCustomerNumber(), c);
                }
                conn.close();
                return customers;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
