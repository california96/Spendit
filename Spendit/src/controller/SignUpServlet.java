package controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.google.gson.Gson;

import utility.DBConnection;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/signup.action")
@MultipartConfig
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger log = Logger.getLogger(SignUpServlet.class.getName());
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String image = "";
		Part filePart = request.getPart("file");
		
		String uploadPath = getServletContext().getInitParameter("profilePicStorage");
		Connection connection = DBConnection.getConnection(getServletContext());
		
		Gson gson = new Gson();
		HashMap<Object, Object> errorMsg = new HashMap<>();
		String json;
		
		UserOperations newUser = new UserOperations();
		/*Check for Duplicates*/
		if(newUser.checkEmail(connection, email) || newUser.checkUser(connection, username)) {
				response.sendRedirect("register.jsp?error=1");
				return;
		}
		else {
		/*Terminate if there's a duplicate*/
		if(filePart.getSize() > 0) {
		image = newUser.uploadImage(uploadPath, filePart);
		}else {
			image = "default.png";
		}
		if(StringUtils.isBlank(firstName) || StringUtils.isBlank(firstName) || StringUtils.isBlank(lastName) || StringUtils.isBlank(username) || StringUtils.isBlank(password) ) { //assume that XSSFilter is blocked
			response.sendError(400);
			//response.sendRedirect("400error.jsp");
			return;
		}
		
		HttpSession session = request.getSession();
		if(newUser.insert(connection, username, email, password, firstName, lastName, image)){
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			errorMsg.put("msg", "success");
			json = gson.toJson(errorMsg);
			log.info(json);
			session.setAttribute("user", newUser.getUser(connection, username));
			response.getWriter().write(json);
			
			request.getRequestDispatcher("index.jsp").forward(request, response);
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

}
