<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="CSS/CSS.css">
        <title>List Of Events</title>
    </head>
    <body>
        <h1>List of Events currently in Database</h1>

        <a href="ListMember"><div class="menu">List of members</div></a>
        <a href="CreateMember.jsp"><div class="menu">Create a Member</div></a>
        <a href="ListEvent"><div class="menu active">List of events</div></a>
        <a href="CreateEvent"><div class="menu">Create a Manual Event</div></a>
        
        <table>
            <tr >
                <th bgcolor=>ID</th>
                <th bgcolor=>Type</th>
                <th bgcolor=>Member</th>
                <th bgcolor=>Date</th>
            </tr>
            <c:forEach var="event" begin="0" items="${requestScope.eventList}">
                <tr>
                    <td>${event.id}&nbsp;&nbsp;</td> 
                    <td>${event.type}&nbsp;&nbsp;</td>
                    <td>${event.member.firstName} ${event.member.lastName}&nbsp;&nbsp;</td> 
                    <td>${event.date}&nbsp;&nbsp;</td> 
                </tr> 
            </c:forEach>
        </table>
    </body>
</html>
