<%@ page import="data_access.entity.LegalCustomer" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<html lang="fa">
<head>
    <meta charset="UTF-8">
    <link href=cssfile/styleSheet.css rel=stylesheet>
    <title>ویرایش مشتری حقوقی </title>
</head>
<body>
<div id="wrapper">
    <hr class="hr">
    <div class="content">
        <div class="layer">
            <div class="layer-in">
                <br>
                <p>لطفا اطلاعات مشتری را وارد نمایید.</p>
                <br>

                <form action="UpdateLegalCustomer" name="edit" method="get" >
                    <input type="hidden" name="action" value="edit-legal-customer">
                    <table>
                        <%
                            LegalCustomer legalCustomer = (LegalCustomer) request.getAttribute("legalCustomer");
                        %>
                        <tr>
                            <td>شماره مشتری</td>
                            <td><input type='text' name='customerId' value="<%=legalCustomer.getCustomerId()%>"
                                       readonly></td>
                        </tr>
                        <tr>
                            <td> نام شرکت</td>
                            <td><input type="text" name="companyName" value="<%=legalCustomer.getCompanyName()%>"></td>
                        </tr>
                        <tr>
                            <td> تاریخ ثبت</td>
                            <td><input type="DATE" name="lastName" value="<%=legalCustomer.getRegistrationDate()%>"></td>
                        </tr>
                        <tr>
                            <td>شماره اقتصادی</td>
                            <td><input type="text" name="economicCode" value="<%=legalCustomer.getEconomicCode()%>"></td>
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