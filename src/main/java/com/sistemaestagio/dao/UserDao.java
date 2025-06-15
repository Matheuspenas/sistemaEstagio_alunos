package com.sistemaestagio.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sistemaestagio.bean.User;

public class UserDao {
	public static Connection getConnection() {
		Connection con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sistema_estagio","root","");		
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;		
	}
	
	public static List<User> getAllUsers(){
		List<User> list = new ArrayList<User>();
		
		try {
			Connection con = getConnection();
			PreparedStatement ps = (PreparedStatement) con.prepareStatement("Select * FROM user");			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setGender(rs.getString("gender"));
				user.setCountry(rs.getString("country"));
				list.add(user);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}
	
	public static int deleteUser(User u) {
		int status = 0;
		try {
			Connection con = getConnection();
			PreparedStatement ps = (PreparedStatement) 
					con.prepareStatement("DELETE FROM user WHERE ID=?");
			ps.setInt(1, u.getId());
			status = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}
	
	public static int updateUser(User u) {
		int status = 0;
		try {
			Connection con = getConnection();
			PreparedStatement ps = (PreparedStatement) con.prepareStatement("UPDATE user SET name = ? , "
			+ " password = ? , email = ? , gender = ? , country = ? WHERE id = ?");
			ps.setString(1, u.getName());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getEmail());
			ps.setString(4, u.getGender());
			ps.setString(5, u.getCountry());
			ps.setInt(6, u.getId());			
			status = ps.executeUpdate();
		}catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}	
	
	public static int addUser(User u) {
		int status = 0;
		try {
			Connection con = getConnection();
			PreparedStatement ps = (PreparedStatement) con.prepareStatement("INSERT INTO user (name, password, email, gender, country) VALUES (?,?,?,?,?)");
			ps.setString(1, u.getName());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getEmail());
			ps.setString(4, u.getGender());
			ps.setString(5, u.getCountry());
			status = ps.executeUpdate();
		}catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}
	
	public static User getRegistroById(int id) {
		User usuario = null;
		try {
			Connection con = getConnection();
			PreparedStatement ps = (PreparedStatement) con.prepareStatement("SELECT * FROM user WHERE ID=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				usuario = new User();
				usuario.setId(rs.getInt("id"));
				usuario.setName(rs.getString("name"));
				usuario.setPassword(rs.getString("password"));
				usuario.setEmail(rs.getString("email"));
				usuario.setGender(rs.getString("gender"));
				usuario.setCountry(rs.getString("country"));				
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return usuario;
	}
	
	public static List<User> getUsersByPage(int page, int pageSize) {
	    List<User> list = new ArrayList<>();
	    try {
	        Connection con = getConnection();
	        PreparedStatement ps = con.prepareStatement(
	            "SELECT * FROM user LIMIT ? OFFSET ?");
	        ps.setInt(1, pageSize);
	        ps.setInt(2, (page - 1) * pageSize);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            User u = new User();
	            u.setId(rs.getInt("id"));
	            u.setName(rs.getString("name"));
	            u.setPassword(rs.getString("password"));
	            u.setEmail(rs.getString("email"));
	            u.setGender(rs.getString("gender"));
	            u.setCountry(rs.getString("country"));
	            list.add(u);
	        }
	    } catch (Exception e) {
	        System.out.println(e);
	    }
	    return list;
	}

	public static int getUserCount() {
	    int count = 0;
	    try {
	        Connection con = getConnection();
	        PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM user");
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            count = rs.getInt(1);
	        }
	    } catch (Exception e) {
	        System.out.println(e);
	    }
	    return count;
	}
	
	
}
