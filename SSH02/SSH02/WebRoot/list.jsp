<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Users list</title>
    <style type="text/css">
    	table {
			border-collapse: collapse;
		}
    </style>
    <link href="css/styles.css" rel="stylesheet" type="text/css" />
	<link href="css/jquery.validator.css" rel="stylesheet" type="text/css" />
	<link href="css/jquery.validator.css" rel="stylesheet" type="text/css" />
	<link href='http://fonts.googleapis.com/css?family=Ubuntu+Condensed' rel='stylesheet' type='text/css' />
	<script type="text/javascript" src="js/jquery-2.0.3.js"></script>
	<script type="text/javascript" src="js/jquery-migrate-1.2.1.js"></script>
	<script type="text/javascript" src="js/jquery.validator.js"></script>
	<script type="text/javascript" src="js/local/zh_CN.js"></script>
	<script type="text/javascript">
		 $(document).ready(function(){$("a").click(function(event){
		 	 	event.preventDefault()
		 	 	var obj = $(this);
		 	 	var url = obj.attr("href");
				$.ajax({
					url: url,
					type: 'get',
					dataType: 'json',
					success: function(d){
						obj.parents("tr").hide();
					},
					error:function(d){
						$("#del").html("删除不正确！");
					}
			});
		})});
	</script>
  </head>
  
  <body>
  	<br /><br /><br /><br />
  	<div id="del"></div>
    <table border="1" width="50%" align="center">
    	<tr bgcolor="#cccc00">
    		<td align="center">UserId</td>
    		<td align="center">UserName</td>
    		<td align="center">Password</td>
    		<td align="center">Authority</td>
    		<td colspan="2" align="center">Options</td>
    	</tr>
    	<s:iterator value="%{#request.list}" var="user">
    	<tr>
    		<td align="center"><s:property value="#user.id" /></td>
    		<td align="center">e<s:property value="#user.userName" /></td>
    		<td align="center">&nbsp;<s:property value="#user.password" /></td>
    		<td align="center">&nbsp;<s:property value="#user.authority" /></td>
    		<td align="center"><a href="update.action?id=<s:property value='#user.id' />&action=loading" >update</a></td>
    		<td align="center"><a href="jasons/jsondelete.action?id=<s:property value='#user.id' />" >delete</a></td>
    	</tr>
    	</s:iterator>
    </table>
  </body>
</html>