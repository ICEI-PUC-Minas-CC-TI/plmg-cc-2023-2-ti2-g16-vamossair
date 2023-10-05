package dao;

import java.sql.SQLException;
import java.sql.Statement;
import model.User;

public class UserDAO extends DAO {

	public UserDAO() {
		super();
		conectar();
	}

	public void insert(User user) {
		try {

			String sql = "INSERT INTO user (nome,email,senha)" + "VALUES ('" + user.getNome() + "','" + user.getEmail()
					+ "','" + user.getSenha() + "');";
			
			Statement st = connection.createStatement();
			st.executeUpdate(sql);
			st.close();

		} catch (SQLException e) {
			System.err.println(e);
		}
	}
	
}
