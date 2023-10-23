package service;

import java.util.ArrayList;
import java.util.List;
import dao.AvaliacaoDAO;
import dao.LugarDAO;
import model.Avaliacao;
import model.Lugar;

public class LugarService {
    private LugarDAO lugarDAO = new LugarDAO();
	private AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();

	public List<Lugar> getAll() {
		List<Lugar> lugares = lugarDAO.getAll();

		for(Lugar lugar : lugares){
			double nota = 0;
			List<Avaliacao> avaliacoes = avaliacaoDAO.getByLugarId(lugar.getId());
			for(Avaliacao avaliacao : avaliacoes){
				nota += avaliacao.getNota();
			}
			nota /= avaliacoes.size();
			lugar.setNota(nota);
		}

		return lugares;
	}

	public List<Lugar> getLugaresFavoritos(List<Integer> lugaresFavoritos){
		List<Lugar> lugares = new ArrayList<Lugar>();

		for (Integer lugarId : lugaresFavoritos) {
			lugares.add(lugarDAO.getByLugarId(lugarId));
		}

		for(Lugar lugar : lugares){
			double nota = 0;
			List<Avaliacao> avaliacoes = avaliacaoDAO.getByLugarId(lugar.getId());
			for(Avaliacao avaliacao : avaliacoes){
				nota += avaliacao.getNota();
			}
			nota /= avaliacoes.size();
			lugar.setNota(nota);
		}

		return lugares;
	}

}
