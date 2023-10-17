package service;

import dao.UserDAO;
import spark.Request;
import spark.Response;
import model.User;
import java.security.*;
import java.math.*;

public class UserService {
	private UserDAO userDAO = new UserDAO();

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public Object register(Request request, Response response) {
		String email = request.queryParams("email");
		String nome = request.queryParams("nome");
		String senha = criptografia(request.queryParams("senha"));

		User user = new User(nome, email, senha);

		if (userDAO.insert(user) == true) {
			response.status(201);
			response.redirect("/login");
		} else {
			response.status(404);
		}

		return response;
	}

	public Object login(Request request, Response response) {
		String email = request.queryParams("email");
		String senha = criptografia(request.queryParams("senha"));

		User user = userDAO.get(email, senha);

		if (user != null) {
			response.cookie("session", Integer.toString(user.getId()));

			response.redirect("/profile");
		} else {
			response.status(401);
			response.redirect("/login");
		}

		return response;
	}

	public Object logout(Request request, Response response) {

		response.removeCookie("session");
		response.redirect("/login");

		return response;
	}
	
	private String criptografia(String senha) {
		String senhaHash = "";
		try {
			MessageDigest m = MessageDigest.getInstance("MD5");

			m.update(senha.getBytes(), 0, senha.length());
			senhaHash = new BigInteger(1, m.digest()).toString(16);

		} catch (NoSuchAlgorithmException e) {
			System.err.println(e);
		}

		return senhaHash;
	}

}
