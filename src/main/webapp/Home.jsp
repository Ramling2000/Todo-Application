<%@page import="dto.Task"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<style type="text/css">
body {
	background-image:
		url("https://img.freepik.com/free-vector/hand-painted-watercolor-pastel-sky-background_23-2148902771.jpg");
	background-size: cover;
}
</style>
</head>

    <center>
    <body>
	<%-- This is Receiving the values from Servlet --%>

	<%
	List<Task> list = (List<Task>) request.getAttribute("list");
	%>
	
             
	<table border="1">
		<tr>
			<th>ID</th>
			<th>Task Name</th>
			<th>Description</th>
			<th>Task Date</th>
			<th>Task Completion Date</th>
			<th>Status</th>
			<th>Change Status</th>
			<th>Delete</th>
			<th>Update</th>
		</tr>
		<%
		for (Task t : list) {
		%>
		<%--Printing value if it ExistsS --%>
		<tr>
		
			<td><%=t.getId()%></td>
			<td><%=t.getName()%></td>
			<td><%=t.getDescription()%></td>
			<td><%=t.getTaskDate()%></td>
			<td><%=t.getCompletionDate()%></td>
			<td><%if (t.isStatus())%>Completed<%else%>Not completed</td>
			<td><a href="changestatus?id=<%=t.getId()%>"><button>Change</button></a></td>
			<td><a href="delete?id=<%=t.getId()%>"><button>Delete</button></a></td>
			<td><a href="update?id=<%=t.getId()%>"><button>Update</button></a></td>
		</tr>
		<%
		}
		%>
	</table>
	
	<br>
	<br>
	<a href="tasksession"><button style="margin-left: 20px">Add
			Task</button></a>
	<a href="Logout"><button style="margin-left: 500px">Logout</button></a>   
</body>
</center>
</html>