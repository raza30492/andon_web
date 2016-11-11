<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="${pageContext.servletContext.contextPath}/">Andon System</a>
        </div>
            <ul class="nav navbar-nav">
                <li class=""><a href="${pageContext.servletContext.contextPath}/">Home</a></li>
                
                <!-- If User is Admin-->
                <c:if test="${sessionScope['scopedTarget.userPref'].level == 4}" >              
                    <li>
                        <a href="report.jsp" >Report</a>
                    </li>
                    <li>
                        <a href="${pageContext.servletContext.contextPath}/user/show?page=view" >Users</a>
                    </li>                                   
                </c:if>              
                 <!-- Mangement Level User-->
                <c:if test="${sessionScope['scopedTarget.userPref'].level == 3}" >
                    <li class="${pageContext.request.requestURI eq '/andon_system/report.jsp' ? 'active' : ''}">
                        <a href="report.jsp" >Report</a>
                    </li>                 
                </c:if>
                
                <li class="">
                    <a href="${pageContext.servletContext.contextPath}/section/">Sections</a>
                </li>
                <li class="">
                    <a href="${pageContext.servletContext.contextPath}/department/" >Departments</a>
                </li>
                <li class="">
                    <a href="${pageContext.servletContext.contextPath}/problem/escalation/" >Problem & Escalation</a> 
                </li>
                <li >
                    <a href="download.jsp" >Downloads</a>
                </li>
                <li class="">
                    <a href="faq.jsp">FAQ</a>
                </li>
                <li class="">
                    <a href="about.jsp">About</a>
                </li> 
          </ul>
          <ul class="nav navbar-nav navbar-right">
            
            <c:choose>
                <c:when test="${sessionScope['scopedTarget.userPref'].username != null}">
                    <li><a href="#" ><span class="glyphicon glyphicon-user"></span> ${sessionScope['scopedTarget.userPref'].username} </a></li>
                    <li><a href="${pageContext.servletContext.contextPath}/logout"><span class="glyphicon glyphicon-log-out"></span> Logout&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
                </c:when>
                <c:otherwise>
                    
                   <li><a href="${pageContext.servletContext.contextPath}/login/show"><span class="glyphicon glyphicon-log-in"></span>  Login&nbsp;&nbsp;</a></li>
                </c:otherwise>
            </c:choose>
          </ul>
    </div>
</nav>