package controller;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import model.Category;
import model.Expense;

public class CategoryOperations {
	static Logger log = Logger.getLogger(CategoryOperations.class.getName());
	public boolean insert(Connection connection, String name, int moduleID, String description, String image) {
		String sql = "INSERT INTO categories(name, moduleID, description, image) VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement prep = connection.prepareStatement(sql);
			prep.setString(1, name);
			prep.setInt(2, moduleID);
			prep.setString(3, description);
			prep.setString(4, image);
			prep.executeUpdate();
			return true;
		}catch(SQLException sqle) {
			log.error(sqle.getMessage());
		}catch(Exception e) {
			log.error(e.getMessage());
		}
		return false;
	}
	public ArrayList<Category> getCategories(Connection connection, int moduleID){
		ArrayList<Category> categories = new ArrayList<Category>();
		String sql = "SELECT id, name, categories.moduleID, modules.modulename, description, image\n" + 
				"FROM categories\n" + 
				"INNER JOIN modules on modules.moduleID = categories.moduleID\n" + 
				"WHERE categories.moduleID = ?";
		try {
			PreparedStatement prep = connection.prepareStatement(sql);
			prep.setInt(1, moduleID);
			ResultSet rs = prep.executeQuery();
			while(rs.next()) {
				categories.add(new Category(rs.getInt("id"), rs.getString("name"), rs.getInt("moduleID"), rs.getString("modulename"), rs.getString("description"), rs.getString("image")));
			}
		}catch(SQLException sqle) {
			log.error(sqle.getMessage());
		}catch(Exception e) {
			log.error(e.getMessage());
		}
		return categories;
	}
	public ArrayList<Category> getAllCategories(Connection connection){
		ArrayList<Category> categories = new ArrayList<Category>();
		String sql = "SELECT id, name, categories.moduleID, modules.modulename, description, image\n" + 
				"FROM categories\n" + 
				"INNER JOIN modules on modules.moduleID = categories.moduleID"; 
		try {
			PreparedStatement prep = connection.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();
			while(rs.next()) {
				categories.add(new Category(rs.getInt("id"), rs.getString("name"), rs.getInt("moduleID"), rs.getString("modulename"), rs.getString("description"), rs.getString("image")));
			}
		}catch(SQLException sqle) {
			log.error(sqle.getMessage());
		}catch(Exception e) {
			log.error(e.getMessage());
		}
		return categories;
	}
	public Category getCategory(Connection connection, int categoryID){
		Category category = null;
		String sql = "SELECT id, name, categories.moduleID, modules.modulename as 'module', description, image\n" + 
				"				FROM categories\n" + 
				"				INNER JOIN modules on modules.moduleID = categories.moduleID\n" + 
				"				WHERE categories.id = ?"; 
		try {
			PreparedStatement prep = connection.prepareStatement(sql);
			prep.setInt(1, categoryID);
			ResultSet rs = prep.executeQuery();
			while(rs.next()) {
				category = new Category(rs.getInt("id"), rs.getString("name"), rs.getInt("moduleID"), rs.getString("module"), rs.getString("description"), rs.getString("image"));
			}
		}catch(SQLException sqle) {
			log.error(sqle.getMessage());
		}catch(Exception e) {
			log.error(e.getMessage());
		}
		return category;
	}
	public void updateCategory(Connection connection, String name, int moduleID, String description, String image, int categoryID) {
		String sql = "UPDATE categories SET name = ?, moduleID = ?, description = ?, image = ? WHERE categories.id = ?";
		try {
			PreparedStatement prep = connection.prepareStatement(sql);
			prep.setString(1, name);
			prep.setInt(2, moduleID);
			prep.setString(3, description);
			prep.setString(4, image);
			prep.setInt(5, categoryID);
			prep.executeUpdate();
		}catch(SQLException sqle) {
			log.error(sqle.getMessage());
		}catch(Exception e) {
			log.error(e.getMessage());
		}
	}
	public void delete(Connection connection,int categoryID) {
		String sql = "DELETE FROM categories WHERE categories.id = ?";
		try {
			PreparedStatement prep = connection.prepareStatement(sql);
			prep.setInt(1, categoryID);
			prep.executeUpdate();
		}catch(SQLException sqle) {
			log.error(sqle.getMessage());
		}catch(Exception e) {
			log.error(e.getMessage());
		}
	}
	public String uploadImage(String uploadPath, Part filePart) {
		String fileName;
		try {
			fileName = getFileName(filePart);
			InputStream is = filePart.getInputStream();
			Files.copy(is, Paths.get(uploadPath  + File.separator + fileName), StandardCopyOption.REPLACE_EXISTING);
			return fileName;
		}catch(Exception e) {
			log.error(e.getMessage());
			return e.getMessage();
		}
		
	}
	public String getFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}
}
