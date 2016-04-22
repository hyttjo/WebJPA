<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="CSS/CSS.css">
        <title>Edit a Member Record</title>
    </head>
    <body>
        <h1>Edit a Member record</h1>

        <a href="ListMember"><div class="menu">List of members</div></a>
        <a href="CreateMember.jsp"><div class="menu">Create a Member</div></a>
        <a href="ListEvent"><div class="menu">List of events</div></a>
        <a href="CreateEvent"><div class="menu">Create a Manual Event</div></a>
        <c:forEach var="member" begin="0" items="${requestScope.memberList}">
            <c:choose>
                <c:when test="${param.id == member.id}">
                    <form id="editMemberForm" action="EditMember" method="post">
                        <table>
                            <tr><td>ID</td><td><input type="text" id="id" name="id" value="${member.id}" readonly /></td></tr>
                            <tr><td>First Name</td><td><input type="text" id="firstName" name="firstName" value="${member.firstName}" /></td></tr>
                            <tr><td>Last Name</td><td><input type="text" id="lastName" name="lastName" value="${member.lastName}" /></td></tr>
                            <tr><td>Email</td><td><input type="text" id="email" name="email" value="${member.email}" /></td></tr>
                        </table><br>
                        <input type="submit" id="EditRecord" value="EditRecord" />
                    </form> 
                </c:when>
            </c:choose>
        </c:forEach>
    </body>
</html>
