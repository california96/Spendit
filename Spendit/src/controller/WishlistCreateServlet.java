package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import model.Wishlist;
import model.User;
import utility.DBConnection;

/**
 * Servlet implementation class WishlistCreateServlet
 */
@WebServlet("/createwish.action")
public class WishlistCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger log = Logger.getLogger(WishlistCreateServlet.class.getName());

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
		int categoryID = Integer.parseInt(request.getParameter("categoryID"));
		int incomeSourceID = Integer.parseInt(request.getParameter("incomeSourceID"));
		double amount = Double.parseDouble(request.getParameter("amount"));	
	
		String comment = request.getParameter("comment");
		
		HttpSession session = request.getSession(false);
		User user = (User)session.getAttribute("user");
		Connection connection = DBConnection.getConnection(getServletContext());
		if(StringUtils.isBlank(comment)) {
			response.sendError(400);
			return;
		}
		try {
			java.util.Date date = new SimpleDateFormat("MM/dd/yyyy hh:mm a").parse(request.getParameter("date"));
			String formatted = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
			Wishlist wish = new Wishlist(user.getUserID(), categoryID, incomeSourceID, 1, amount, formatted, comment);
			WishlistOperations wishOps = new WishlistOperations();
			if(wishOps.insert(connection, wish)) {
				response.sendRedirect("retrievewishlist.action");
			}
		}catch(Exception e) {
			log.error(e.getMessage());
		}
	}

}
