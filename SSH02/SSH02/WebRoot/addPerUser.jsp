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
		 $(document).ready(function(){$("#searcher").click(function(event){
		 	 	event.preventDefault();
		 	 	var obj = $(this);
		 	 	var url = "jasons/addUserSearcher.action?userid=" + $("#userid").val();
		 	 	var obj2 = $("#setable1");
				$.ajax({
					url: url,
					type: 'get',
					dataType: 'json',
					success: function(d){
						data = eval("("+d+")");
						obj2.append(data.LINE);
					},
					error:function(d){
						$("#del").html("增加用户不正确！");
					}
			});
		})});
	</script>
  </head>
  
  <body>
  	<br /><br /><br /><br />
  	<form action="jasons/addUserSearcher.action">
	  	<table border="0" width="50%" align="center">
	  		<tr >
	  			<td align="center"><s:textfield id="userid" value=""/><s:submit id="searcher" value="Search"/></td>
	  		</tr>
	  	</table>
  	</form>
    <table id="setable1" border="1" width="50%" align="center">
    	<tr bgcolor="#cccc00">
    		<td align="center">UserId</td>
    		<td align="center">UserName</td>
    		<td align="center">Password</td>
    		<td align="center">Authority</td>
    	</tr>
    </table>
    <div id="del"></div>
  </body>
</html>