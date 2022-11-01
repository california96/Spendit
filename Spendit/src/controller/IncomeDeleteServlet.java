package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import model.Expense;
import model.Income;

import java.util.ArrayList;
import utility.DBConnection;

/**
 * Servlet implementation class ExpenseEditServlet
 */
@WebServlet("/deleteincome.action")
public class IncomeDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int incomeID = Integer.parseInt(request.getParameter("id"));
		
		ServletContext context = getServletContext();
		
		Connection connection = DBConnection.getConnection(context);
		IncomeOperations inOp = new IncomeOperations();
		
		inOp.deleteIncome(connection, incomeID);
		request.getRequestDispatcher("retrieveallincome.action").forward(request, response);
		
		

		
	}

}
