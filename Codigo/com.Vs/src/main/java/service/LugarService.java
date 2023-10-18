package service;

import java.util.ArrayList;
import java.util.List;
import dao.LugarDAO;
import model.Lugar;

public class LugarService {
    private LugarDAO lugarDAO = new LugarDAO();

	public LugarDAO getLugarDAO() {
		return lugarDAO;
	}

	public List<Lugar> getLugaresFavoritos(List<Integer> lugaresFavoritos){
		List<Lugar> lugares = new ArrayList<Lugar>();

		for (Integer lugarId : lugaresFavoritos) {
				lugares.add(lugarDAO.getByLugarId(lugarId));
		}

		return lugares;
	}
}
