<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
                    <a role="button" class="btn btn-primary active" href="${pageContext.servletContext.contextPath}/user/show?page=view">View User</a>
                    <a role="button" class="btn btn-primary" href="${pageContext.servletContext.contextPath}/user/register/form">Register User</a>
                    <a role="button" class="btn btn-primary" href="${pageContext.servletContext.contextPath}/user/show?page=update">Update User</a>
                    <a role="button" class="btn btn-primary" href="${pageContext.servletContext.contextPath}/user/show?page=remove">Remove User</a>
                </div>

                <div style="width: 41%; margin: 20px auto" >

                    <c:if test="${requestScope.action == null}">
                        <p id="message">${requestScope.message}</p>
                        <div>
                            <select name="desgnId" class="form-control" onchange="showUsersView(this.value)">
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
                            
                        <h3>User Details</h3>
                        
                        <p id="message">${requestScope.message}</p>
                            
                        <table class="table-bordered" id="bordered-table"  style="width:100%;margin: 20px auto;text-align: left;">

                            <tr>
                                <td width="25%" class="bold">User Name</td>
                                <td width="60%" id="username">${requestScope.user.name}</td>
                            </tr>
                            <tr>
                                <td class="bold">Employee Id</td>
                                <td>${requestScope.user.id}</td>
                            </tr>
                            <tr>
                                <td class="bold">Email</td>
                                <td id="email">${requestScope.user.email}</td>
                            </tr>
                            <tr>
                                <td class="bold">Mobile No.</td>
                                <td id="mobile">${requestScope.user.mobile}</td>
                            </tr>
                            <tr>
                                <td class="bold">User Level</td>
                                <td>Level ${requestScope.user.designation.level}</td>
                            </tr>
                            <tr>
                                <td class="bold">Designation</td>
                                <td>${requestScope.user.designation.name}</td>
                            </tr>
                        </table>
                            <a href="#" class="btn btn-default" >Update Details</a>
                            <a href="#" class="btn btn-default" >Reset Password</a>
                    </c:if>


                </div>
            </div>
        </div>
        <%@include file="/static/include/footer.jsp" %>
    </body>
</html>
