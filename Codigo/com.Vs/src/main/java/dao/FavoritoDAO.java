package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Favorito;

public class FavoritoDAO extends DAO {

	public FavoritoDAO() {
		super();
		conectar();
	}

	public boolean insert(Favorito favorito) {
		boolean status = false;

		try {
			String sql = "INSERT INTO favoritos (usuario_id, lugar_id) VALUES (?, ?)";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, favorito.getUserId());
			preparedStatement.setInt(2, favorito.getLugarId());

			preparedStatement.executeUpdate();
			preparedStatement.close();

			status = true;
		} catch (SQLException e) {
			System.err.println(e);
		}

		return status;
	}

	public Favorito get(int user_id, int lugar_id) {
		Favorito favorito = null;

		try {
			String sql = "SELECT * FROM favoritos WHERE usuario_id = ? AND lugar_id = ?";

			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, user_id);
			ps.setInt(2, lugar_id);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				favorito = new Favorito(rs.getInt("id"), rs.getInt("usuario_id"), rs.getInt("lugar_id"));
			}

		} catch (SQLException e) {
			System.err.println(e);
		}

		return favorito;
	}

	public List<Integer> getByUserId(int userId) {
		List<Integer> favoritos = new ArrayList<>();

		try {
			String sql = "SELECT lugar_id FROM favoritos WHERE usuario_id = ?";

			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, userId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int lugarId = rs.getInt("lugar_id");
				favoritos.add(lugarId);
			}

		} catch (SQLException e) {
			System.err.println(e);
		}

		return favoritos;
	}

	public boolean update(Favorito favorito) {
		boolean status = false;
		try {
			String sql = "UPDATE favoritos SET usuario_id = ?, lugar_id = ? WHERE id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, favorito.getUserId());
			ps.setInt(2, favorito.getLugarId());
			ps.setInt(3, favorito.getId());

			ps.executeUpdate();
			status = true;

		} catch (SQLException e) {
			System.err.println(e);
		}
		return status;
	}

	public boolean delete(int id) {
		boolean status = false;
		try {
			String sql = "DELETE FROM favoritos WHERE id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			status = true;

		} catch (SQLException e) {
			System.err.println(e);
		}
		return status;
	}

	public boolean deleteByUserId(int id) {
		boolean status = false;
		try {
			String sql = "DELETE FROM favoritos WHERE usuario_id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			status = true;

		} catch (SQLException e) {
			System.err.println(e);
		}
		return status;
	}

}
