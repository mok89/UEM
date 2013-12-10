<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.js" type="text/javascript"></script>
<link href="res/css/student.css" media="all" rel="stylesheet" type="text/css" />
<link href="res/css/menu_manager.css" media="all" rel="stylesheet" type="text/css" />
<link href="res/css/manager.css" media="all" rel="stylesheet"type="text/css" />

<script type="text/javascript">
	var timeout         = 500;
	var closetimer		= 0;
	var ddmenuitem      = 0;
	
	function jsddm_open()
	{	jsddm_canceltimer();
		jsddm_close();
		ddmenuitem = $(this).find('ul').eq(0).css('visibility', 'visible');}
	
	function jsddm_close()
	{	if(ddmenuitem) ddmenuitem.css('visibility', 'hidden');}
	
	function jsddm_timer()
	{	closetimer = window.setTimeout(jsddm_close, timeout);}
	
	function jsddm_canceltimer()
	{	if(closetimer)
		{	window.clearTimeout(closetimer);
			closetimer = null;}}
	
	$(document).ready(function()
	{	$('#jsddm > li').bind('mouseover', jsddm_open);
		$('#jsddm > li').bind('mouseout',  jsddm_timer);});
	
	document.onclick = jsddm_close;
</script>

<title>Manager ${user}</title>


<script src="res/jquery-2.0.3.js"> </script>
</head>

<body>
	<header>
			<table>
	<tbody>
		<tr>
			<td>
				<img alt="" src="res/img/logo.png">
			</td>
			<td>
				<font>Welcome ${user}</font>
				<br>
				<font>
					<script type="text/JavaScript"> 
							var mydate=new Date() 
							var year=mydate.getYear() 
							if (year <1000) 
							year+=1900 
							var day=mydate.getDay() 
							var month=mydate.getMonth() 
							var daym=mydate.getDate() 
							if (daym<10) 
							daym="0"+daym 
							var dayarray=new Array("Domenica ","Lunedì ","Martedì ","Mercoledì ","Giovedì ","Venerdì ","Sabato ") 
							var montharray=new Array("/ 01 /","/ 02 /"," / 03 /"," / 04 /","/ 05 /","/ 06 /","/ 07 /","/ 08 /","/ 09 /","/ 10 /","/ 11 /","/ 12 /") 
							document.write(dayarray[day]+" "+daym+" "+montharray[month]+" "+year) 
					</script>
				</font>
			</td>
		</tr>
	</tbody>
</table>
	
	
	</header>
	<div class="container">
		<div class="container-up">
			<ul id="jsddm">
				<li><a href="#">Home</a></li>
				<li><a href="#">Regulations Management</a>
					<ul>
						<li><a href="#">Courses Management</a></li>
						<li><a href="#">Courses Assignment</a></li>
		    		</ul>
				</li>
				<li><a href="#">Management examination session</a></li>
				<li><a href="#">Settings</a></li>
				<li><a href="#">Help</a></li>
			</ul>
		</div>
		<div class="container-left"></div>
		<div class="container-center"></div>
		<div class="container-right"></div>
		<div class="container-down"></div>
	</div>
	<footer></footer>
</body>
</html>