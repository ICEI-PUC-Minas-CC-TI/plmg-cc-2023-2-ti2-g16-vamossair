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
                    + "VALUES ( '" + lugar.getNome() + "','" + lugar.getDescricao() + "', '" + lugar.getCategoria()
                    + "', '" + lugar.getCategoria() + "', '" + lugar.getCidade() + "', '" + lugar.getBairro() + "', '"
                    + lugar.getRua() + "', " + lugar.getComplemento() + ");";
            PreparedStatement st = connection.prepareStatement(sql);

            st.executeUpdate();
            st.close();
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
        List<Lugar> lugares = new ArrayList<Lugar>();

        try {

            Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String sql = "SELECT * FROM lugar" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Lugar l = new Lugar(rs.getInt("id"), rs.getString("nome"), rs.getString("descricao"),
                        rs.getString("categoria"), rs.getString("cidade"), rs.getString("bairro"), rs.getString("rua"),
                        rs.getInt("complemento"));

                lugares.add(l);
            }

            st.close();

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
            String sql = "UPDATE lugar SET nome = '" + lugar.getNome() + "', descricao = '" + lugar.getDescricao()
                    + "', categoria = '" + lugar.getCategoria() + "', cidade = '" + lugar.getCidade() + "', bairro = '"
                    + lugar.getBairro() + "', rua = '" + lugar.getRua() + "', complemento = " + lugar.getComplemento() +
                    " WHERE id = " + lugar.getId();
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
            Statement st = connection.createStatement();
            st.executeUpdate("DELETE FROM lugar WHERE id = " + id);
            st.close();
            status = true;
        } catch (SQLException e) {
            System.err.println(e);
        }
        return status;
    }

}
