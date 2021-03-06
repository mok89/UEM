<%@page import="it.unical.uniexam.MokException"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="label.welcome" /> ${M.getName()}</title>

<link href="${pageContext.request.contextPath}/res/css/manager.css"
	media="all" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/res/js/jquery-2.0.3.js">
</script>
<script
	src="${pageContext.request.contextPath}/res/js/jquery-sorting.js">
</script>
<script
	src="${pageContext.request.contextPath}/res/js/jquery-ui-1.10.3.custom.js">
</script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/res/css/jquery-ui.css">
	
<script src="${pageContext.request.contextPath}/res/js/pino.js">

</script>

<script
	src="${pageContext.request.contextPath}/res/js/jquery-ui-timepicker-addon.js">
</script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/res/js/jquery-ui-timepicker-addon.css">


<script>
  $(function() {
    $( document ).tooltip({
    	show: {
            effect: "slideDown",
            delay: 150
          },
    track:true
    });
  });
  </script>
</head>
<body>
	<div class="processing">
		<img /> Loading ...
	</div>
	<header>
		<tiles:insertAttribute name="container-header" />
	</header>
	<input type="hidden" id="context"
		value="${pageContext.request.contextPath}/manager" />
	<input type="hidden" id="contextPath"
		value="${pageContext.request.contextPath}" />
	
	<div class="container">
		<tiles:insertAttribute name="container-up" />
		<tiles:insertAttribute name="container-left" />
		<tiles:insertAttribute name="container-center" />
		<tiles:insertAttribute name="container-down" />
	</div>
	<div class="container-footer">
		<footer>
			<tiles:insertAttribute name="footer" />
		</footer>
	</div>
	<!-- fine body ... FINE BODY -->
	<script type="text/javascript">
		$(document).ready(function(){
			<%Map<String,String>map =(Map<String,String>)request.getAttribute("personalizzationMap");
			if(map!=null){
				try{
				if(map.size()>0){
					Set<Entry<String, String>> entrySet = map.entrySet();
					for(Entry<String, String> entry:entrySet){
						String id=entry.getKey();
						String []pairs=entry.getValue().split("%");
						if(pairs.length>0){
							StringBuilder property=new StringBuilder();
							boolean primo=true;
							String []values=null;
							for(String value:pairs){
								 values=value.split("=");
								 if(primo)
									 primo=false;
								 else
									 property.append(",");
								 property.append("\""+values[0]);
								 property.append("\":\"");
								 property.append(values[1]+"\"");
							}%>
					var idd="#<%=id%>";
					$(idd).css({<%=property.toString()%>});
			<%}}}}catch(Exception e){new MokException(e);}
				}%>
		});
	</script>
</body>
</html>
