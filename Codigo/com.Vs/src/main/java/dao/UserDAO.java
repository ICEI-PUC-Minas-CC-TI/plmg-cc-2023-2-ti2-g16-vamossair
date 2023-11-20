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
			String sql = "SELECT * FROM usuarios WHERE email=? AND senha=?";

			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, senha);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = new User(rs.getInt("id"), rs.getString("nome"), rs.getString("email"),
						rs.getString("senha"), rs.getInt("nivel"));
			}

		} catch (SQLException e) {
			System.err.println(e);
		}

		return user;
	}

	public User getById(int id) {
		User user = null;

		try {
			String sql = "SELECT * FROM usuarios WHERE id=?";

			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = new User(rs.getInt("id"), rs.getString("nome"), rs.getString("email"),
						rs.getString("senha"), rs.getInt("nivel"));
			}

		} catch (SQLException e) {
			System.err.println(e);
		}

		return user;
	}

	public boolean update(User user) {
		boolean status = false;
		try {
			String sql = "UPDATE usuarios SET nome = ?, email = ?, senha = ?, nivel = ? WHERE id = ?";
			PreparedStatement st = connection.prepareStatement(sql);

			st.setString(1, user.getNome());
			st.setString(2, user.getEmail());
			st.setString(3, user.getSenha());
			st.setInt(4, user.getNivel());
			st.setInt(5, user.getId());

			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return status;
	}

	public boolean delete(int id) {
		boolean status = false;
		try {
			String sql = "DELETE FROM usuarios WHERE id = ?";

			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			status = true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return status;
	}

}