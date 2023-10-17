package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Favorito;

public class FavoritoDAO extends DAO{
    
    public FavoritoDAO() {
		super();
		conectar();
	}

    public boolean insert(Favorito favorito) {
		boolean status = false;
	
		try {
			String sql = "INSERT INTO favorito (user_id, lugar_id) VALUES (?, ?)";
	
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
}
