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
        <title><fmt:message key="login.title"/></title>
    </jsp:attribute>
    <jsp:body>
	<form name="LoginForm" method="post" action="controller" id="login_form" >
		
		<hr/>
		<fmt:message key="login.message"/></br>
		<input type="hidden" name="command" value="login" />
		<fmt:message key="login.login"/>:<br/>
		<input type="text" name="login" value="" required="required"/>
		<br/><fmt:message key="login.password"/>:<br/>
		<input type="password" name="password" value="" required="required"/>
			<br/>		
             <c:if test="${requestScope.errorLoginPassMessage != null}">
                 <fmt:message key="${requestScope.errorLoginPassMessage}"/>
             </c:if>
			<br/>
			
		<input type="submit" value="<fmt:message key="login.signup"/>" size="100"/>
	</form>
	<form name="registration" method="post" action="controller">
		<input type="hidden" name="command" value="registerform"/>
		</br>
		<input type="submit" value="<fmt:message key="login.register"/>"/>
	</form>
    </jsp:body>
</t:pageTemplate>

