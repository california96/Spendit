package controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.Expense;
import model.User;
import utility.DBConnection;

/**
 * Servlet implementation class ExpenseCreateServlet
 */
@WebServlet("/createcategory.action")
public class CategoryCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		
		int moduleID = Integer.parseInt(request.getParameter("module"));
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String image = request.getParameter("image");
		CategoryOperations cOps = new CategoryOperations();

		Connection connection = DBConnection.getConnection(getServletContext());
		if(cOps.insert(connection, name, moduleID, description, image)) {
			response.sendRedirect("categoryindex.action");
		}
		

	}

}
