package controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dto.Task;
import dto.myUser;
@WebServlet("/update")
public class Update extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	myUser user=(myUser)req.getSession().getAttribute("user");
    	if(user==null) {
    		resp.getWriter().print("<h1 style='color:red'>Session Expired, Login Again</h1>");

      		req.getRequestDispatcher("Login.html").include(req, resp);
      	}
    	else{
      	 int id=Integer.parseInt(req.getParameter("id"));
      	 String name=req.getParameter("name");
      	 String description=req.getParameter("description");
      	 int days=Integer.parseInt(req.getParameter("days"));
      	  
      	 Task t=new Task();
      	 t.setId(id);
      	 t.setName(name);
      	 t.setDescription(description);
      	 t.setTaskDate(LocalDate.now());
      	 t.setCompletionDate(LocalDate.now().plusDays(days));
      	  
      	 UserDao dao=new UserDao();
      	 dao.update(t);
      	 
      // Logic to Update Session
      	 myUser user2=dao.fetchByEmail(user.getEmail());
     	   req.getSession().setAttribute("user", user2);
     			   
    	resp.getWriter().print("<h1 style='color:green'> Updated the Task Succefully</h1>");
    	
        req.setAttribute("list", user2.getList());
        req.getRequestDispatcher("Home.jsp").include(req, resp);

    }
}
}