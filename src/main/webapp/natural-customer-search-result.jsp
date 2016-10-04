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
    <hr class="hrshow">
    <div class="content">
        <div class="layer">
            <div class="layer-in">
                <br>
                <br>
                <p>جستجو بر اساس</p>
                <div class="search-box">
                    <select name="searchFilter">
                        <option value="firstName">نام</option>
                        <option value="LastName">نام خانوادگی</option>
                        <option value="nationalCode">کد ملی</option>
                        <option value="customerId">شماره مشتری</option>
                    </select>
                    <br>
                    <br>
                    <form action="SearchNaturalCustomer" >
                        <input type="text" name="searchValue">
                    </form>
                </div>
                <br>
                <a href="../natural-customer-search-result.html" class="form">جستجو</a>
                <%
                int count = 0;
                ArrayList<NaturalCustomer> naturalCustomers = (ArrayList<NaturalCustomer>) request.getAttribute("naturalCustomers");
                if (naturalCustomers.size() > 0) {
                %>
                <p>نتایج جستجو</p>
                <table>
                    <thead>
                    <tr>
                        <th>نام</th>
                        <th>نام خانوادگی</th>
                        <th>نام پدر</th>
                        <th>تاریخ تولد</th>
                        <th>کد ملی</th>
                        <th>شماره مشتری</th>
                        <th> ویرایش/حذف </th>
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
                        <td><a href=""
                               class=form>حذف</a>
                            <a href=""
                               class=form>ویرایش</a>
                        </td>
                    </tr>
                    <%}%>
                    </tbody>
                </table>
                <%} else {%>
                <h3>مشتری با مشخصات وارد شده وجود ندارد</h3>
                <%}%>

                <a href="../natural-customer-management.html" class=form>بازگشت به صفحه قبل</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>