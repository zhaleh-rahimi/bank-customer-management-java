<%@ page import="data_access.entity.LegalCustomer" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fa">
<head>
    <meta charset="UTF-8">
    <link href=cssfile/styleSheet.css rel=stylesheet>
    <title>ثبت مشتری حقوقی جدید</title>
</head>
<body>
<div id="wrapper">
    <hr class="hrshow">
    <div class="content">
        <div class="layer">
            <div class="layer-in">
                <br>
                <p>  نتیجه ثبت مشتری حقوقی :</p>
                <br>
                <div class="result-table">
                    <table>
                        <tr>
                            <th>نام شرکت</th>
                            <th>تاریخ ثبت</th>
                            <th>شماره اقتصادی</th>
                            <th>شماره مشتری
                            </th>
                        </tr>
                        <tbody>
                        <%
                            LegalCustomer legalCustomer = (LegalCustomer) request.getAttribute("legalCustomer");
                        %>
                        <tr>

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
                                <div class="newId">
                                <%=legalCustomer.getCustomerId() %>
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