package business_logic;

import business_logic.exceptions.FieldRequiredException;
import data_access.NaturalCustomerCRUD;
import data_access.entity.NaturalCustomer;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by dotinschool3 on 10/3/2016.
 */
public class NaturalCustomerLogic {
    public static void validate(NaturalCustomer naturalCustomer){
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
    }

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

    public static NaturalCustomer updateNaturalCustomer(NaturalCustomer naturalCustomer) {
        return NaturalCustomerCRUD.updateNaturalCustomerInTable(naturalCustomer);
    }
}
