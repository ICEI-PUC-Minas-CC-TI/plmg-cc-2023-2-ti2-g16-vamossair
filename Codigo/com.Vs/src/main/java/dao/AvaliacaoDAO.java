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

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, avaliacao.getUserId());
            preparedStatement.setInt(2, avaliacao.getLugarId());
            preparedStatement.setInt(3, avaliacao.getNota());
            preparedStatement.setString(4, avaliacao.getComentario());


            preparedStatement.executeUpdate();
            preparedStatement.close();

            status = true;
        } catch (SQLException e) {
            System.err.println(e);
        }

        return status;
    }

    public List<Avaliacao> getByLugarId(int lugarId) {
        List<Avaliacao> avaliacoes = new ArrayList<Avaliacao>();

        try {
            Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String sql = "SELECT * FROM avaliacoes WHERE lugar_id = " + lugarId + ";";

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Avaliacao avaliacao = new Avaliacao(rs.getInt("id"), rs.getInt("usuario_id"), rs.getInt("lugar_id"),
                        rs.getInt("nota"),
                        rs.getString("comentario"));
                avaliacoes.add(avaliacao);
            }

            st.close();

        } catch (SQLException e) {
            System.err.println(e);
        }

        return avaliacoes;
    }
}
