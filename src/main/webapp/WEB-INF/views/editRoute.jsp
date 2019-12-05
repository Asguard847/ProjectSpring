<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="/WEB-INF/views/template/header.jsp"%>


<div class="container-wrapper">
    <div class="container">
        <div class="page-header">
            <h1><fmt:message key = "edit.routes.header"/></h1>

            <p class="lead"><fmt:message key = "edit.routes.fill"/></p>
        </div>

         <form:form action="${pageContext.request.contextPath}/admin/routes/editRoute/${route.id}" modelAttribute="route" method="post">

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

        <div class="page-header">
            <h1><fmt:message key = "routes.buses"/></h1>
            <p class="lead"><fmt:message key = "edit.routes.add"/></p>
        </div>

          <table class="table table-striped table-hover">
                <thead>
                    <tr class="bg-success">
                        <th><fmt:message key = "routes.number"/></th>
                        <th><fmt:message key = "routes.model"/></th>
                        <th><fmt:message key = "routes.driver"/></th>
                        <th></th>
                    </tr>
                    </thead>
                    <c:forEach items="${route.buses}" var="bus">
                        <tr>
                            <td>${bus.number}</td>
                            <td>${bus.model}</td>
                            <td>${bus.driver.firstName} ${bus.driver.lastName}</td>

                            <td>
                                <a href="<c:url value="/admin/routes/deleteBusFromRoute/${bus.id}"/>">
                                    <button type="button" class="btn btn-danger"><fmt:message key = "button.delete"/></button></a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>

                <br><br>

                <p class="lead"><fmt:message key = "routes.interval"/>: ${route.timeInterval}</p>

               <br><br>

               <div class="form-group">
                   <label for="driverSelect"><fmt:message key = "routes.assign"/></label>
                    <form:select path="newBusId">
                       <form:option value="0" label = "none"/>
                       <form:options items="${buses}" />
                   </form:select>
               </div>
        <br><br>
        <input type="submit" value="<fmt:message key = "button.submit"/>" class="btn btn-default">

        <a href="<c:url value="/admin/routes"/>">
         <button type="button" class="btn btn-secondary"><fmt:message key = "button.back"/></button></a>

       </form:form>

        <br><br>

        <%@include file="/WEB-INF/views/template/footer.jsp" %>
