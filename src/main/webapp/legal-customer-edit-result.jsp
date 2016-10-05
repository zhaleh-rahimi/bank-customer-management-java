<%@ page import="data_access.entity.NaturalCustomer" %>
<%@ page import="data_access.entity.LegalCustomer" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<html lang="fa">
<head>
    <meta charset="UTF-8">
    <link href=cssfile/styleSheet.css rel=stylesheet>
    <title>نتیجه به روز رسانی مشتری حقوقی </title>
</head>
<body>
<div id="wrapper">
    <hr class="hr">
    <div class="content">
        <div class="layer">
            <div class="layer-in">
                <br>
                <p> نتیجه به روز رسانی مشتری حقوقی:</p>
                <p style="color: chartreuse">موفقیت آمیز</p>
                <br>
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
                            <%=legalCustomer.getCustomerId() %>
                        </td>

                    </tr>
                    </tbody>
                </table>
                <a href="../customer-type-selection.html" class=form>بازگشت به خانه</a>
            </div>
        </div>
    </div>
</div>
</body>
</html></title>
</head>
<body>

</body>
</html>