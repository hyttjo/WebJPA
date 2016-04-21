<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="CSS/CSS.css">
        <script src="JS/Javascript.js"></script>
        <title>List Of Members</title>
    </head>
    <body>
        <h1>List of Members currently in Database</h1>

        <strong>Go to List of members</strong><br>
        <a href="CreateMember.jsp"><strong>Create a Member Record</strong></a><br>
        <a href="ListEvent"><strong>Go to List of events</strong></a><br>
        <a href="CreateEvent"><strong>Create a Manual Event Record</strong></a><br><br>
        
        <table>
            <tr>
                <th bgcolor=>Show events</th>
                <th bgcolor=>ID</th>
                <th bgcolor=>FirstName</th>
                <th bgcolor=>LastName</th>
                <th bgcolor=>Email</th>
                <th bgcolor=>Since</th>
            </tr>
        <c:forEach var="member" begin="0" items="${requestScope.memberList}">
            <tr>
                <td><a href="#" onclick="show(this);">show</a></td> 
                <td>${member.id}&nbsp;&nbsp;</td> 
                <td>${member.firstName}&nbsp;&nbsp;</td> 
                <td>${member.lastName}&nbsp;&nbsp;</td> 
                <td>${member.email}&nbsp;&nbsp;</td> 
                <td>${member.since}&nbsp;&nbsp;</td> 
            </tr>
            <tr class="events" style="display: none">
                <td colspan="6">
                    <table>
                        <tr>
                            <th>Member's events</th>
                            <th>ID</th>
                            <th>Type</th>
                            <th>Date</th>
                        </tr>
                        <c:forEach var="event" begin="0" items="${member.events}">
                            <tr>
                                <td></td> 
                                <td>${event.id}&nbsp;&nbsp;</td> 
                                <td>${event.type}&nbsp;&nbsp;</td> 
                                <td>${event.date}&nbsp;&nbsp;</td> 
                            </tr> 
                        </c:forEach>
                    </table>
                </td>
            </tr>
        </c:forEach>
            
        </table>
    </body>
</html>
