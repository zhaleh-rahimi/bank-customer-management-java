package servlet;

import business_logic.LegalCustomerLogic;
import business_logic.exceptions.DuplicateInformationException;
import business_logic.exceptions.FieldRequiredException;
import data_access.LegalCustomerCRUD;
import data_access.entity.LegalCustomer;
import util.ErrorMessage;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by dotinschool3 on 10/3/2016.
 */
@WebServlet(name = "CreateLegalCustomerServlet", urlPatterns = "/CreateLegalCustomer")
public class CreateLegalCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        LegalCustomer legalCustomer = new LegalCustomer();
        ErrorMessage errorMessage = new ErrorMessage();
        try {

            legalCustomer.setCompanyName(request.getParameter("companyName"));
            legalCustomer.setRegistrationDate(request.getParameter("registrationDate"));
            legalCustomer.setEconomicCode(request.getParameter("economicCode"));
            System.out.println(legalCustomer.toString());


            LegalCustomerLogic.create(legalCustomer);
            request.setAttribute("legalCustomer", legalCustomer);
            getServletConfig().getServletContext().getRequestDispatcher("/legal-customer-registration-result.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FieldRequiredException e) {
            errorMessage.info= "ورود همه فیلدها الزامی است.";
            errorMessage.header="عملیات ناموفق";
            request.setAttribute("error",errorMessage );
            getServletConfig().getServletContext().getRequestDispatcher("/legal-customer-registration-result.jsp").forward(request, response);
        } catch (DuplicateInformationException e) {
            errorMessage.info= "شماره اقتصادی وارد شده تکراری است.";
            errorMessage.header="عملیات ناموفق";
            request.setAttribute("info",errorMessage );
            getServletConfig().getServletContext().getRequestDispatcher("/legal-customer-registration-result.jsp").forward(request, response);
        }



    }

}
