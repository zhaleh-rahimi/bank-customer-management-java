package data_access;

import data_access.entity.LegalCustomer;

import java.sql.*;
import java.util.ArrayList;

import static data_access.DataBaseConnection.getDBConnection;

/**
 * Created by DOTIN SCHOOL 4 on 9/28/2016.
 */
public class LegalCustomerCRUD {
    private static ArrayList<LegalCustomer> legalCustomers=null;

    public static void insertIntoLegalCustomerTable(LegalCustomer legalCustomer) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String customerInsertQueryStr = "INSERT INTO customer" + " VAlUES ()";
        String insertTableSQL = "INSERT INTO legal_customer"
                + "( legal_customer_number, name, registration_date, economic_code) VALUES"
                + "(?,?,?,?)";

        try {
            dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareStatement(customerInsertQueryStr, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            int auto_id = resultSet.getInt(1);
            System.out.println(auto_id);
            legalCustomer.setCustomerId(auto_id);
            preparedStatement = dbConnection.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, auto_id);
            preparedStatement.setString(2, legalCustomer.getCompanyName());
            preparedStatement.setString(3, legalCustomer.getRegistrationDate());
            preparedStatement.setString(4, legalCustomer.getEconomicCode());

            preparedStatement.executeUpdate();
            System.out.println("Record is inserted into customer_management_db table!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }
    private static ArrayList<LegalCustomer> setSearchResult(ResultSet resultSet) throws SQLException {
        ArrayList<LegalCustomer> legalCustomers = new ArrayList<LegalCustomer>();
        while (resultSet.next()) {
            LegalCustomer legalCustomer = new LegalCustomer();
            //Retrieve by column name
            legalCustomer.setCustomerId(resultSet.getInt("legal_customer_number"));
            legalCustomer.setCompanyName(resultSet.getString("name"));
            legalCustomer.setRegistrationDate(resultSet.getString("registration_Date"));
            legalCustomer.setEconomicCode(resultSet.getString("economic_code"));
            legalCustomers.add(legalCustomer);
            //Display values for test
            System.out.println(legalCustomer.toString());
        }
        return legalCustomers;
    }

    public static ArrayList<LegalCustomer> findCustomerByCompanyName(String name) throws SQLException {
        String searchQueryStr = "SELECT * FROM legal_customer WHERE name = ?";
        PreparedStatement statement = getDBConnection().prepareStatement(searchQueryStr);
        statement.setString(1, name);
        ResultSet resultSet = statement.executeQuery();
        legalCustomers = setSearchResult(resultSet);
        resultSet.close();
        //Display values for test
        System.out.println(legalCustomers);
        return legalCustomers;
    }

    public static ArrayList<LegalCustomer> findCustomerByEconomicCode(String economicCode) throws SQLException {
        String searchQueryStr = "SELECT * FROM legal_customer WHERE economic_code = ?";
        PreparedStatement statement = getDBConnection().prepareStatement(searchQueryStr);
        statement.setString(1, economicCode);
        ResultSet resultSet = statement.executeQuery();
        legalCustomers = setSearchResult(resultSet);
        resultSet.close();
        //Display values for test
        System.out.println(legalCustomers);
        return legalCustomers;
    }

    public static ArrayList<LegalCustomer> findCustomerById(String id) throws SQLException {
        String searchQueryStr = "SELECT * FROM legal_customer WHERE legal_customer_number= ?";
        PreparedStatement statement = getDBConnection().prepareStatement(searchQueryStr);
        statement.setInt(1, Integer.parseInt(id));
        ResultSet resultSet = statement.executeQuery();
        legalCustomers = setSearchResult(resultSet);
        resultSet.close();
        //Display values for test
        System.out.println(legalCustomers);
        return legalCustomers;
    }

    public static LegalCustomer updateLegalCustomerInTable(LegalCustomer legalCustomer) {
        String updateQueryStr = "UPDATE legal_customer SET "
                + "name = ? , registration_date = ?, economic_code = ? "
                + " WHERE natural_customer_number = " + legalCustomer.getCustomerId();

        try {
            //inserting values into natural_customer table
            PreparedStatement preparedStatement = getDBConnection().prepareStatement(updateQueryStr);
            preparedStatement.setString(1, legalCustomer.getCompanyName());
            preparedStatement.setString(2, legalCustomer.getEconomicCode());
            preparedStatement.setDate(3, Date.valueOf(legalCustomer.getRegistrationDate()));

            preparedStatement.executeUpdate();
            //show the result of update for test
            System.out.println("Record is updated in legal_customer table!"+legalCustomer);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return legalCustomer;
    }

    public static void deleteFromNaturalCustomerTable(int customerId) throws SQLException {
        String deleteQueryStr = "DELETE FROM legal_customer WHERE legal_customer_number = "+ customerId ;
        PreparedStatement preparedStatement = getDBConnection().prepareStatement(deleteQueryStr);
        preparedStatement.execute();
        //show the result of delete for test
        System.out.println("Record is deleted from legal_customer table!");
    }

    /*public static void main(String[] argv) throws SQLException {
        LegalCustomer legalCustomer = new LegalCustomer();
        legalCustomer.setCompanyName("داتین");
        legalCustomer.setEconomicCode("12333");
        legalCustomer.setRegistrationDate("2010-2-2");

        insertIntoLegalCustomerTable(legalCustomer);

    }*/
}
