package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import model.User;
import model.Category;
import model.Expense;
import java.util.ArrayList;
import utility.DBConnection;

/**
 * Servlet implementation class ExpenseEditServlet
 */
@WebServlet("/updatecategory.action")
public class CategoryEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection connection = DBConnection.getConnection(getServletContext());
		String name = request.getParameter("name");
		int moduleID = Integer.parseInt(request.getParameter("moduleID"));
		String description = request.getParameter("description");
		String image = request.getParameter("image");
		int categoryID = Integer.parseInt(request.getParameter("categoryID"));
		if(StringUtils.isBlank(name) || StringUtils.isBlank(description)) {
			response.sendRedirect("400.jsp");
			return;
		}
		CategoryOperations cOps = new CategoryOperations();
	
		cOps.updateCategory(connection, name, moduleID, description, image, categoryID);
		
	
		response.sendRedirect("categoryindex.action");
	

		
	}

}
