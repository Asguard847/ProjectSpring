<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="/WEB-INF/views/template/header.jsp"%>


<div class="container-wrapper">
    <div class="container">
        <div class="page-header">
            <h1><fmt:message key = "edit.driver.header"/></h1>

            <p class="lead"><fmt:message key = "edit.driver.fill"/></p>
        </div>

       <form:form action="${pageContext.request.contextPath}/admin/drivers/editDriver/${driver.id}" modelAttribute="driver" method="post" enctype="multipart/form-data">

              <div class="form-group">
                  <label for="firstName"><fmt:message key = "driver.first"/></label><form:errors path="firstName" cssStyle="color: #ff0000" />
                  <form:input type="text" path="firstName" class="form-Control"/>
              </div>

              <div class="form-group">
                  <label for="lastName"><fmt:message key = "driver.last"/></label><form:errors path="lastName" cssStyle="color: #ff0000" />
                   <form:input type="text"  path="lastName" class="form-Control" />
              </div>

             <div class="form-group">
                  <label for="phoneNumber"><fmt:message key = "driver.phone"/></label><form:errors path="phoneNumber" cssStyle="color: #ff0000" />
                 <form:input type="text" path="phoneNumber" class="form-Control"/>
              </div>

            <div class="form-group">
                  <label for="email"><fmt:message key = "driver.email"/></label><form:errors path="email" cssStyle="color: #ff0000" />
                  <form:input type="text" path="email" class="form-Control"/>
            </div>



              <div class="form-group">
                  <label class="control-label" for="driverImage"><fmt:message key = "driver.image"/></label>
                  <form:input id="driverImage" path="driverImage" type="file"  class="form:input-large"/>
              </div>

              <br><br>
              <input type="submit" value="<fmt:message key = "button.submit"/>" class="btn btn-default">

              </form:form>

<%@include file="/WEB-INF/views/template/footer.jsp" %>
