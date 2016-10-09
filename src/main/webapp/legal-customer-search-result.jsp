<%@ page import="data_access.entity.LegalCustomer" %>
<%@ page import="java.util.ArrayList" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<html lang="fa">
<head>
    <meta charset="UTF-8">
    <link href=cssfile/styleSheet.css rel=stylesheet>
    <title>نتیجه جستجو</title>
</head>
<body>
<div id="wrapper">
    <div class="content">
        <div class="layer">
            <div class="layer-in">
                <%
                    int count = 0;
                    ArrayList<LegalCustomer> legalCustomers = (ArrayList<LegalCustomer>) request.getAttribute("legalCustomers");
                    if (legalCustomers.size() > 0) {
                %>
                <br>
                <br>
                <p>نتایج جستجو</p>
                <table id="customers">
                    <thead>
                    <tr>
                        <th>ردیف</th>
                        <th>نام شرکت</th>
                        <th>تاریخ ثبت</th>
                        <th>شماره اقتصادی</th>
                        <th>شماره مشتری</th>
                        <th>عملیات</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        for (LegalCustomer legalCustomer : legalCustomers) {
                            count++;
                    %>
                    <tr>
                        <td>
                            <%=count%>
                        </td>
                        <td>
                            <%=legalCustomer.getCompanyName()%>
                        </td>
                        <td>
                            <%=legalCustomer.getRegistrationDate()%>
                        </td>
                        <td>
                            <%=legalCustomer.getEconomicCode()%>
                        </td>
                        <td>
                            <%=legalCustomer.getCustomerId()%>
                        </td>
                        <td><a href="UpdateLegalCustomer?action=delete-legal-customer&id=<%=legalCustomer.getCustomerId() %>"
                               class=form id="btn1">حذف</a>

                            <a href="UpdateLegalCustomer?action=send-to-edit-page-legal-customer&id=<%=legalCustomer.getCustomerId()%>&companyName=<%=legalCustomer.getCompanyName()%>&registrationDate=<%=legalCustomer.getRegistrationDate()%>&economicCode=<%=legalCustomer.getEconomicCode()%>"
                               class=form id="btn2">ویرایش</a>
                        </td>

                    </tr>
                    <%}%>
                    </tbody>
                </table>
                <%} else {%>
                <h3>مشتری با مشخصات وارد شده وجود ندارد</h3>
                <%}%>

                <a href="../legal-customer-search.html" class=form>بازگشت به صفحه قبل</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>