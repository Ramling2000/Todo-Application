package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dto.Task;
import dto.myUser;

@WebServlet("/Login")
public class Login extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String email = req.getParameter("email");
		String password = req.getParameter("password");

		UserDao dao = new UserDao();
		myUser user = dao.fetchByEmail(email);

		if (user == null) {
			resp.getWriter().print("<h1>Incorrect Email</n1>");
			req.getRequestDispatcher("Login.html").include(req, resp);
		} else {
			if (user.getPassword().equals(password)) 
			{
				//Setting session

				req.getSession().setAttribute("user", user);
				req.getSession().setMaxInactiveInterval(60);
				
				resp.getWriter().print("<h1style='color:green'>Login Successfully</h1>");
//				List<Task>list=user.getList();
				
				//Carrying values to Home Page
				req.setAttribute("list", user.getList());
				req.getRequestDispatcher("Home.jsp").include(req, resp);
				
			} else {
				resp.getWriter().print("<h1 style='color:red'>Incorrect password</h1>");
				req.getRequestDispatcher("Login.html").include(req, resp);
			}
		}
	}
}
