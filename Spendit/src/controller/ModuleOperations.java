package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import utility.DBConnection;

public class ModuleOperations {
	static Logger log = Logger.getLogger(ModuleOperations.class.getName());
	public ArrayList<model.Module> getModules(Connection connection){
	ArrayList<model.Module> modules = new ArrayList<model.Module>();
	try {
		String sql = "SELECT moduleID, moduleName FROM modules";
		PreparedStatement prep = connection.prepareStatement(sql);
		ResultSet rs = prep.executeQuery();
		while(rs.next()) {
			modules.add(new model.Module(rs.getInt("moduleID"), rs.getString("moduleName")));
		}
		
	}catch(Exception e) {
		log.error(e.getMessage());
	}
	return modules;
	}
}
