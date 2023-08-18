package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dto.Task;
import dto.myUser;

@WebServlet("/addtask")
public class AddTask extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getSession().getAttribute("user")==null) {
			resp.getWriter().print("<h1 style='color:red'>Session Expired, Login Again</h1>");	  	
			req.getRequestDispatcher("Login.html").include(req, resp);
	  	}else{
		String name = req.getParameter("name");
		String description = req.getParameter("description");
		int days = Integer.parseInt(req.getParameter("days"));
		//boolean Status =boolean.(req.getParameter("status"));
		
		Task t = new Task();
		t.setDescription(description);
		t.setName(name);
		t.setCompletionDate(LocalDate.now().plusDays(days));
		t.setTaskDate(LocalDate.now());
	//	t.setStatus(Status);

		UserDao dao = new UserDao();
		dao.save(t);
     
		myUser user=(myUser)req.getSession().getAttribute("user"); 
		List<Task>list=user.getList();
		if(list==null)
			list=new ArrayList<Task>();
		list.add(t);
		user.setList(list);
	dao.update(t);
		
		resp.getWriter().print("<h1>Task Added Succesfully</h1>");
		req.setAttribute("list", user.getList());
		req.getRequestDispatcher("Home.jsp").include(req, resp);
	}
}
}