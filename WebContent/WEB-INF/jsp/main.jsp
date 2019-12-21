<%@ page language="java" import="by.htp.ts.bean.User" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Welcome</title>

   <fmt:setLocale value="${sessionScope.local}"/>
   <fmt:setBundle basename="resources.local" var="loc"/>
   
   <fmt:message bundle="${loc}" key="local.lang_button_ru" var="button_ru"/>
   <fmt:message bundle="${loc}" key="local.lang_button_en" var="button_en"/>
   

</head>
<body>

   <form action="Controller" method="post">
       <input type="hidden" name="command" value="change_language"/>
       <input type="hidden" name="lang" value="ru"/>
       <input type="submit" value="${button_ru}"/> 
   </form>
   
   <form action="Controller" method="post">
       <input type="hidden" name="command" value="change_language"/>
       <input type="hidden" name="lang" value="en"/>
       <input type="submit" value="${button_en}"/> 
   </form>

<br/>

<% User user=(User)session.getAttribute("user");
   out.println("WELCOME "+user.getName()+"!!!");

%>

   <form action="Controller" method="post">
      <input type="hidden" name="command" value="logout"/>
      <input type="submit" value="Log out"/>
   </form>
</body>
</html>