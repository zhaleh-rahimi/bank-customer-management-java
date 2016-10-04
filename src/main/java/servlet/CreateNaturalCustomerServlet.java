package servlet;

import business_logic.NaturalCustomerLogic;
import data_access.entity.NaturalCustomer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by DOTIN SCHOOL 4 on 10/1/2016.
 */
@WebServlet(name = "CreateNaturalCustomer", urlPatterns = {"/CreateNaturalCustomer"})
public class CreateNaturalCustomerServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        NaturalCustomer naturalCustomer = new NaturalCustomer();

        naturalCustomer.setFirstName(request.getParameter("firstName"));
        naturalCustomer.setLastName(request.getParameter("lastName"));
        naturalCustomer.setFatherName(request.getParameter("fatherName"));
        naturalCustomer.setDateOfBirth(request.getParameter("dateOfBirth"));
        naturalCustomer.setNationalCode(request.getParameter("nationalCode"));
        System.out.println(naturalCustomer.toString());

        //NaturalCustomerLogic.create(naturalCustomer);
        try {
              naturalCustomer.insertNaturalCustomer();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("naturalCustomer", naturalCustomer);
        getServletConfig().getServletContext().getRequestDispatcher("/natural-customer-registration-result.jsp").forward(request, response);


    }


}
