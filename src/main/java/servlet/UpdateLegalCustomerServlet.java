package servlet;

import business_logic.LegalCustomerLogic;
import business_logic.exceptions.DuplicateInformationException;
import data_access.entity.LegalCustomer;
import util.Message;

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
            try {
                editLegalCustomer(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void editLegalCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        LegalCustomer legalCustomer = setLegalCustomerValues(
                Integer.parseInt(request.getParameter("customerId")),
                request.getParameter("companyName"),
                request.getParameter("registrationDate"),
                request.getParameter("economicCode")
        );
        try {
            legalCustomer = LegalCustomerLogic.updateLegalCustomer(legalCustomer);
            request.setAttribute("legalCustomer", legalCustomer);
            getServletConfig().getServletContext().getRequestDispatcher("/legal-customer-edit-result.jsp").forward(request, response);
        }catch (DuplicateInformationException e){
            Message errorMessage=new Message();
            errorMessage.info="شماره اقتصادی وارد شده تکراری است.";
            errorMessage.header="عملیات ناموفق";
            request.setAttribute("error", errorMessage);
            getServletConfig().getServletContext().getRequestDispatcher("/legal-customer-edit-result.jsp").forward(request, response);
        }

    }

    private void deleteLegalCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int customerId = Integer.parseInt(request.getParameter("id"));
        LegalCustomerLogic.deleteLegalCustomerByID(customerId);
        Message message=new Message();
        message.info = "حذف انجام شد ";
        message.header = "عملیات موفق";
        request.setAttribute("error", message);
        getServletConfig().getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
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
