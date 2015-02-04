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
        <title><fmt:message key="register.title"/></title>
    </jsp:attribute>
    <jsp:body>
	<form name="RegisterForm" method="post" action="controller" id="register_form" accept-charset="UTF-8">
		<input type="hidden" name="command" value="register"/>
		<b><fmt:message key="register.login"/></b></br>
		<input type="text" name ="login" placeholder="example123" required="required"/>
		<c:if test="${requestScope.errorLoginPassMessage != null}">
                 <fmt:message key="${requestScope.errorLoginPassMessage}"/>
        </c:if><br/>
		<b><fmt:message key="register.password"/></b></br>
		<input type="password" name ="password" required="required"/><c:if test="${requestScope.passwordErrorKey != null}">
                 <fmt:message key="${requestScope.passwordErrorKey}"/>
             </c:if><br/>
		<b><fmt:message key="register.confirm"/></b></br>
		<input type="password" name ="confirm" required="required"/><br/>
		<b><fmt:message key="register.name"/>:</b><br/>
		<input type="text" name ="name" required="required"/><br/>
		<b><fmt:message key="register.surname" />:</b><br/>
		<input type="text" name="surname" required="required"/><br/>
		<b><fmt:message key="register.birthdate"/>:</b><br/>
		<input type="text" name="birthdate" required="required"/><br/>
		<b><fmt:message key="register.certificate"/>:</b><br/>
		<input type="text" name="certificate" required="required"/><br/>
		<b><fmt:message key="register.math" />:</b><br/>
		<input type="text" name="math" required="required"/><br/>
		<b><fmt:message key="register.physics"/>:</b><br/>
		<input type="text" name="physics" required="required"/><br/>
		<b><fmt:message key="register.russian"/>:</b><br/>
		<input type="text" name="russian" required="required"/><br/>
		<c:if test="${requestScope.logicalErrorKey != null}">
                 <fmt:message key="${requestScope.logicalErrorKey}"/>
             </c:if></br>
		<b><fmt:message key="register.learningform"/>:</b><br/>
		<select name="learning">
		<option value="gov"><fmt:message key="register.learningform.gov"/></option>
		<option value="fee"><fmt:message key="register.learningform.fee"/></option>
		<option value="dlearn"><fmt:message key="register.learningform.dlearn"/></option>
		</select><br/>
		<b><fmt:message key="register.faculty"/>:</b><br/>
		<select name="faculty">
		<option value="fitr">ФИТР</option>
		<option value="msf">МСФ</option>
		<option value="fmmp">ФММП</option>
		<option value="ef">ЭФ</option>
		</select><br/>
		<br/>
		<input type="submit" value="<fmt:message key="register.button"/>"/>
		
	
	</form>

    </jsp:body>
</t:pageTemplate>