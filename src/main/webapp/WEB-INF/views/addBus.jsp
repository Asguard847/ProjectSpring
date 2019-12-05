<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="/WEB-INF/views/template/header.jsp"%>


<div class="container-wrapper">
    <div class="container">
        <div class="page-header">
            <h1><fmt:message key = "add.bus.header"/></h1>

            <p class="lead"><fmt:message key = "add.bus.fill"/></p>
        </div>

          <form:form action="${pageContext.request.contextPath}/admin/buses/addBus" modelAttribute="bus" method="post">

        <div class="form-group">
            <label for="number"><fmt:message key = "routes.number"/></label><form:errors path="number" cssStyle="color: #ff0000" />
            <form:input type="text" path="number" class="form-Control"/>
        </div>

        <div class="form-group">
            <label for="model"><fmt:message key = "routes.model"/></label></label><form:errors path="model" cssStyle="color: #ff0000" />
            <form:input type="text" path="model" class="form-Control"/>
        </div>

        <br><br>
        <input type="submit" value="<fmt:message key = "button.submit"/>" class="btn btn-default">

        </form:form>

        <%@include file="/WEB-INF/views/template/footer.jsp" %>
