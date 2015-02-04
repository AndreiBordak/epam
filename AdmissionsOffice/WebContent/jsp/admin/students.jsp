<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@	taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="configuration.locale"/>
<t:pageTemplate>
	    <jsp:attribute name="head">
        <title><fmt:message key="admin.main.title"/></title>
    </jsp:attribute>
    <jsp:body>

		<table id="entrants">
		<tr>
    		<th><fmt:message key="main.name"/></th>
    		<th><fmt:message key="main.surname"/></th>
    		<th><fmt:message key="main.birthdate"/></th>
    		<th><fmt:message key="main.facultyname"/></th>
    		<th><fmt:message key="main.learningform"/></th>
    		<th><fmt:message key="main.points"/></th>
 	 	</tr>
			<c:forEach var="student" items="${students}">
				<tr class="alt">
					<td><c:out value="${student.name}"/></td>
					<td><c:out value="${student.surname}"/></td>
					<td><c:out value="${student.birthDate}"/></td>
					<td><c:out value="${student.facName}"/></td>
					<td><c:out value="${student.learningform}"/></td>
					<td><c:out value="${student.sum}"/></td>
				</tr>
			</c:forEach>
		</table>

		<form name="Delete" method="post" action="controller">
			<input type="hidden" name="command" value="deletestudents"/>
			<input type="submit" value="<fmt:message key="admin.delete.button"/>"/>
		</form>
    </jsp:body>
</t:pageTemplate>