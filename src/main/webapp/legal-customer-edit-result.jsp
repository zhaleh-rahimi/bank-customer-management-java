<%@ page import="data_access.entity.LegalCustomer" %>
<%@ page import="util.Message" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<html lang="fa">
<head>
    <meta charset="UTF-8">
    <link href=cssfile/styleSheet.css rel=stylesheet>
    <title>نتیجه به روز رسانی مشتری حقوقی </title>
</head>
<body>
<div id="wrapper">
    <div class="content">
        <div class="layer">
            <div class="layer-in">
                <br>
                <p> نتیجه به روز رسانی مشتری حقوقی:</p>

                    <%
                        LegalCustomer legalCustomer = (LegalCustomer) request.getAttribute("legalCustomer");
                        if(legalCustomer!=null){
                    %>
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
                <% } else {
                    Message er= (Message) request.getAttribute("error");%>
                <h2 style="color: #b80000"><%=er.getHeader()%></h2>
                <h3 style="color: #b80000 "><%=er.getInfo()%></h3>
                <% }%>
                <a href="../customer-type-selection.html" class=form>بازگشت به خانه</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
</title>
</head>
<body>

</body>
</html>