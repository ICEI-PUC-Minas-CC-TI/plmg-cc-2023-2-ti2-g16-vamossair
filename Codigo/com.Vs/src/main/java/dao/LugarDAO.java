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
            PreparedStatement st = connection.prepareStatement("SELECT * FROM lugar WHERE id = ?;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
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

}
