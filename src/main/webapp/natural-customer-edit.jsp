<%@ page import="data_access.entity.NaturalCustomer" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<html lang="fa">
<head>
    <meta charset="UTF-8">
    <link href=cssfile/styleSheet.css rel=stylesheet>
    <title>ویرایش مشتری حقیقی </title>
</head>
<body>
<div id="wrapper">
    <div class="content">
        <div class="layer">
            <div class="layer-in">
                <br>
                <p>لطفا اطلاعات مشتری را وارد نمایید.</p>
                <br>

                <form action="UpdateNaturalCustomer" name="edit" method="get" >
                    <input type="hidden" name="action" value="edit-natural-customer">
                    <%
                        NaturalCustomer naturalCustomer = (NaturalCustomer) request.getAttribute("naturalCustomer");
                    %>
                    <table>
                        <tr>
                            <td>شماره مشتری</td>
                            <td><input type='text' name='customerId' value="<%=naturalCustomer.getCustomerId()%>"
                                       readonly></td>
                        </tr>
                        <tr>
                            <td> نام</td>
                            <td><input type='text' name='firstName' value="<%=naturalCustomer.getFirstName()%>"></td>
                        </tr>
                        <tr>
                        <td> نام خانوادگی</td>
                        <td><input type='text' name='lastName' value="<%=naturalCustomer.getLastName()%>"></td>
                        </tr>
                        <tr>
                        <td> نام پدر</td>
                        <td><input type='text' name='fatherName' value="<%=naturalCustomer.getFatherName()%>"></td>
                        </tr>
                        <tr>
                            <td> تاریخ تولد</td>
                            <td><input type="text" name='dateOfBirth' value="<%=naturalCustomer.getDateOfBirth()%>">
                            </td>
                        </tr>
                        <tr>
                            <td>کد ملی</td>
                            <td><input type='text' name='nationalCode'
                                       value="<%=naturalCustomer.getNationalCode()%>"></td>
                        </tr>
                    </table>

                    <input type="submit"  class="button" value="به روز رسانی">
                    <a href="../customer-type-selection.html" class="form">بازگشت به خانه</a>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>