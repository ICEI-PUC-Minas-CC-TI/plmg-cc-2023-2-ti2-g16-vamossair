package dao;

import java.sql.*;
import model.User;

public class UserDAO extends DAO {

	public UserDAO() {
		super();
		conectar();
	}

	public boolean insert(User user) {
		boolean status = false;
	
		try {
			String sql = "INSERT INTO usuarios (nome, email, senha) VALUES (?, ?, ?)";
	
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
	
			preparedStatement.setString(1, user.getNome());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getSenha());
	
			preparedStatement.executeUpdate();
			preparedStatement.close();
	
			status = true;
		} catch (SQLException e) {
			System.err.println(e);
		}
	
		return status;
	}
	
	public User get(String email, String senha) {
		User user = null;
		
		try {
			
			String sql = "SELECT * FROM usuarios WHERE email='" + email + "' AND senha='" + senha + "';";
			
			Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery(sql);
			
			if(rs.next()){            
	         	user = new User(rs.getInt("id"), rs.getString("nome"),rs.getString("email"),rs.getString("senha"));
	        }
			
			st.close();
			
		}catch(SQLException e) {
			System.err.println(e);
		}
		
		return user;	
	}

	public User getById(int id) {
		User user = null;
		
		try {
			
			String sql = "SELECT * FROM usuarios WHERE id=" + id +";";
			
			Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery(sql);
			
			if(rs.next()){            
	         	user = new User(rs.getInt("id"), rs.getString("nome"),rs.getString("email"),rs.getString("senha"));
	        }
			
			st.close();
			
		}catch(SQLException e) {
			System.err.println(e);
		}
		
		return user;	
	}

	public boolean update(User user) {
		boolean status = false;
		try {  
			String sql = "UPDATE usuarios SET nome = '" + user.getNome() + "', "
					   + "email = '" + user.getEmail() + "'', " 
					   + "senha = '" + user.getSenha() +
					    "' WHERE id = " + user.getId();
			PreparedStatement st = connection.prepareStatement(sql);
		
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public boolean delete(int id) {
		boolean status = false;
		try {  
			Statement st = connection.createStatement();
			st.executeUpdate("DELETE FROM usuarios WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
}