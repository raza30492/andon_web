<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Problem & Escalation</title>
        <%@include file="/static/include/common.jsp" %>
        
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/static/css/index.css" />
    </head>
    <body>
        <%@include file="/static/include/header.jsp" %>
        <div class="wrapper">
            <div class="content">
                <h2>Problem & Escalation Mapping</h2>
                <div style="width: 95%; margin: 20px auto" >
                      
                        <table class="table-bordered" id="bordered-table" style="width: 80%;margin: auto">
                           <tr>
                              <th class="align-center" width="15%">Department</th>
                              <th class="align-center" width="20%">Problems</th>
                              <th class="align-center">Level 1</th>
                              <th class="align-center" width="22%">Level 2</th>
                              <th class="align-center" width="18%">Level 3</th>
                           </tr>
                           <c:forEach items="${requestScope.departments}" var="dept">
                           <tr>
                              <td rowspan="${dept.problems.size()}" >${dept.name}</td>
                              <td>${dept.problems.toArray()[0].name}</td>
                              <td>
                                  <c:forEach items="${dept.problems.toArray()[0].designations}" var="designation">
                                      <c:if test="${designation.level == 1}">${designation.name}<br></c:if>
                                  </c:forEach>
                              </td>
                                <td>
                                    <c:forEach items="${dept.problems.toArray()[0].designations}" var="designation">
                                        <c:if test="${designation.level == 2}">${designation.name}<br></c:if>
                                    </c:forEach>
                                </td>
                                <td>
                                    <c:forEach items="${dept.problems.toArray()[0].designations}" var="designation">
                                        <c:if test="${designation.level == 3}">${designation.name}<br></c:if>
                                    </c:forEach>
                                </td>

                           </tr>
                           <c:forEach var="i" begin="1" end="${dept.problems.size()-1}">
                                <tr>
                                    <td>${dept.problems.toArray()[i].name}</td>
                                    <td>
                                        <c:forEach items="${dept.problems.toArray()[i].designations}" var="designation">
                                            <c:if test="${designation.level == 1}">${designation.name}<br></c:if>
                                        </c:forEach>
                                    </td>
                                    <td>
                                        <c:forEach items="${dept.problems.toArray()[i].designations}" var="designation">
                                            <c:if test="${designation.level == 2}">${designation.name}<br></c:if>
                                        </c:forEach>
                                    </td>
                                    <td>
                                        <c:forEach items="${dept.problems.toArray()[i].designations}" var="designation">
                                            <c:if test="${designation.level == 3}">${designation.name}<br></c:if>
                                        </c:forEach>
                                    </td>
                                </tr>
                           </c:forEach>

                           </c:forEach>
                        </table>
                         
                    </div>
            </div>
        </div>
        <%@include file="/static/include/footer.jsp" %>
    </body>
</html>
