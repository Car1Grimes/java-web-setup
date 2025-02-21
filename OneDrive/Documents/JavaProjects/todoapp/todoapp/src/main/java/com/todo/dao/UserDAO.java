package com.todo.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.todo.dto.UserDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import utils.DBUtils;

public class UserDAO {
	
	public boolean createAccount(String username, String password) {
		boolean usernameExists = false;
		String insertSql = "INSERT INTO users(username, password) VALUES (?,?)";
		String selectSql = "SELECT * FROM users WHERE username = ?";
		try {
			Connection con = DBUtils.getConnection();
			PreparedStatement selectStmt = con.prepareStatement(selectSql);
			selectStmt.setString(1, selectSql);
			ResultSet selectRs = selectStmt.executeQuery();
			if (selectRs.next()) {
				String usernameForTest = selectRs.getString("username");
				if (usernameForTest != null) {
					usernameExists = true;
				}
			} else {
				PreparedStatement createStmt = con.prepareStatement(insertSql);
				createStmt.setString(1,  username);
				createStmt.setString(2,  password);
				createStmt.executeUpdate();
			}
			con.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return usernameExists;
	}
	
	public int login(UserDTO user) {
		try {
			Connection connection = DBUtils.getConnection();
			String sql = "SELECT id, username, password FROM users WHERE username = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, user.getUsername());
			ResultSet rSet = statement.executeQuery();
			if (rSet.next()) {
				String pass = rSet.getString("password");
				if (pass.equals(user.getPassword())) {
					return user.getUserId();
				}
			}
			connection.close();
		} catch(SQLException e) {
			System.err.println("Errors broke out when connecting to database. (UserDAO) " + e.getMessage());
			e.printStackTrace();
		}
		return 0;
	}
	
}
