package br.edu.insper.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class DAO {

	private Connection connection = null;

	public DAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/RetroHub", "", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean addUser(User user) {
		try {
			
			String sql = "SELECT * FROM user WHERE name=?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1,user.getName());
			ResultSet rs = stmt.executeQuery();
			
			if(!rs.next()) {
				
				sql = "INSERT INTO user" + "(name,password) values(?,?)";
				stmt = connection.prepareStatement(sql);
				
				stmt.setString(1,user.getName());
				stmt.setString(2,user.getPassword());
				stmt.execute();
				stmt.close();
				
				return true;
				
			}
		} 
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return false;
	}
	
	public int getUserId(User user) {
		
		int id;
		try {
			
			String sql = "SELECT * FROM users WHERE name=?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, user.getName());
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				id = rs.getInt("id");	
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return id;
	}

	public List<User> getUsers(){
		
		List<User> users = (List) new ArrayList<User>();
		
		try {
			
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM user");
			ResultSet lista;
			
			lista = stmt.executeQuery();
			
			while (lista.next()) {
				User user = new User();
				user.setUserId(lista.getInt("id"));
				user.setName(lista.getString("name"));
				user.setPassword(lista.getString("password"));
				users.add(user);
				
			lista.close();
			stmt.close();
			}
					
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public boolean login(User user) {

		boolean status = false;
		
		try {

			String sql = "SELECT * FROM user WHERE name=? AND password=?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getPassword());
			
			ResultSet check = stmt.executeQuery();
			status = check.next();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
	
	public void close() {
		// TODO Auto-generated method stub
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
