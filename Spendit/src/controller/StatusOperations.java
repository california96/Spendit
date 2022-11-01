package controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import model.Status;
public class StatusOperations {
	static Logger log = Logger.getLogger(StatusOperations.class.getName());
	public ArrayList<model.Status> getAllStatus(Connection connection){
		ArrayList<model.Status> status = new ArrayList<model.Status>();
		String sql = "SELECT * FROM status";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				status.add(new Status(rs.getInt("statusID"), rs.getString("status")));
			}
		}catch(SQLException sqle) {
			log.error(sqle.getMessage());
		}
		return status;
	}
}
