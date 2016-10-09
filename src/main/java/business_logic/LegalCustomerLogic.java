package business_logic;

import business_logic.exceptions.DataNotFoundException;
import business_logic.exceptions.DuplicateInformationException;
import business_logic.exceptions.FieldRequiredException;
import data_access.LegalCustomerCRUD;
import data_access.entity.LegalCustomer;
import data_access.entity.NaturalCustomer;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by dotinschool3 on 10/3/2016.
 */
public class LegalCustomerLogic {
    public static Boolean validated(LegalCustomer legalCustomer) {
        if (legalCustomer.getCompanyName() == null || legalCustomer.getCompanyName().equals("")) {
            throw new FieldRequiredException("وارد کردن نام شرکت الزامی است.");
        }
        if (legalCustomer.getEconomicCode() == null || legalCustomer.getEconomicCode().equals("")) {
            throw new FieldRequiredException("وارد کردن شماره اقتصادی الزامی است.");
        }
        if (legalCustomer.getRegistrationDate() == null || legalCustomer.getRegistrationDate().equals("")) {
            throw new FieldRequiredException("وارد کردن تاریخ ثبت الزامی است.");
        }

        return true;

    }

    public static void create(LegalCustomer legalCustomer) throws SQLException {
        if (validated(legalCustomer)) {
            if (!(LegalCustomerCRUD.duplicatedNumber(legalCustomer.getEconomicCode()))) {
                LegalCustomerCRUD.insertIntoLegalCustomerTable(legalCustomer);
            } else {
                throw new DuplicateInformationException("duplicate number");
            }
        }
    }


    public static void deleteLegalCustomerByID(int customerId) throws SQLException {
        LegalCustomerCRUD.deleteFromNaturalCustomerTable(customerId);
    }

    public static ArrayList<LegalCustomer> searchByCompanyName(String name) throws SQLException {
        try {
            return LegalCustomerCRUD.findCustomerByCompanyName(name);
        } catch (RuntimeException e) {
            throw new DataNotFoundException("does not exist");
        }
    }

    public static ArrayList<LegalCustomer> searchByEconomicCode(String economicCode) throws SQLException {

        try {
            return LegalCustomerCRUD.findCustomerByEconomicCode(economicCode);
        } catch (RuntimeException e) {
            throw new DataNotFoundException("doesn exist");
        }
    }

    public static ArrayList<LegalCustomer> searchById(String id) throws SQLException {
        try {
            return LegalCustomerCRUD.findCustomerById(id);
        } catch (RuntimeException e) {
            throw new DataNotFoundException("doesn exist");
        }
    }

    public static LegalCustomer updateLegalCustomer(LegalCustomer legalCustomer) throws SQLException {
        int idOfNew = legalCustomer.getCustomerId();
        if (LegalCustomerCRUD.noDuplicateNumberInTable(legalCustomer.getEconomicCode(),idOfNew)) {
            return LegalCustomerCRUD.updateLegalCustomerInTable(legalCustomer);
        } else {
            throw new DuplicateInformationException("duplicate number");
        }
    }


}
