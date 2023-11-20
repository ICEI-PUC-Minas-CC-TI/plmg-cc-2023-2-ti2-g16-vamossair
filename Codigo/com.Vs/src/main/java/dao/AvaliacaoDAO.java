package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Avaliacao;

public class AvaliacaoDAO extends DAO {

	public AvaliacaoDAO() {
		super();
		conectar();
	}

	public boolean insert(Avaliacao avaliacao) {
		boolean status = false;

		try {
			String sql = "INSERT INTO avaliacoes (usuario_id, lugar_id, nota, comentario) VALUES (?, ?, ?, ?)";

			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setInt(1, avaliacao.getUserId());
			ps.setInt(2, avaliacao.getLugarId());
			ps.setInt(3, avaliacao.getNota());
			ps.setString(4, avaliacao.getComentario());

			ps.executeUpdate();
			ps.close();

			status = true;
		} catch (SQLException e) {
			System.err.println(e);
		}

		return status;
	}

	public Avaliacao get(int id) {
		Avaliacao avaliacao = null;

		try {

			String sql = "SELECT * FROM avaliacoes WHERE id = ?";

			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				avaliacao = new Avaliacao(rs.getInt("id"), rs.getInt("usuario_id"), rs.getInt("lugar_id"),
						rs.getInt("nota"), rs.getString("comentario"));
			}

			ps.close();

		} catch (SQLException e) {
			System.err.println(e);
		}

		return avaliacao;
	}

	public List<Avaliacao> get() {
		return get("");
	}

	public List<Avaliacao> getOrderByID() {
		return get("id");
	}

	public List<Avaliacao> getOrderByUserId() {
		return get("user_id");
	}

	public List<Avaliacao> getOrderByLugarId() {
		return get("lugar_id");
	}

	private List<Avaliacao> get(String orderBy) {
		List<Avaliacao> avaliacoes = new ArrayList<Avaliacao>();

		try {
			String sql = "SELECT * FROM avaliacoes" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			PreparedStatement pst = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Avaliacao avaliacao = new Avaliacao(rs.getInt("id"), rs.getInt("usuario_id"), rs.getInt("lugar_id"),
						rs.getInt("nota"), rs.getString("comentario"));
				avaliacoes.add(avaliacao);
			}
			pst.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return avaliacoes;
	}

	public List<Avaliacao> getByLugarId(int lugarId) {
		List<Avaliacao> avaliacoes = new ArrayList<Avaliacao>();

		try {
			String sql = "SELECT * FROM avaliacoes WHERE lugar_id = ?";

			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, lugarId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Avaliacao avaliacao = new Avaliacao(rs.getInt("id"), rs.getInt("usuario_id"), rs.getInt("lugar_id"),
						rs.getInt("nota"),
						rs.getString("comentario"));
				avaliacoes.add(avaliacao);
			}

		} catch (SQLException e) {
			System.err.println(e);
		}

		return avaliacoes;
	}

	public boolean update(Avaliacao avaliacao) {
		boolean status = false;
		try {
			String sql = "UPDATE avaliacoes SET"
					+ "usuario_id = " + avaliacao.getUserId() + ", "
					+ "lugar_id = " + avaliacao.getLugarId() + ","
					+ "nota = " + avaliacao.getNota() + ", "
					+ "comentario = '" + avaliacao.getComentario() + "' WHERE id = " + avaliacao.getId();
			PreparedStatement st = connection.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e);
		}
		return status;
	}

	public boolean delete(int id) {
		boolean status = false;
		try {
			String sql = "DELETE FROM avaliacoes WHERE id = ?";

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
			String sql = "DELETE FROM avaliacoes WHERE usuario_id = ?";

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
