package servlet;

import business_logic.NaturalCustomerLogic;
import data_access.entity.NaturalCustomer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by dotinschool3 on 10/4/2016.
 */
@WebServlet(name = "UpdateNaturalCustomerServlet", urlPatterns = "/UpdateNaturalCustomer")
public class UpdateNaturalCustomerServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        request.setCharacterEncoding("UTF-8");
        try {
            if ("edit".equalsIgnoreCase(action)) {

                editNaturalCustomer(request, response);

            }
            if ("delete".equalsIgnoreCase(action)) {
                deleteNaturalCustomer(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private void editNaturalCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {

        NaturalCustomer naturalCustomer = new NaturalCustomer();
        naturalCustomer.setCustomerId(Integer.parseInt(request.getParameter("customerId")));
        naturalCustomer.setFirstName(request.getParameter("firstName"));
        naturalCustomer.setLastName(request.getParameter("lastName"));
        naturalCustomer.setFatherName(request.getParameter("fatherName"));
        naturalCustomer.setDateOfBirth(request.getParameter("dateOfBirth"));
        naturalCustomer.setNationalCode(request.getParameter("nationalCode"));
        NaturalCustomerLogic.updateNaturalCustomer(naturalCustomer);
        System.out.println(naturalCustomer);
        request.setAttribute("naturalCustomer", naturalCustomer);
        getServletConfig().getServletContext().getRequestDispatcher("/natural-customer-edit-result.jsp").forward(request, response);

    }

    private void deleteNaturalCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int customerId = Integer.parseInt(request.getParameter("id"));
        NaturalCustomerLogic.deleteNaturalCustomerByID(customerId);
    }

}
