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
//URL Mapping
@WebServlet("/signup")
//Creating Servlet Class
public class Signup extends HttpServlet {
	

	@Override
	//Since form we need to take doPost
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//To receive data from form/url - name attribute is case sensitive

		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String mobile = req.getParameter("mobile");
		String gender = req.getParameter("gender");
		String dob = req.getParameter("dob");
		String[] language = req.getParameterValues("language");
		String address = req.getParameter("address");
		String password = req.getParameter("password");

	

		myUser user1 = new myUser();
		user1.setAddress(address);
		user1.setDod(LocalDate.parse(dob));
		user1.setEmail(email);
		user1.setGender(gender);
		user1.setLanguage(language);
		user1.setMobile(Long.parseLong(mobile));
		user1.setName(name);
		user1.setPassword(password);
        
		

		UserDao dao = new UserDao();
		// logic to verified email
		//To check email should not repeat

		myUser user = dao.fetchByEmail(email);
		if (user == null) {
			dao.save(user1);

			resp.getWriter().print("<h1 style=\"color:green\">Account Created Successfully</h1>");
			req.getRequestDispatcher("Login.html").include(req, resp);
		}

		else {
			resp.getWriter().print("<h1 style='color:red'>Email should not be repeated<h1>");
			req.getRequestDispatcher("Signup.html").include(req, resp);

		}

//		
//		UserDao dao = new UserDao();
//		if(dao.save(user1)) {
//		 resp.getWriter().print("<h1 style='color:red'>Email already exist</h1>");
//	  req.getRequestDispatcher("Signup.html").include(req, resp);	
//		}
//		else {
//			 resp.getWriter().print("<h1 style='color:green'>Account created success fully</h1>");	
//            req.getRequestDispatcher("Login.html").include(req, resp);
//		}

	}
}
