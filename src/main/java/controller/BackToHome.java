package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.myUser;
@WebServlet("/backtohome")
public class BackToHome extends HttpServlet{
   @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	   myUser user=(myUser)req.getSession().getAttribute("user");
	   if(user==null) {
			  resp.getWriter().print("<h1 style='color:red'>Session Expired, Login Again</h1>");
	  		req.getRequestDispatcher("Login.html").include(req, resp);
	  	}else {
	  		req.setAttribute("list", user.getList());
	  		req.getRequestDispatcher("Home.jsp").include(req, resp);
	  	}
}
}
