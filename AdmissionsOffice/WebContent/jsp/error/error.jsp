<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
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
        <title><fmt:message key="main.title"/></title>
    </jsp:attribute>
    <jsp:body>
		<c:if test="${requestScope.alertDangerKey != null}">
                 <fmt:message key="${requestScope.alertDangerKey}"/>
        </c:if><br/>
    </jsp:body>
</t:pageTemplate>
