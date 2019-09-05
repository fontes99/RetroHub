package br.edu.insper.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

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
			connection = DriverManager.getConnection("jdbc:mysql://localhost/RetroHub", "root", "123456");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List getLista() throws SQLException {
		
		List pessoas = (List) new ArrayList<User>();
		
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Pessoas");
		
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			User pessoa = new User();
			pessoa.setId(rs.getInt("id"));
			pessoa.setNome(rs.getString("nome"));
			Calendar data = Calendar.getInstance();
			data.setTime(rs.getDate("nascimento"));
			pessoa.setNascimento(data);
			pessoa.setAltura(rs.getDouble("altura"));
			((ArrayList<User>) pessoas).add(pessoa);
		}
		rs.close();
		stmt.close();
		return pessoas;
	}

	public void close() throws SQLException {
		// TODO Auto-generated method stub
		connection.close();
	}
	
	public void adiciona(User pessoa) throws SQLException {
		String sql = "INSERT INTO Pessoas" +
		"(nome,nascimento,altura) values(?,?,?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1,pessoa.getNome());
		stmt.setDate(2, new java.sql.Date(
		pessoa.getNascimento().getTimeInMillis()));
		stmt.setDouble(3,pessoa.getAltura());
		stmt.execute();
		stmt.close();
		}

}
