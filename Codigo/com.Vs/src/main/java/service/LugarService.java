package service;

import dao.LugarDAO;

public class LugarService {
    private LugarDAO lugarDAO = new LugarDAO();

	public LugarDAO getLugarDAO() {
		return lugarDAO;
	}
}
