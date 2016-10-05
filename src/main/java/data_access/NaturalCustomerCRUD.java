package data_access;

import data_access.entity.NaturalCustomer;

import java.sql.*;
import java.util.ArrayList;

import static data_access.DataBaseConnection.getDBConnection;

/**
 * Created by DOTIN SCHOOL 4 on 9/28/2016.
 */
public class NaturalCustomerCRUD {
    private static ArrayList<NaturalCustomer> naturalCustomers = null;

    public static void insertIntoNaturalCustomerTable(NaturalCustomer naturalCustomer) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String customerInsertQueryStr = "INSERT INTO customer" + " VAlUES ()";
        String insertTableSQL = "INSERT INTO natural_customer"
                + "(natural_customer_number,first_name, last_name, father_name, date_of_birth,national_code) VALUES"
                + "(?,?,?,?,?,?)";

        try {
            //generating unique customer Id ---auto incrementing
            preparedStatement = getDBConnection().prepareStatement(customerInsertQueryStr, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            int auto_id = resultSet.getInt(1);
            //show the id for test
            System.out.println(auto_id);
            naturalCustomer.setCustomerId(auto_id);
            //inserting values into natural_customer table
            preparedStatement = getDBConnection().prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, auto_id);
            preparedStatement.setString(2, naturalCustomer.getFirstName());
            preparedStatement.setString(3, naturalCustomer.getLastName());
            preparedStatement.setString(4, naturalCustomer.getFatherName());
            preparedStatement.setDate(5, Date.valueOf(naturalCustomer.getDateOfBirth()));
            preparedStatement.setString(6, naturalCustomer.getNationalCode());
            preparedStatement.executeUpdate();
            //show the result of inserting for test
            System.out.println("Record is inserted into customer_management_db table!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static ArrayList<NaturalCustomer> setSearchResult(ResultSet resultSet) throws SQLException {
        ArrayList<NaturalCustomer> naturalCustomers = new ArrayList<NaturalCustomer>();
        while (resultSet.next()) {
            NaturalCustomer naturalCustomer = new NaturalCustomer();
            //Retrieve by column name
            naturalCustomer.setCustomerId(resultSet.getInt("natural_customer_number"));
            naturalCustomer.setFirstName(resultSet.getString("first_name"));
            naturalCustomer.setLastName(resultSet.getString("last_name"));
            naturalCustomer.setDateOfBirth(resultSet.getString("date_of_birth"));
            naturalCustomer.setNationalCode(resultSet.getString("national_code"));
            naturalCustomer.setFatherName(resultSet.getString("father_name"));
            naturalCustomers.add(naturalCustomer);
            //Display values for test
            System.out.println(naturalCustomer.toString());
        }
        return naturalCustomers;
    }

    public static ArrayList<NaturalCustomer> findCustomerByFirstName(String name) throws SQLException {
        String searchQueryStr = "SELECT * FROM natural_customer WHERE first_name = ?";
        PreparedStatement statement = getDBConnection().prepareStatement(searchQueryStr);
        statement.setString(1, name);
        ResultSet resultSet = statement.executeQuery();
        naturalCustomers = setSearchResult(resultSet);
        resultSet.close();
        //Display values for test
        System.out.println(naturalCustomers);
        return naturalCustomers;
    }

    public static ArrayList<NaturalCustomer> findCustomerByLastName(String lastName) throws SQLException {
        String searchQueryStr = "SELECT * FROM natural_customer WHERE last_name = ?";
        PreparedStatement statement = getDBConnection().prepareStatement(searchQueryStr);
        statement.setString(1, lastName);
        ResultSet resultSet = statement.executeQuery();
        naturalCustomers = setSearchResult(resultSet);
        resultSet.close();
        //Display values for test
        System.out.println(naturalCustomers);
        return naturalCustomers;
    }

    public static ArrayList<NaturalCustomer> findCustomerById(String id) throws SQLException {
        String searchQueryStr = "SELECT * FROM natural_customer WHERE natural_customer_number = ?";
        PreparedStatement statement = getDBConnection().prepareStatement(searchQueryStr);
        statement.setInt(1, Integer.parseInt(id));
        ResultSet resultSet = statement.executeQuery();
        naturalCustomers = setSearchResult(resultSet);
        resultSet.close();
        //Display values for test
        System.out.println(naturalCustomers);
        return naturalCustomers;
    }

    public static ArrayList<NaturalCustomer> findCustomerByNationalCode(String nationalCode) throws SQLException {
        String searchQueryStr = "SELECT * FROM natural_customer WHERE national_code = ?";
        PreparedStatement statement = getDBConnection().prepareStatement(searchQueryStr);
        statement.setString(1, nationalCode);
        ResultSet resultSet = statement.executeQuery();
        ArrayList<NaturalCustomer> naturalCustomers = setSearchResult(resultSet);
        resultSet.close();
        //Display values for test
        System.out.println(naturalCustomers);
        return naturalCustomers;
    }

    public static NaturalCustomer updateNaturalCustomerInTable(NaturalCustomer naturalCustomer)  {
        String updateQueryStr = "UPDATE natural_customer SET "
                + "first_name = ? , last_name = ?, father_name = ? , date_of_birth = ? , national_code = ? "
                + " WHERE natural_customer_number = " + naturalCustomer.getCustomerId();

        try {
            //inserting values into natural_customer table
            PreparedStatement preparedStatement = getDBConnection().prepareStatement(updateQueryStr);
            preparedStatement.setString(1, naturalCustomer.getFirstName());
            preparedStatement.setString(2, naturalCustomer.getLastName());
            preparedStatement.setString(3, naturalCustomer.getFatherName());
            preparedStatement.setDate(4, Date.valueOf(naturalCustomer.getDateOfBirth()));
            preparedStatement.setString(5, naturalCustomer.getNationalCode());
            preparedStatement.executeUpdate();
            //show the result of update for test
            System.out.println("Record is updated in natural_customer table!"+naturalCustomer);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return naturalCustomer;
    }

    public static void deleteFromNaturalCustomerTable(int customerId) throws SQLException {
        String deleteQueryStr = "DELETE FROM natural_customer WHERE natural_customer_number = "+ customerId ;
        PreparedStatement preparedStatement = getDBConnection().prepareStatement(deleteQueryStr);
        preparedStatement.execute();
        //show the result of delete for test
        System.out.println("Record is deleted from natural_customer table!");
    }



    //Main Test
    /*public static void main(String[] argv) throws SQLException {
        NaturalCustomer naturalCustomer = new NaturalCustomer();
        *//*naturalCustomer.setFirstName("مهلا");
        naturalCustomer.setLastName("محمدی");
        naturalCustomer.setFatherName("حمید");
        naturalCustomer.setDateOfBirth("2010-2-2");
        naturalCustomer.setNationalCode("557");*//*
        //naturalCustomer.setCustomerId(179);
        *//*try {
            insertIntoNaturalCustomerTable(naturalCustomer);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*//*

       deleteFromNaturalCustomerTable(179);
    }*/

}
