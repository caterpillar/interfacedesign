<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="org.springframework.security.web.csrf.DefaultCsrfToken" %>
<%--
  Created by IntelliJ IDEA.
  User: lishaohua
  Date: 16-6-2
  Time: 下午4:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form name="f" action="/user/login" method="POST">
    <table>
        <tbody>
        <tr>
            <td>User ssdsd:</td>
            <td><input type="text" name="username" value=""></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td colspan="2"><input name="submit" type="submit" value="Login"></td>
        </tr>
        <%
            session.getAttribute("_csrf.parameterName");
            DefaultCsrfToken token = ((DefaultCsrfToken)session.getAttribute("org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN"));
            if(token != null) {
                System.out.println(token.getToken());
            }
        %>

        <tr>
            <td>${_csrf.token} | ${_csrf.parameterName} | ${_csrf.headerName}</td>
        </tr>
        <input type="hidden" name="_csrf" value="${org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN.token}"/>
        <%--<input type="hidden" name="<%=((DefaultCsrfToken)session.getAttribute("org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN")).getParameterName()%>" value="<%=((DefaultCsrfToken)session.getAttribute("org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN")).getToken()%>" />--%>
        </tbody>
    </table>
</form>
</body>
</html>
