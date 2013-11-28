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
	<script type="text/javascript" src="http://malsup.github.com/jquery.form.js"></script>
	<script>
		$(document).ready(function() {
		  var f = $('form');
		  var l = $('#loader'); // loder.gif image
		  var b = $('#button'); // upload button
		  var p = $('#preview'); // preview area
		
		  b.click(function(){
		    // implement with ajaxForm Plugin
		    f.ajaxForm({
		      beforeSend: function(){
		        l.show();
		        b.attr('disabled', 'disabled');
		        p.fadeOut();
		      },
		      success: function(e){
		      	data = eval("("+e+")");
		      	alert(data.imgurl);
		        l.hide();
		        f.resetForm();
		        b.removeAttr('disabled');
		        p.html("Image upload successfully").fadeIn();
		      },
		      error: function(e){
		        b.removeAttr('disabled');
		        p.html(e).fadeIn();
		      }
		    });
		  });
		});
	</script>
  </head>
  
  <body>
  	<br /><br /><br /><br />
  	<!-- loader.gif -->
	<img style="display:none" id="loader" src="images/ajax-loader.gif" alt="Loading...." title="Loading...." />
	<!-- simple file uploading form -->
	<form id="form" action="jasons/uploadImg.action" method="post" enctype="multipart/form-data">
	  <input id="uploadImage" type="file" accept="image/*" name="image" />
	  <input id="button" type="submit" value="Upload">
	</form>
	<!-- preview action or error msgs -->
	<div id="preview" style="display:none"></div>
  </body>
</html>