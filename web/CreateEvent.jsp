<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="CSS/CSS.css">
        <title>Create a Event Record</title>
    </head>
    <body>
        <h1>Create a Manual Event record</h1>
        
        <a href="ListMember"><div class="menu">List of members</div></a>
        <a href="CreateMember.jsp"><div class="menu">Create a Member</div></a>
        <a href="ListEvent"><div class="menu">List of events</div></a>
        <a href="CreateEvent"><div class="menu active">Create a Manual Event</div></a>
        
        <form id="createEventForm" action="CreateManualEvent" method="post">
            <table>
                <tr>
                    <td>Type</td>
                    <td>
                        <select id="type" name="type">
                            <option val="CreatedMember">CreatedMember</option>
                            <option val="ChangedMember">ChangedMemberData</option>
                            <option val="DeletedMember">DeletedMember</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Member</td>
                    <td>
                        <select id="member" name="member">
                            <c:forEach var="member" begin="0" items="${requestScope.memberList}">
                                <option value="${member.id}">${member.firstName} ${member.lastName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
            </table><br>
            <input type="submit" id="CreateRecord" value="CreateRecord" />
        </form>
    </body>
</html>
