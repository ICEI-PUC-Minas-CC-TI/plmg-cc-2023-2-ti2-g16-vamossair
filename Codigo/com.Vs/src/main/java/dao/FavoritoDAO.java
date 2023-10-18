package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Favorito;

public class FavoritoDAO extends DAO{
    
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

	public List<Integer> getByUserId(int userId){
		List<Integer> favoritos = new ArrayList<Integer>();

		 try {
			Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String sql = "SELECT lugar_id FROM favoritos WHERE usuario_id = " + userId + ";";

			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
                int lugarId = rs.getInt("lugar_id");
                favoritos.add(lugarId);
            }

			st.close();

        } catch (SQLException e) {
            System.err.println(e);
        }

		return favoritos;
	}
}
