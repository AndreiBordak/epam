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
        <title><fmt:message key="main.title"/></title>
    </jsp:attribute>
    <jsp:body>
	<fmt:message key="unregister.message"/><br/>
	<form name="LoginForm" method="post" action="controller" >
		
		<hr/>
		<input type="hidden" name="command" value="unregister" />
		<br/><fmt:message key="login.password"/>:<br/>
		<input type="password" name="password" value="" required="required"/>
			<br/>		
             <c:if test="${requestScope.passwordErrorKey != null}">
                 <fmt:message key="${requestScope.passwordErrorKey}"/>
             </c:if>
			<br/>
			
		<input type="submit" value="<fmt:message key="register.button"/>" size="100"/>
	</form>
	
    </jsp:body>
</t:pageTemplate>
