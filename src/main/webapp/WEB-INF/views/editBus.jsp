<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="/WEB-INF/views/template/header.jsp"%>


<div class="container-wrapper">
    <div class="container">
        <div class="page-header">
            <h1><fmt:message key = "edit.bus.header"/></h1>

            <p class="lead"><fmt:message key = "edit.bus.fill"/></p>
        </div>


           <form:form action="${pageContext.request.contextPath}/admin/buses/editBus/${bus.id}" modelAttribute="bus" method="post">

               <div class="form-group">
                   <label for="number"><fmt:message key = "routes.number"/></label><form:errors path="number" cssStyle="color: #ff0000" />
                   <form:input type="text" path="number" class="form-Control"/>
               </div>

               <div class="form-group">
                   <label for="model"><fmt:message key = "routes.model"/></label></label><form:errors path="model" cssStyle="color: #ff0000" />
                   <form:input type="text" path="model" class="form-Control"/>
               </div>

        <br>

         <label class="radio-inline">
             <form:radiobutton path="ready" value="true" checked="checked"/><fmt:message key = "driver.ready"/>
            </label>
            <label class="radio-inline">
              <form:radiobutton path="ready" value="false"/><fmt:message key = "driver.notready"/>
            </label>

            <br><br>

        <h4><fmt:message key = "bus.driver"/></h4>

        <c:choose>
              <c:when test="${bus.driver==null}">
                   <p style="color: crimson"><fmt:message key = "option.none"/></p>
              </c:when>
              <c:otherwise>
                   <p>${bus.driver.firstName} ${bus.driver.lastName}</p>
              </c:otherwise>
        </c:choose>

    <br>


     <fmt:message key = "bus.assign"/> <input type="checkbox" id="myCheck"  name="check" onclick="myFunction()">

     <script>
     function myFunction() {
       var checkBox = document.getElementById("myCheck");
       var select = document.getElementById("driverSelect");
       var vis = "none";
       if (checkBox.checked == true){
         vis = "block";
        }
       select.style.display = vis;
      }
     </script>

     <div class="form-group" id="driverSelect" style="display:none">
          <form:select path="driver.id">
                <form:option value="0" label="none"/>
                <form:options items="${drivers}" />
           </form:select>
     </div>

     <br><br>

        <input type="submit" value="<fmt:message key = "button.submit"/>" class="btn btn-default">
        </form:form>

        <br><br>




<%@include file="/WEB-INF/views/template/footer.jsp" %>
