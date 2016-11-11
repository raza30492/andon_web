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
        <title>Remove User</title>
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
                  <div class="btn-group">
                     <a role="button" class="btn btn-primary" href="${pageContext.servletContext.contextPath}/user/show?page=view">View User</a>
                     <a role="button" class="btn btn-primary" href="${pageContext.servletContext.contextPath}/user/register/form">Register User</a>
                     <a role="button" class="btn btn-primary" href="${pageContext.servletContext.contextPath}/user/show?page=update">Update User</a>
                     <a role="button" class="btn btn-primary active" href="${pageContext.servletContext.contextPath}/user/show?page=remove">Remove User</a>
                  </div>
               </div>

                <div style="width: 41%; margin: 20px auto" >


                    <p id="message">${requestScope.message}</p>
                    <div>
                        <select name="desgnId" class="form-control" onchange="showUsersRemove(this.value)">
                                <option value="" >Select Designation</option>
                                <c:forEach items="${applicationScope['scopedTarget.appPref'].desgns}" var="desgn">
                                    <option value="${desgn.id}" >${desgn.name}</option>
                                </c:forEach>   
                            </select>

                    </div>
                    <!-- Content updated by Ajax technology --> 
                    <div id="display_users">

                    </div>


                </div>
            </div>
        </div>
        <%@include file="/static/include/footer.jsp" %>       
    </body>
</html>
