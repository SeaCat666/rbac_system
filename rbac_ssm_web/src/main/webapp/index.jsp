<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="Bookmark" type="image/x-icon" href="WEB-INF/favicon.ico" />
    <link rel="icon" type="image/x-icon" href="WEB-INF/favicon.ico" />
    <link rel="shortcut icon" type="image/x-icon" href="WEB-INF/favicon.ico" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>主页</title>
</head>
<body>
    <%--<a href="${pageContext.request.contextPath}/product/findAll.do">查询产品信息</a>--%>
    <jsp:forward page="${pageContext.request.contextPath}/pages/main.jsp"></jsp:forward>
</body>
</html>