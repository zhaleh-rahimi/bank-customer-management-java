package servlet;

import business_logic.NaturalCustomerLogic;
import business_logic.exceptions.DuplicateInformationException;
import data_access.entity.NaturalCustomer;
import util.Message;

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
            if ("send-to-edit-page-natural-customer".equalsIgnoreCase(action)) {
                sendDataToEditPage(request, response);
            }
            if ("delete-natural-customer".equalsIgnoreCase(action)) {
                deleteNaturalCustomer(request, response);
            }
            if ("edit-natural-customer".equalsIgnoreCase(action)) {
                editNaturalCustomer(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void sendDataToEditPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        NaturalCustomer naturalCustomer = setNaturalCustomerValues(
                Integer.parseInt(request.getParameter("id")),
                request.getParameter("firstName"),
                request.getParameter("lastName"),
                request.getParameter("fatherName"),
                request.getParameter("dateOfBirth"),
                request.getParameter("nationalCode"));

        request.setAttribute("naturalCustomer", naturalCustomer);
        getServletConfig().getServletContext().getRequestDispatcher("/natural-customer-edit.jsp").forward(request, response);
    }

    private void editNaturalCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        NaturalCustomer naturalCustomer = setNaturalCustomerValues(
                Integer.parseInt(request.getParameter("customerId")),
                request.getParameter("firstName"),
                request.getParameter("lastName"),
                request.getParameter("fatherName"),
                request.getParameter("dateOfBirth"),
                request.getParameter("nationalCode"));

        try {
            naturalCustomer = NaturalCustomerLogic.updateNaturalCustomer(naturalCustomer);

            request.setAttribute("naturalCustomer", naturalCustomer);
            getServletConfig().getServletContext().getRequestDispatcher("/natural-customer-edit-result.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (DuplicateInformationException e){
            Message errorMessage=new Message();
            errorMessage.info = "شماره ملی وارد شده تکراری می باشد.";
            errorMessage.header = "عملیات ناموفق";
            request.setAttribute("error", errorMessage);
            getServletConfig().getServletContext().getRequestDispatcher("/natural-customer-edit-result.jsp").forward(request, response);
        }

    }

    private void deleteNaturalCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int customerId = Integer.parseInt(request.getParameter("id"));
        NaturalCustomerLogic.deleteNaturalCustomerByID(customerId);
        Message message=new Message();
        message.info = "حذف انجام شد ";
        message.header = "عملیات موفق";
        request.setAttribute("error", message);
        getServletConfig().getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

    private NaturalCustomer setNaturalCustomerValues(Integer id, String name, String lastName, String fatherName, String dateOfBirth, String nationalCode) {
        NaturalCustomer naturalCustomer = new NaturalCustomer();
        naturalCustomer.setCustomerId(id);
        naturalCustomer.setFirstName(name);
        naturalCustomer.setLastName(lastName);
        naturalCustomer.setFatherName(fatherName);
        naturalCustomer.setDateOfBirth(dateOfBirth);
        naturalCustomer.setNationalCode(nationalCode);
        return naturalCustomer;
    }
}
