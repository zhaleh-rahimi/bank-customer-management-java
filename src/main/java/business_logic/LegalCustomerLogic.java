package business_logic;

import data_access.LegalCustomerCRUD;
import data_access.entity.LegalCustomer;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by dotinschool3 on 10/3/2016.
 */
public class LegalCustomerLogic {
    public static void create(LegalCustomer legalCustomer) throws SQLException {
        LegalCustomerCRUD.insertIntoLegalCustomerTable(legalCustomer);

    }
    public static void deleteLegalCustomerByID(int customerId) throws SQLException {
        LegalCustomerCRUD.deleteFromNaturalCustomerTable(customerId);
    }

    public static ArrayList<LegalCustomer> searchByCompanyName(String name) throws SQLException {
        return LegalCustomerCRUD.findCustomerByCompanyName(name);
    }

    public static ArrayList<LegalCustomer> searchByEconomicCode(String economicCode) throws SQLException {
        return LegalCustomerCRUD.findCustomerByEconomicCode(economicCode);
    }

    public static ArrayList<LegalCustomer> searchById(String id) throws SQLException {
        return LegalCustomerCRUD.findCustomerById(id);
    }

    public static LegalCustomer updateLegalCustomer(LegalCustomer legalCustomer) {
        return LegalCustomerCRUD.updateLegalCustomerInTable(legalCustomer);
    }


}
