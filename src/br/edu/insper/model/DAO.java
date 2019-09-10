package br.edu.insper.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DAO {

	private Connection connection = null;

	public DAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/RetroHub", "root", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addUser(User user) {
		
		String sql = "INSERT INTO users" + "(username,password) VALUES (?, ?)";
		try {
			PreparedStatement stmt;
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getPassword());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean checkIfUserExists(User user) {
		String sql = "SELECT * FROM users WHERE username=?";
		PreparedStatement stmt;
		String name = user.getName();
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, name);
			ResultSet rs = stmt.executeQuery();
			if (rs == null) {
				return true;
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public int getUserId(User user) {
		
		int id = 0;
		try {
			
			String sql = "SELECT * FROM users WHERE username=?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, user.getName());
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				id = rs.getInt("userId");	
			}
			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return id;
	}
	
	public String getUserById(int id) {
		String username = "";
		String sql = "SELECT * FROM users WHERE userId=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				username = rs.getString("username");
			}
			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return username;
	}

	public List<User> getUsers(){
		
		List<User> users = (List) new ArrayList<User>();
		
		try {
			
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users");
			ResultSet lista;
			
			lista = stmt.executeQuery();
			
			while (lista.next()) {
				User user = new User();
				user.setUserId(lista.getInt("id"));
				user.setName(lista.getString("name"));
				user.setPassword(lista.getString("password"));
				users.add(user);
				}
			lista.close();
			stmt.close();
					
		} catch (Exception e) {
			// TODO: handle exception
		}
		return users;
		
	}
	
	public boolean login(User user) {

		boolean status = false;
		
		try {

			String sql = "SELECT * FROM users WHERE username=? AND password=?";
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
	
	public void addSubject(Subject subject) {
		String sql1 = "INSERT INTO subjects" + "(subject,url) VALUES (?,?)";
		
		String sql2 = "CREATE TABLE " + subject.getURL() + 
				"(id INT(255) NOT NULL AUTO_INCREMENT PRIMARY KEY, msg TEXT NOT NULL, idUser INT(32) NOT NULL, time TIMESTAMP NOT NULL)";
		try {
			PreparedStatement stmt1 = connection.prepareStatement(sql1);
			PreparedStatement stmt2 = connection.prepareStatement(sql2);
			stmt1.setString(1, subject.getSubject());
			stmt1.setString(2, subject.getURL());
			stmt1.execute();
			stmt2.execute();
			stmt1.close();
			stmt2.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Subject> getSubjectList() {
		List<Subject> assuntos = (List) new ArrayList<Subject>();
		String sql = "SELECT * FROM subjects";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet lista;
			
			lista = stmt.executeQuery();
			
			while (lista.next()) {
				Subject subject = new Subject();
				subject.setId(lista.getInt("id"));
				subject.setSubject(lista.getString("subject"));
				subject.setURL(lista.getString("subject"));
				assuntos.add(subject);
			}
			lista.close();
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return assuntos;
	}
	
	public List<Message> getChatMessages(String url) {
		List<Message> messages = (List) new ArrayList<Message>();
		String sql = "SELECT * FROM " + url;
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet msgList;
			
			msgList = stmt.executeQuery();
			
			while (msgList.next()) {
				Message message = new Message();
				message.setIdUser(msgList.getInt("idUser"));
				message.setMsg(msgList.getString("msg"));
				message.setTime(msgList.getTimestamp("time"));
				messages.add(message);
			}
			msgList.close();
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return messages;
	}
	
	public void addMessage(Message message, String url) {
		String sql = "INSERT INTO " + url + "(msg, idUser, time) VALUES (?,?,?)";
		System.out.println("Adicionando msg a URL: " + url);
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, message.getMsg());
			stmt.setInt(2, message.getIdUser());
			stmt.setTimestamp(3, message.getTime());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
}
