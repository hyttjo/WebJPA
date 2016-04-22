<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="CSS/CSS.css">
        <title>Create a Member Record</title>
    </head>
    <body>
        <h1>Create a Member record</h1>

        <a href="ListMember"><div class="menu">List of members</div></a>
        <a href="CreateMember.jsp"><div class="menu active">Create a Member</div></a>
        <a href="ListEvent"><div class="menu">List of events</div></a>
        <a href="CreateEvent"><div class="menu">Create a Manual Event</div></a>

        <form id="createMemberForm" action="CreateMember" method="post">
            <table>
                <tr><td>First Name</td><td><input type="text" id="firstName" name="firstName" /></td></tr>
                <tr><td>Last Name</td><td><input type="text" id="lastName" name="lastName" /></td></tr>
                <tr><td>Email</td><td><input type="text" id="email" name="email" /></td></tr>
            </table><br>
            <input type="submit" id="CreateRecord" value="CreateRecord" />
        </form>
    </body>
</html>
