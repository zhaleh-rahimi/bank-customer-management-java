<%@ page import="data_access.entity.NaturalCustomer" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<html lang="fa">
<head>
    <meta charset="UTF-8">
    <link href=cssfile/styleSheet.css rel=stylesheet>
    <title>ثبت مشتری حقیقی جدید</title>
</head>
<body>
<div id="wrapper">
    <hr class="hr">
    <div class="content">
        <div class="layer">
            <div class="layer-in">
                <br>
                <p> نتیجه ثبت مشتری حقیقی:</p>
                <br>
                <div class="result-table">
                <table>
                    <tr>
                        <th>نام</th>
                        <th>نام خانوادگی</th>
                        <th>نام پدر</th>
                        <th>تاریخ تولد</th>
                        <th>شماره ملی</th>
                        <th>شماره مشتری
                        </th>
                    </tr>
                    <tbody>
                    <%
                        NaturalCustomer naturalCustomer = (NaturalCustomer) request.getAttribute("naturalCustomer");
                    %>
                    <tr>

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
                            <%=naturalCustomer.getDateOfBirth() %>
                        </td>
                        <td>
                            <%=naturalCustomer.getNationalCode() %>
                        </td>
                        <td>
                            <div class="newId">
                            <%=naturalCustomer.getCustomerId() %>
                            </div>
                        </td>

                    </tr>
                    </tbody>
                </table>
            </div>
            <a href="../customer-type-selection.html" class=form>بازگشت به خانه</a>
        </div>
    </div>
</div>
</div>
</body>
</html>