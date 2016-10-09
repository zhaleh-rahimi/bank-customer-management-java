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
                <%
                    Message er = (Message) request.getAttribute("error");%>
                <h2 style="color: chartreuse"><%=er.getHeader()%>
                </h2>
                <h3 style="color: chartreuse "><%=er.getInfo()%>
                </h3>

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