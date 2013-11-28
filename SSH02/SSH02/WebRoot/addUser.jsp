<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>addUser.jsp</title>
  </head>
  
  <body>
    <s:form action="save.action" method="post">
	<s:textfield name="name" label="User Name:" />
	<s:password name="password" label="Password:" />
	<s:submit value="Save" />
    </s:form>
  </body>
</html>