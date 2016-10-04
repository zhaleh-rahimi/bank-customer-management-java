package data_access;

import data_access.entity.NaturalCustomer;

import java.sql.*;
import java.util.ArrayList;

import static data_access.DataBaseConnection.getDBConnection;

/**
 * Created by DOTIN SCHOOL 4 on 9/28/2016.
 */
public class NaturalCustomerCRUD {


    public static void insertIntoNaturalCustomerTable(NaturalCustomer naturalCustomer) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String customerInsertQueryStr = "INSERT INTO customer" + " VAlUES ()";
        String insertTableSQL = "INSERT INTO natural_customer"
                + "(natural_customer_number,first_name, last_name, father_name, date_of_birth,national_code) VALUES"
                + "(?,?,?,?,?,?)";

        try {
            dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareStatement(customerInsertQueryStr, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            int auto_id = resultSet.getInt(1);
            System.out.println(auto_id);
            naturalCustomer.setCustomerId(auto_id);
            preparedStatement = dbConnection.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, auto_id);
            preparedStatement.setString(2, naturalCustomer.getFirstName());
            preparedStatement.setString(3, naturalCustomer.getLastName());
            preparedStatement.setString(4, naturalCustomer.getFatherName());
            preparedStatement.setDate(5, Date.valueOf(naturalCustomer.getDateOfBirth()));
            preparedStatement.setString(6, naturalCustomer.getNationalCode());

            preparedStatement.executeUpdate();
            System.out.println("Record is inserted into customer_management_db table!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] argv) throws SQLException {
        NaturalCustomer naturalCustomer = new NaturalCustomer();
        naturalCustomer.setFirstName("مهلا");
        naturalCustomer.setLastName("محمدی");
        naturalCustomer.setFatherName("پرهام");
        naturalCustomer.setDateOfBirth("2010-2-2");
        naturalCustomer.setNationalCode("557");
        try {
            insertIntoNaturalCustomerTable(naturalCustomer);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        findCustomerByFirstName("ali");
    }
    public static ArrayList<NaturalCustomer> findCustomerByFirstName(String name) throws SQLException {
        Connection dbConnection = getDBConnection();
        String searchQueryStr = "SELECT * FROM natural_customer WHERE first_name = ?";
        PreparedStatement statement = dbConnection.prepareStatement(searchQueryStr);
        statement.setString(1, name);
        ResultSet resultSet = statement.executeQuery();
        ArrayList<NaturalCustomer> naturalCustomers=new ArrayList<NaturalCustomer>();
        while(resultSet.next()){
            NaturalCustomer naturalCustomer=new NaturalCustomer();
            //Retrieve by column name
            naturalCustomer.setCustomerId(resultSet.getInt("natural_customer_number"));
            naturalCustomer.setFirstName(resultSet.getString("first_name"));
            naturalCustomer.setLastName(resultSet.getString("last_name"));
            naturalCustomer.setDateOfBirth(resultSet.getString("date_of_birth"));
            naturalCustomer.setNationalCode(resultSet.getString("national_code"));
            naturalCustomer.setFatherName(resultSet.getString("father_name"));
            naturalCustomers.add(naturalCustomer);
            //Display values
            System.out.println(naturalCustomer.toString());
        }
        resultSet.close();
        System.out.println(naturalCustomers);
        return  naturalCustomers;
    }

    public static void findCustomerByLAstName() {
    }

    public static void findCustomerByNationalCode() {
    }

    public static void findCustomerById() {
    }
}
