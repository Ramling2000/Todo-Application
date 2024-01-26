package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dto.Task;
import dto.myUser;
@WebServlet("/delete")
public class Delete extends HttpServlet{
  @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	  myUser user = (myUser) req.getSession().getAttribute("user");

	  if(user==null) {
		  resp.getWriter().print("<h1 style='color:red'>Session Expired, Login Again</h1>");
  		req.getRequestDispatcher("Login.html").include(req, resp);
  	}
	  else {
		  int id = Integer.parseInt(req.getParameter("id"));

		  UserDao dao = new UserDao();
		  
			Task t = dao.fetchTask(id);
			if (t == null) {
				resp.getWriter().print("<h1 style='color:red'>Session Expired, Login Again</h1>");
				req.getRequestDispatcher("Login.html").include(req, resp);
	 
			} else {

				// Logic to Remove mapping
				user.getList().remove(t);
				dao.update(user);

				// Logic to Delete
				dao.remove(t);

				// Logic to Update Session
				myUser user2 = dao.fetchByEmail(user.getEmail());
				req.getSession().setAttribute("user", user2);

				resp.getWriter().print("<h1 style='color:green'>Data Deleted Successfully</h1>");

				req.setAttribute("list", user2.getList());
				req.getRequestDispatcher("Home.jsp").include(req, resp);
			}
}
}
}
  