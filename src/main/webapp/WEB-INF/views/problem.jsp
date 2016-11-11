<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Problems</title>
        <%@include file="/static/include/common.jsp" %>       
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/static/css/problem.css" />
        <script type="text/javascript" src="scripts/problem_ajax.js"></script>
    </head>
    <body>
        <%@include file="/static/include/header.jsp" %>
        <div class="wrapper">
            <div class="content">
                <div >
                    <h4>Problems</h4>

                    <div style="width: 60%; margin: 20px auto" >
                      
                        <table class="table-bordered" id="bordered-table" style="width: 75%;margin: auto">
                           <tr>
                              <th class="align-center">Department</th>
                              <th class="align-center">Problems</th>
                           </tr>
                           <c:forEach items="${applicationScope['scopedTarget.appPref'].departments}" var="dept">
                           <tr>
                              <td rowspan="${dept.problems.size()}" >${dept.name}</td>
                              <td>${dept.problems.toArray()[0].name}</td>

                           </tr>
                           <c:forEach var="i" begin="1" end="${dept.problems.size()-1}">
                              <tr><td>${dept.problems.toArray()[i].name}</td></tr>
                           </c:forEach>

                           </c:forEach>
                        </table>
                         
                    </div>
                </div>
            </div>
        </div>
        <%@include file="/static/include/footer.jsp" %>
        
    </body>
</html>
