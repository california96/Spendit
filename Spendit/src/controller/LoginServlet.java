package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.User;
import utility.DBConnection;

import org.apache.log4j.Logger;


//@WebServlet("/login.action")
public class LoginServlet extends HttpServlet {
	static Logger log = Logger.getLogger(LoginServlet.class.getName());

	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		ServletContext context = getServletContext();
		
		Connection connection = DBConnection.getConnection(context);
		UserOperations auth = new UserOperations();
		
		
		Gson gson = new Gson();
		HashMap<Object, Object> errorMsg = new HashMap<>();
		String json;
		HttpSession session = request.getSession();
		
		if(auth.authenticate(connection, username, password)) {
			int userID = auth.getUser(connection, username).getUserID();
			LogController.logActivity(connection, userID, LogController.LOGGED_IN);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			errorMsg.put("msg", "success");
			json = gson.toJson(errorMsg);
			log.debug(json);
			
			
			session.setAttribute("user", auth.getUser(connection, username));
			response.getWriter().write(json);
		
			
		} else {
			
			
			   response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				errorMsg.put("msg", "error");
				json = gson.toJson(errorMsg);
				log.warn(json);
				response.getWriter().write(json);
			
		}
	}

}
