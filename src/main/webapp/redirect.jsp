<%--
Views should be stored under the WEB-INF folder so that
they are not accessible except through controller process.

This JSP is here to provide a redirect to the dispatcher
servlet but should be the only JSP outside of WEB-INF.
--%>
<%@page import="com.mycompany.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% if(session.getAttribute("user")!=null){
    User user=(User)session.getAttribute("user");
    if(user.isIsBanned()){
        response.sendRedirect("bannedView.htm");
    }
    else{
        response.sendRedirect("home.htm");
    }
}else{
        response.sendRedirect("home.htm");
    } %>
