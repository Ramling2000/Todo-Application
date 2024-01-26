<!DOCTYPE html>
<%@page import="java.time.Period"%>
<%@page import="dto.Task"%>
<html>

<head>
<meta charset="ISO-8859-1">
<title>Edit Task</title>
<style type="text/css">
body {
	background-image:
		url("https://img.freepik.com/free-vector/hand-painted-watercolor-pastel-sky-background_23-2148902771.jpg");
	background-size: cover;
}
</style>
</head>

<body>

	<%
	Task t = (Task) request.getAttribute("t");
	%>
	
	<h2>Enter Task Details</h2>
	<form action="update" method="get">
		<input type="text" name="id" value="<%=t.getId() %>" hidden required/th>
		<fieldset>
			<table>
				<tr>
					<th>Task Name:</th>
					<th><input type="text" name="name" value="<%=t.getName() %>" required/th>
				</tr>
				<tr>
					<th>Task Description:</th>
					<th><input type="text" name="description" value="<%=t.getDescription() %>" required></th>
				</tr>
				<tr>
					<th>Days Required:</th>
					<th><input type="number" name="days" value="<%=Period.between(t.getTaskDate(), t.getCompletionDate()).getDays() %>" required></th>
				</tr>
				<tr>
					<th><button>Update</button></th>
					<th><a href="backtohome"><button type="button">Cancel</button></a></th>
				</tr>
			</table>
		</fieldset>
	</form>
</body>

</html>