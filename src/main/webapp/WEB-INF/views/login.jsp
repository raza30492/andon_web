<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
    <head>
        <title>User Login</title>
        <%@include file="/static/include/common.jsp" %>
        
        <link rel="stylesheet" type="text/css" href="index.css" />
        <script type="text/javascript" src="index.js" ></script>
        
    </head>
    <body>
        <%@include file="/static/include/header.jsp" %>
        
        <div class="wrapper">
            <div class="content">
               
               <div style="width: 60%;margin: 20px auto"> 
                    <h4>Login Page</h4>
                    
                    <p id="errorBox">${requestScope.message}</p>
                    
                    <form:form action="${pageContext.servletContext.contextPath}/login/auth" method="post" modelAttribute="loginForm">
                       <div class="row">
                           <div class="form-group col-md-12">
                              <div class="col-md-2"></div>
                              <div class="col-md-2 align-left">
                                 <label class="control-lable">Employee Id:</label>
                              </div>
                              <div class="col-md-5">
                                 <form:input path="empId" cssClass="form-control" placeholder="Enter Employee Id"/>
                              </div>
                              <div class="col-md-3">
                                 <form:errors path="empId" cssClass="error"/>
                              </div>
                           </div>
                        </div>
                        <div class="row">
                           <div class="form-group col-md-12">
                              <div class="col-md-2"></div>
                              <div class="col-md-2 align-left">
                                 <label class="control-lable">Password:</label>
                              </div>
                              <div class="col-md-5">
                                 <form:password path="passwd" cssClass="form-control" placeholder="********"/>
                              </div>
                              <div class="col-md-3">
                                 <form:errors path="passwd" cssClass="error"/>
                              </div>
                           </div>
                        </div>
                        <div class="row">
                           <div class="form-group col-md-12">
                              <div class="col-md-2"></div>
                              <div class="col-md-2  align-left">
                                 <label class="control-lable">Role:</label>
                              </div>
                              <div class="col-md-5">
                                 <form:select path="level" cssClass="form-control">
                                    <form:option value="" label="Select" />
                                    <form:option value="3" label="Management" />
                                    <form:option value="4" label="Admin" />
                                 </form:select>

                                 
                              </div>
                              <div class="col-md-3 align-left">
                                 <form:errors path="level" cssClass="error"/>
                              </div>
                           </div>
                        </div>
                        <div class="row">
                           <div class="col-md-12">
                              <input type="submit" value="Login" class="btn btn-primary" />
                           </div>
                        </div>
                    </form:form>
                    
                </div>
                
            </div>
        </div>
        <%@include file="/static/include/footer.jsp" %>
    </body>
</html>
