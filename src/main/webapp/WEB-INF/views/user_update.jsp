<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:if test="${sessionScope['scopedTarget.userPref'] == null}" >
    <jsp:forward page="login.jsp" />
</c:if>
<c:if test="${sessionScope['scopedTarget.userPref'].level != 4}" >
    <jsp:forward page="unauthorized.jsp" />
</c:if>

<!DOCTYPE html>
<html>
    <head>
        <title>View User</title>
        <%@include file="/static/include/common.jsp" %>
        
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/static/css/user.css" />
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/static/scripts/user_ajax.js"></script>
    </head>
    <body>
        <%@include file="/static/include/header.jsp" %>
        <div class="wrapper">
            <div class="content">
               
               <!--Context Path to be accessed by javascript for sending Ajax request-->
               <div id="context_path" style="display: none">${pageContext.servletContext.contextPath}</div>
               
                <div class="btn-group">
                    <a role="button" class="btn btn-primary" href="${pageContext.servletContext.contextPath}/user/show?page=view">View User</a>
                    <a role="button" class="btn btn-primary" href="${pageContext.servletContext.contextPath}/user/register/form">Register User</a>
                    <a role="button" class="btn btn-primary  active" href="${pageContext.servletContext.contextPath}/user/show?page=update">Update User</a>
                    <a role="button" class="btn btn-primary" href="${pageContext.servletContext.contextPath}/user/show?page=remove">Remove User</a>
                </div>

                <div style="width: 41%; margin: 20px auto" >

                    <c:if test="${requestScope.action == null}">
                        <p id="message">${requestScope.message}</p>
                        <div>
                            <select name="desgnId" class="form-control" onchange="showUsersUpdate(this.value)">
                                <option value="" >Select Designation</option>
                                <c:forEach items="${applicationScope['scopedTarget.appPref'].desgns}" var="desgn">
                                    <option value="${desgn.id}" >${desgn.name}</option>
                                </c:forEach>   
                            </select>

                        </div>
                        <!-- Content updated by Ajax technology --> 
                        <div id="display_users">

                        </div>
                    </c:if>
                        
                    <c:if test="${requestScope.action eq 'user_details'}">
                       
                    <form:form action="${pageContext.servletContext.contextPath}/user/update" method="post" modelAttribute="userForm">
                       <div class="row">
                           <div class="form-group col-md-12">
                              
                              <div class="col-md-4 align-left">
                                 <label class="control-lable">Employee Id:</label>
                              </div>
                              <div class="col-md-8">
                                 <form:input path="empId" type="hidden"/>
                                 <form:input path="empId" cssClass="form-control" disabled="true"/>
                                 <form:errors path="empId" cssClass="error"/>
                              </div>

                           </div>
                        </div>
                        <div class="row">
                           <div class="form-group col-md-12">
                              
                              <div class="col-md-4 align-left">
                                 <label class="control-lable">Name:</label>
                              </div>
                              <div class="col-md-8">
                                 <form:input path="name" cssClass="form-control" placeholder="Employee Name"/>
                                 <form:errors path="name" cssClass="error"/>
                              </div>

                           </div>
                        </div>
                        <div class="row">
                           <div class="form-group col-md-12">
                              
                              <div class="col-md-4 align-left">
                                 <label class="control-lable">Email:</label>
                              </div>
                              <div class="col-md-8">
                                 <form:input path="email" cssClass="form-control" placeholder="Email [Optional]"/>
                                 <form:errors path="email" cssClass="error"/>
                              </div>

                           </div>
                        </div>
                       <div class="row">
                           <div class="form-group col-md-12">
                              
                              <div class="col-md-4 align-left">
                                 <label class="control-lable">Mobile No:</label>
                              </div>
                              <div class="col-md-8">
                                 <form:input path="mobile" cssClass="form-control" placeholder="Mobile number"/>
                                 <form:errors path="mobile" cssClass="error"/>
                              </div>

                           </div>
                        </div>
                        <div class="row">
                           <div class="form-group col-md-12">
                              
                              <div class="col-md-4  align-left">
                                 <label class="control-lable">Designation:</label>
                              </div>
                              <div class="col-md-8">
                                 <form:select path="desgnId" cssClass="form-control">
                                    <form:option value="" label="Select" />
                                    <form:options items="${applicationScope['scopedTarget.appPref'].desgns}" itemValue="id" itemLabel="name" />
                                 </form:select>
                                 <form:errors path="desgnId" cssClass="error"/>
                                 
                              </div>

                           </div>
                        </div>
                        <div class="row">
                           <div class="col-md-12">
                              <input type="submit" value="Update" class="btn btn-primary" />
                           </div>
                        </div>
                    </form:form>
                    </c:if>


                </div>
            </div>
        </div>
        <%@include file="/static/include/footer.jsp" %>
    </body>
</html>
