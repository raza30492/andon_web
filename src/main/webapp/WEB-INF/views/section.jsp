<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Sections</title>
        <%@include file="/static/include/common.jsp" %>
        
        <link rel="stylesheet" type="text/css" href="css/section.css" />
    </head>
    <body>
        <%@include file="/static/include/header.jsp" %>
        <div class="wrapper">
            <div class="content">
                <h4 class="heading1">Available Sections</h4>

                <table class="table-bordered" id="bordered-table" style="width: 40%;margin:20px auto" >
                    <tr>
                        <th width="20%" class="align-center">Sl. No.</th>
                        <th class="align-center">Section Name</th>
                    </tr>

                    <c:forEach items="${applicationScope['scopedTarget.appPref'].sections}" var="section" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>${section.name}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
        <%@include file="/static/include/footer.jsp" %>       
    </body>
</html>
