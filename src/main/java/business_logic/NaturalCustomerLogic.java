package business_logic;

import business_logic.exceptions.DataNotFoundException;
import business_logic.exceptions.DuplicateInformationException;
import business_logic.exceptions.FieldRequiredException;
import data_access.NaturalCustomerCRUD;
import data_access.entity.NaturalCustomer;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by dotinschool3 on 10/3/2016.
 */
public class NaturalCustomerLogic {
    public static boolean validate(NaturalCustomer naturalCustomer) {
        if (naturalCustomer.getFirstName() == null || naturalCustomer.getFirstName().equals("")) {
            throw new FieldRequiredException("وارد کردن نام الزامی است.");
        }
        if (naturalCustomer.getLastName() == null || naturalCustomer.getLastName().equals("")) {
            throw new FieldRequiredException("وارد کردن نام خانوادگی الزامی است.");
        }
        if (naturalCustomer.getFatherName() == null || naturalCustomer.getFatherName().equals("")) {
            throw new FieldRequiredException("وارد کردن نام پدر الزامی است.");
        }
        if (naturalCustomer.getDateOfBirth() == null || naturalCustomer.getDateOfBirth().equals("")) {
            throw new FieldRequiredException("وارد کردن تاریخ تولد الزامی است.");
        }
        if (naturalCustomer.getNationalCode() == null || naturalCustomer.getNationalCode().equals("")) {
            throw new FieldRequiredException("وارد کردن کد ملی الزامی است.");
        }

        return true;
    }

    public static void insertNaturalCustomer(NaturalCustomer naturalCustomer) throws SQLException {
        if (validate(naturalCustomer)) {
            if (!(NaturalCustomerCRUD.duplicatedNumber(naturalCustomer.getNationalCode()))) {
                NaturalCustomerCRUD.insertIntoNaturalCustomerTable(naturalCustomer);
            } else {
                throw new DuplicateInformationException("duplicate number");
            }
        }
    }

    public static ArrayList<NaturalCustomer> searchByFirstName(String searchValueStr) throws SQLException {

        try {
            return NaturalCustomerCRUD.findCustomerByFirstName(searchValueStr);
        } catch (RuntimeException e) {
            throw new DataNotFoundException("no data");
        }
    }

    public static ArrayList<NaturalCustomer> searchByLastName(String searchValueStr) throws SQLException {
        try {
            return NaturalCustomerCRUD.findCustomerByLastName(searchValueStr);
        } catch (RuntimeException e) {
            throw new DataNotFoundException("no data");
        }
    }

    public static ArrayList<NaturalCustomer> searchByNationalCode(String searchValueStr) throws SQLException {
        try {
            return NaturalCustomerCRUD.findCustomerByNationalCode(searchValueStr);
        } catch (DataNotFoundException e) {
            throw new DataNotFoundException("no data");
        }
    }

    public static ArrayList<NaturalCustomer> searchById(String searchValueStr) throws SQLException {
        try {
            return NaturalCustomerCRUD.findCustomerById(searchValueStr);
        } catch (RuntimeException e) {
            throw new DataNotFoundException("no data");
        }
    }

    public static void deleteNaturalCustomerByID(int customerId) throws SQLException {
        NaturalCustomerCRUD.deleteFromNaturalCustomerTable(customerId);
    }

    public static NaturalCustomer updateNaturalCustomer(NaturalCustomer naturalCustomer) throws SQLException {
        Integer idOfNew = naturalCustomer.getCustomerId();
        if (NaturalCustomerCRUD.noDuplicateNumberInTable(naturalCustomer.getNationalCode(),idOfNew)) {
            return NaturalCustomerCRUD.updateNaturalCustomerInTable(naturalCustomer);
        } else {
            throw new DuplicateInformationException("duplicate");
        }
    }
}
