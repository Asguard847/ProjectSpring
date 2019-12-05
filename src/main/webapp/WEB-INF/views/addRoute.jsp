<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="/WEB-INF/views/template/header.jsp"%>


<div class="container-wrapper">
    <div class="container">
        <div class="page-header">
            <h1><fmt:message key = "routes.add"/></h1>

            <p class="lead"><fmt:message key = "add.routes.fill"/></p>
        </div>

        <form:form action="${pageContext.request.contextPath}/admin/routes/addRoute" modelAttribute="route" method="post">

        <div class="form-group">
            <label for="number"><fmt:message key = "routes.number"/></label><form:errors path="number" cssStyle="color: #ff0000" />
            <form:input type="text"  path="number" class="form-Control" />
        </div>

        <div class="form-group">
            <label for="start"><fmt:message key = "routes.start"/></label><form:errors path="startPoint" cssStyle="color: #ff0000" />
            <form:input type="text"  path="startPoint" class="form-Control"/>
        </div>

        <div class="form-group">
            <label for="end"><fmt:message key = "routes.end"/></label><form:errors path="endPoint" cssStyle="color: #ff0000" />
            <form:input type="text"  path="endPoint" class="form-Control" />
        </div>

        <div class="form-group">
           <label for="length"><fmt:message key = "routes.length"/>(meters)</label><form:errors path="length" cssStyle="color: #ff0000" />
           <form:input type="text" path="length" class="form-Control"/>
        </div>

        <br><br>
        <input type="submit" value="<fmt:message key = "button.submit"/>" class="btn btn-default">

        </form:form>

        <%@include file="/WEB-INF/views/template/footer.jsp" %>
