<%@ page import="data_access.entity.NaturalCustomer" %>
<%@ page import="java.util.ArrayList" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<html lang="fa">
<head>
    <meta charset="UTF-8">
    <link href=cssfile/styleSheet.css rel=stylesheet>
    <title>جستجوی مشتری حقیقی</title>
</head>
<body>
<div id="wrapper">
    <div class="content">
        <div class="layer">
            <div class="layer-in">
                <%
                    int count = 0;
                    ArrayList<NaturalCustomer> naturalCustomers = (ArrayList<NaturalCustomer>) request.getAttribute("naturalCustomers");
                    if (naturalCustomers.size() > 0) {
                %>
                <br>
                <br>
                <p>نتایج جستجو</p>
                <table id="customers">
                    <thead>
                    <tr>
                        <th>ردیف</th>
                        <th>نام</th>
                        <th>نام خانوادگی</th>
                        <th>نام پدر</th>
                        <th>تاریخ تولد</th>
                        <th>کد ملی</th>
                        <th>شماره مشتری</th>
                        <th>عملیات</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        for (NaturalCustomer naturalCustomer : naturalCustomers) {
                            count++;
                    %>
                    <tr>
                        <td>
                            <%=count%>
                        </td>
                        <td>
                            <%=naturalCustomer.getFirstName()%>
                        </td>
                        <td>
                            <%=naturalCustomer.getLastName()%>
                        </td>
                        <td>
                            <%=naturalCustomer.getFatherName()%>
                        </td>
                        <td>
                            <%=naturalCustomer.getDateOfBirth()%>
                        </td>
                        <td>
                            <%=naturalCustomer.getNationalCode() %>
                        </td>
                        <td>
                            <%=naturalCustomer.getCustomerId()%>
                        </td>

                        <td><a href="UpdateNaturalCustomer?action=delete-natural-customer&id=<%=naturalCustomer.getCustomerId() %>"
                               class=form id="btn1">حذف</a>

                            <a href="UpdateNaturalCustomer?action=send-to-edit-page-natural-customer&id=<%=naturalCustomer.getCustomerId()%>&firstName=<%=naturalCustomer.getFirstName()%>&lastName=<%=naturalCustomer.getLastName()%>&fatherName=<%=naturalCustomer.getFatherName()%>&dateOfBirth=<%=naturalCustomer.getDateOfBirth()%>&nationalCode=<%=naturalCustomer.getNationalCode()%>"
                               class=form id="btn2">ویرایش</a>

                        </td>

                    </tr>
                    <%}%>
                    </tbody>
                </table>
                <%} else {%>
                <h3>مشتری با مشخصات وارد شده وجود ندارد</h3>
                <%}%>

                <a href="../natural-customer-search.html" class=form>بازگشت به صفحه قبل</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>