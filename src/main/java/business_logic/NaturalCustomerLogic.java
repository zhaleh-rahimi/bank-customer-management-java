package business_logic;

import data_access.NaturalCustomerCRUD;
import data_access.entity.NaturalCustomer;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by dotinschool3 on 10/3/2016.
 */
public class NaturalCustomerLogic {

    public static void insertNaturalCustomer(NaturalCustomer naturalCustomer) throws SQLException {
        NaturalCustomerCRUD.insertIntoNaturalCustomerTable(naturalCustomer);
    }

    public static ArrayList<NaturalCustomer> searchByFirstName(String searchValueStr) throws SQLException {
        return  NaturalCustomerCRUD.findCustomerByFirstName(searchValueStr);
    }

    public static ArrayList<NaturalCustomer> searchByLastName(String searchValueStr) throws SQLException {
        return  NaturalCustomerCRUD.findCustomerByLastName(searchValueStr);
    }

    public static ArrayList<NaturalCustomer> searchByNationalCode(String searchValueStr) throws SQLException {
        return  NaturalCustomerCRUD.findCustomerByNationalCode(searchValueStr);
    }

    public static ArrayList<NaturalCustomer> searchById(String searchValueStr) throws SQLException {
        return  NaturalCustomerCRUD.findCustomerById(searchValueStr);
    }

    public static void deleteNaturalCustomerByID(int customerId) throws SQLException {
        NaturalCustomerCRUD.deleteFromNaturalCustomerTable(customerId);
    }

    public static void updateNaturalCustomer(NaturalCustomer naturalCustomer) {
        NaturalCustomerCRUD.updateNaturalCustomerInTable(naturalCustomer);
    }
}
