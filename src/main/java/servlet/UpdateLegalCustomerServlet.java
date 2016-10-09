package servlet;

import business_logic.LegalCustomerLogic;
import data_access.entity.LegalCustomer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by dotinschool3 on 10/5/2016.
 */
@WebServlet(name = "UpdateLegalCustomerServlet", urlPatterns = "/UpdateLegalCustomer")
public class UpdateLegalCustomerServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        request.setCharacterEncoding("UTF-8");
        if ("send-to-edit-page-legal-customer".equalsIgnoreCase(action)) {
            sendDataToEditPage(request, response);
        }
        if ("delete-legal-customer".equalsIgnoreCase(action)) {
            try {
                deleteLegalCustomer(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if ("edit-legal-customer".equalsIgnoreCase(action)) {
            editLegalCustomer(request, response);
        }
    }

    private void editLegalCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LegalCustomer legalCustomer = setLegalCustomerValues(
                Integer.parseInt(request.getParameter("customerId")),
                request.getParameter("companyName"),
                request.getParameter("registrationDate"),
                request.getParameter("economicCode")
        );
        legalCustomer = LegalCustomerLogic.updateLegalCustomer(legalCustomer);
        request.setAttribute("legalCustomer",legalCustomer);
        getServletConfig().getServletContext().getRequestDispatcher("/legal-customer-edit-result.jsp").forward(request,response);

    }

    private void deleteLegalCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int customerId = Integer.parseInt(request.getParameter("id"));
        LegalCustomerLogic.deleteLegalCustomerByID(customerId);
    }

    private void sendDataToEditPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LegalCustomer legalCustomer = setLegalCustomerValues(
                Integer.parseInt(request.getParameter("id")),
                request.getParameter("companyName"),
                request.getParameter("registrationDate"),
                request.getParameter("economicCode")
        );
        request.setAttribute("legalCustomer",legalCustomer);
        getServletConfig().getServletContext().getRequestDispatcher("/legal-customer-edit.jsp").forward(request,response);
    }

    private LegalCustomer setLegalCustomerValues(Integer id, String name,String registrationDate, String economicCode ) {
        LegalCustomer legalCustomer = new LegalCustomer();
        legalCustomer.setCustomerId(id);
        legalCustomer.setCompanyName(name);
        legalCustomer.setRegistrationDate(registrationDate);
        legalCustomer.setEconomicCode(economicCode);

        return legalCustomer;
    }

}
