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

        <a href="ListMember"><strong>Go to List of members</strong></a><br>
        <a href="CreateMember.jsp"><strong>Create a Member Record</strong></a><br>
        <strong>Go to List of events</strong><br>
        <a href="CreateEvent"><strong>Create a Manual Event Record</strong></a><br><br>
        
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
