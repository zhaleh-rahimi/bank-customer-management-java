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
import java.util.ArrayList;

/**
 * Created by dotinschool3 on 10/5/2016.
 */
@WebServlet(name = "SearchLegalCustomerServlet", urlPatterns = "/SearchLegalCustomer")
public class SearchLegalCustomerServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String searchFilter = request.getParameter("searchFilter");
        String searchValueStr = request.getParameter("searchValue");

        System.out.println(searchFilter);
        System.out.println(searchValueStr);

        ArrayList<LegalCustomer> legalCustomers = null;
        try {
            if (searchFilter.equals("companyName")) {
                legalCustomers = LegalCustomerLogic.searchByCompanyName(searchValueStr);
            } else if (searchFilter.equals("economicCode")) {
                legalCustomers = LegalCustomerLogic.searchByEconomicCode(searchValueStr);
            } else if (searchFilter.equals("customerId")) {
                legalCustomers = LegalCustomerLogic.searchById(searchValueStr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(legalCustomers);
        request.setAttribute("legalCustomers", legalCustomers);
        getServletConfig().getServletContext().getRequestDispatcher("/legal-customer-search-result.jsp").forward(request, response);

    }
}
