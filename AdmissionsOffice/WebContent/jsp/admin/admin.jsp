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
    		<th><fmt:message key="main.learningform"/></th>
 	 	</tr>
			<c:forEach var="entrant" items="${entrants}">
				<tr class="alt">
					<td><c:out value="${entrant.name}"/></td>
					<td><c:out value="${entrant.surname}"/></td>
					<td><c:out value="${entrant.birthDate}"/></td>
					<td><c:out value="${entrant.learningForm}"/></td>
				</tr>
			</c:forEach>
		</table>
		</br>
		<form name="Matriculation" method="post" action="controller">
			<input type="hidden" name="command" value="matriculation"/>
			<input type="submit" value="<fmt:message key="admin.main.button"/>"/>
		</form>

    </jsp:body>
</t:pageTemplate>