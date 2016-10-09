package servlet;

import business_logic.NaturalCustomerLogic;
import business_logic.exceptions.DataNotFoundException;
import data_access.entity.NaturalCustomer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created by dotinschool3 on 10/3/2016.
 */
@WebServlet(name = "SearchNaturalCustomerServlet" , urlPatterns = "/SearchNaturalCustomer")
public class SearchNaturalCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String searchFilter = request.getParameter("searchFilter");
        String searchValueStr = request.getParameter("searchValue");

        System.out.println(searchFilter);
        System.out.println(searchValueStr);

        ArrayList<NaturalCustomer> naturalCustomers = null;

        try {
            if(searchFilter.equals("firstName")){
            naturalCustomers = NaturalCustomerLogic.searchByFirstName(searchValueStr);}
            else if(searchFilter.equals("lastName")){
                naturalCustomers = NaturalCustomerLogic.searchByLastName(searchValueStr);
            }
            else if(searchFilter.equals("nationalCode")){
                naturalCustomers = NaturalCustomerLogic.searchByNationalCode(searchValueStr);
            }
            else if(searchFilter.equals("customerId")){
                naturalCustomers = NaturalCustomerLogic.searchById(searchValueStr);
            }
            System.out.println(naturalCustomers);
            request.setAttribute("naturalCustomers", naturalCustomers);
            getServletConfig().getServletContext().getRequestDispatcher("/natural-customer-search-result.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        }catch (DataNotFoundException e){
            e.getMessage();
        }


    }


}
