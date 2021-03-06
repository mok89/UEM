<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<link href="${pageContext.request.contextPath}/res/css/menuTree.css"
	media="all" rel="stylesheet" type="text/css" />

<script type="text/javascript">
	$(document).ready(function () {
		$("#nav li:has(ul)").click(function(event) {
			if (this == event.target) {
				$(this).toggleClass('clicked').children('ul').slideToggle();
				$(this).find('li:has(ul)').removeClass('clicked').find("ul").slideUp();
				$(this).siblings().removeClass('clicked').find("ul").slideUp();
				alineamentoContainer();
			}
		}).addClass('has_ul');
	});
</script>

<div class="container-left">
	<label><b>Quick Links:</b></label>
	<br>
	<ul id="nav">
		<li><spring:message code="label.secretary.department" />
			<ul>
				<li><a href="${pageContext.request.contextPath}/secretary/registerDepartment"><spring:message code="label.secretary.add" /></a></li>
				<li><a href='#'><spring:message code="label.secretary.list" /></a></li>
			</ul>
		</li>
		<li><spring:message code="label.secretary.degreeCourse" />
			<ul>
				<li><a href='#'><spring:message code="label.secretary.add" /></a></li>
				<li><a href='#'><spring:message code="label.secretary.list" /></a></li>
				<li><spring:message code="label.secretary.courses" />
					<ul>
						<li><a href='#'><spring:message code="label.secretary.add" /></a></li>
						<li><a href='#'><spring:message code="label.secretary.list" /></a></li>
					</ul>
				</li>
			</ul>
		</li>
		<li><spring:message code="label.secretary.manager" />
			<ul>
				<li><a href='${pageContext.request.contextPath}/secretary/registerManager'><spring:message code="label.secretary.add" /></a></li>
				<li><a href='#'><spring:message code="label.secretary.list" /></a></li>
			</ul>
		</li>
		<li><spring:message code="label.secretary.professor" />
			<ul>
				<li><a href='#'><spring:message code="label.secretary.add" /></a></li>
				<li><a href='#'><spring:message code="label.secretary.list" /></a></li>
			</ul>
		</li>
		<li><spring:message code="label.secretary.student" />
			<ul>
				<li><a href='#'><spring:message code="label.secretary.add" /></a></li>
				<li><a href='#'><spring:message code="label.secretary.list" /></a></li>
			</ul>
		</li>
	</ul>
</div>