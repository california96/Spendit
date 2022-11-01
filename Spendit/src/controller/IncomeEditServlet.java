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
import org.apache.log4j.Logger;

import model.User;
import model.Expense;
import model.Income;

import java.util.ArrayList;
import utility.DBConnection;

/**
 * Servlet implementation class ExpenseEditServlet
 */
@WebServlet("/updateincome.action")
public class IncomeEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger log = Logger.getLogger(IncomeEditServlet.class.getName());

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int incomeID = Integer.parseInt(request.getParameter("incomeID"));
		int categoryID = Integer.parseInt(request.getParameter("categoryID"));
		double amount = Double.parseDouble(request.getParameter("amount"));	
		String date = request.getParameter("date");
		String comment = request.getParameter("comment");
		Connection connection = DBConnection.getConnection(getServletContext());
		IncomeOperations inOps = new IncomeOperations();
		if(StringUtils.isBlank(comment)) {
			response.sendError(400);
			return;
		}
		try {
			ExpenseOperations exOps = new ExpenseOperations();
			java.util.Date parsedDate = new SimpleDateFormat("MM/dd/yyyy hh:mm a").parse(date);// Month/Day/Year Hour:Minute A/PM
			date = new SimpleDateFormat("yyyy-MM-dd").format(parsedDate);// Year-Month-Day Hour:Minute:Seconds 24 hour
			inOps.editIncome(connection, incomeID, categoryID, amount, date, comment);
		
		
			response.sendRedirect("retrieveallincome.action");
		}catch(ParseException pe) {
			log.error(pe.getMessage());
		}

		
	}

}
