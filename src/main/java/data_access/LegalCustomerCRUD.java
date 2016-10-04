package data_access;

import data_access.entity.LegalCustomer;
import data_access.entity.NaturalCustomer;

import java.sql.*;

import static data_access.DataBaseConnection.getDBConnection;

/**
 * Created by DOTIN SCHOOL 4 on 9/28/2016.
 */
public class LegalCustomerCRUD {
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

    /*public static void main(String[] argv) throws SQLException {
        LegalCustomer legalCustomer = new LegalCustomer();
        legalCustomer.setCompanyName("داتین");
        legalCustomer.setEconomicCode("12333");
        legalCustomer.setRegistrationDate("2010-2-2");

        insertIntoLegalCustomerTable(legalCustomer);

    }*/
}
