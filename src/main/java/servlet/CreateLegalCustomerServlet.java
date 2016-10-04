package servlet;

import business_logic.LegalCustomerLogic;
import data_access.LegalCustomerCRUD;
import data_access.entity.LegalCustomer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dotinschool3 on 10/3/2016.
 */
@WebServlet(name = "CreateLegalCustomerServlet", urlPatterns = "/CreateLegalCustomer")
public class CreateLegalCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LegalCustomer legalCustomer = new LegalCustomer();
        legalCustomer.setCompanyName(request.getParameter("companyName"));
        legalCustomer.setRegistrationDate(request.getParameter("registrationDate"));
        legalCustomer.setEconomicCode(request.getParameter("economicCode"));
        System.out.println(legalCustomer.toString());

        LegalCustomerLogic.create(legalCustomer);

        RequestDispatcher view = request.getRequestDispatcher("legal-customer-registration-result.html");
        view.forward(request, response);

    }

}
