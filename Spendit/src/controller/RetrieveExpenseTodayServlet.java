package controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import java.util.HashMap;
import model.Income;
import model.User;
import utility.DBConnection;

/**
 * Servlet implementation class RetrieveExpenseTodayServlet
 */
@WebServlet("/expensetoday.action")
public class RetrieveExpenseTodayServlet extends HttpServlet {
	static Logger log = Logger.getLogger(RetrieveExpenseTodayServlet.class.getName());
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		Connection connection = DBConnection.getConnection(getServletContext());
		HashMap<String, String> expense = new HashMap<String, String>();
		ExpenseOperations exOps = new ExpenseOperations();
		expense.put("expense", String.valueOf (exOps.getTodaysExpenses(connection, user.getUserID())));
		Gson gson = new Gson();
		String json = gson.toJson(expense);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
		log.info(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
