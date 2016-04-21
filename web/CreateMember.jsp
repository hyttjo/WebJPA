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

        <a href="ListMember"><strong>Go to List of members</strong></a><br>
        <strong>Create a Member Record</strong><br>
        <a href="ListEvent"><strong>Go to List of events</strong></a><br>
        <a href="CreateEvent"><strong>Create a Manual Event Record</strong></a><br><br>

        <form id="createMemberForm" action="CreateMember" method="post">
            <table>
                <tr><td>FirstName</td><td><input type="text" id = "firstName" name="firstName" /></td></tr>
                <tr><td>LastName</td><td><input type="text" id = "lastName" name="lastName" /></td></tr>
                <tr><td>Email</td><td><input type="text" id = "email" name="email" /></td></tr>
            </table><br>
            <input type="submit" id="CreateRecord" value="CreateRecord" />
        </form>
    </body>
</html>
