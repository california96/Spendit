package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import utility.DBConnection;

/**
 * Servlet implementation class RetrieveModuleOptionsServlet
 */
@WebServlet("/retrievemodules.action")
public class RetrieveModuleOptionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger log = Logger.getLogger(RetrieveModuleOptionsServlet.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetrieveModuleOptionsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection connection = DBConnection.getConnection(getServletContext());
		ArrayList<model.Module>modules = new ModuleOperations().getModules(connection);
		Gson gson = new Gson();
		String json = gson.toJson(modules);
		log.info(json);
		response.getWriter().write(json);
	
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
