package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Lugar;

public class LugarDAO extends DAO {

    public LugarDAO() {
        super();
        conectar();
    }

    public boolean insert(Lugar lugar) {
        boolean status = false;
        try {
            String sql = "INSERT INTO lugar (nome, descricao, categoria, cidade, bairro, rua, complemento) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, lugar.getNome());
            st.setString(2, lugar.getDescricao());
            st.setString(3, lugar.getCategoria());
            st.setString(4, lugar.getCidade());
            st.setString(5, lugar.getBairro());
            st.setString(6, lugar.getRua());
            st.setInt(7, lugar.getComplemento());

            st.executeUpdate();
            status = true;

        } catch (SQLException e) {
            System.err.println(e);
        }
        return status;
    }

    public List<Lugar> getAll() {
        return get("");
    }

    public List<Lugar> get(String orderBy) {
        List<Lugar> lugares = new ArrayList<>();

        try {
            String sql = "SELECT * FROM lugar" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Lugar l = new Lugar(rs.getInt("id"), rs.getString("nome"), rs.getString("descricao"),
                        rs.getString("categoria"), rs.getString("cidade"), rs.getString("bairro"),
                        rs.getString("rua"), rs.getInt("complemento"));

                lugares.add(l);
            }

        } catch (SQLException e) {
            System.err.println(e);
        }

        return lugares;
    }

    public Lugar getByLugarId(int lugarId) {
        Lugar lugar = null;

        try {
            PreparedStatement st = connection.prepareStatement("SELECT * FROM lugar WHERE id = ?;",
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            st.setInt(1, lugarId);

            ResultSet rs = st.executeQuery();

            rs.first();

            lugar = new Lugar(rs.getInt("id"), rs.getString("nome"), rs.getString("descricao"),
                    rs.getString("categoria"), rs.getString("cidade"), rs.getString("bairro"), rs.getString("rua"),
                    rs.getInt("complemento"));

            st.close();

        } catch (SQLException e) {
            System.err.println(e);
        }

        return lugar;
    }

    public boolean update(Lugar lugar) {
        boolean status = false;
        try {
            String sql = "UPDATE lugar SET nome = ?, descricao = ?, categoria = ?, cidade = ?, bairro = ?, rua = ?, complemento = ? WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, lugar.getNome());
            st.setString(2, lugar.getDescricao());
            st.setString(3, lugar.getCategoria());
            st.setString(4, lugar.getCidade());
            st.setString(5, lugar.getBairro());
            st.setString(6, lugar.getRua());
            st.setInt(7, lugar.getComplemento());
            st.setInt(8, lugar.getId());

            st.executeUpdate();
            status = true;

        } catch (SQLException e) {
            System.err.println(e);
        }
        return status;
    }

    public boolean delete(int id) {
        boolean status = false;
        try {
            String sql = "DELETE FROM lugar WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
            status = true;

        } catch (SQLException e) {
            System.err.println(e);
        }
        return status;
    }

}
