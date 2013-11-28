<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>update.jsp</title>
  </head>
  
  <body>
    <s:form action="update.action" method="post">
    	<s:hidden name="id" value="%{#request.user.id}" />
		<s:textfield name="name" label="User Name:" value="%{#request.user.userName}" />
		<s:password name="password" label="Password:" />
		<s:textfield name="authority" label="authority:" value="%{#request.user.authority}" />
		<s:submit value="Update" />
    </s:form>
  </body>
</html>