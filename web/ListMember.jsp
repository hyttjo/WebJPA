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

        <a href="ListMember"><div class="menu active">List of members</div></a>
        <a href="CreateMember.jsp"><div class="menu">Create a Member</div></a>
        <a href="ListEvent"><div class="menu">List of events</div></a>
        <a href="CreateEvent"><div class="menu">Create a Manual Event</div></a>
        
        <table>
            <tr>
                <th bgcolor=>Show events</th>
                <th bgcolor=>ID</th>
                <th bgcolor=>First Name</th>
                <th bgcolor=>Last Name</th>
                <th bgcolor=>Email</th>
                <th bgcolor=>Since</th>
                <th bgcolor=>Edit data</th>
            </tr>
        <c:forEach var="member" begin="0" items="${requestScope.memberList}">
            <tr>
                <td><a href="#" onclick="show(this);">show</a></td> 
                <td>${member.id}&nbsp;&nbsp;</td> 
                <td>${member.firstName}&nbsp;&nbsp;</td> 
                <td>${member.lastName}&nbsp;&nbsp;</td> 
                <td>${member.email}&nbsp;&nbsp;</td> 
                <td>${member.since}&nbsp;&nbsp;</td> 
                <td><a href="ListEditMember?id=${member.id}">edit</a></td> 
            </tr>
            <tr class="events" style="display: none">
                <td colspan="7">
                    <table>
                        <tr>
                            <th>Member's events</th>
                            <th>ID</th>
                            <th>Type</th>
                            <th>Date</th>
                        </tr>
                        <c:forEach var="event" begin="0" items="${member.events}">
                            <tr>
                                <td>${event.member.firstName} ${event.member.lastName}</td> 
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
