package service;

import dao.AvaliacaoDAO;
import dao.FavoritoDAO;
import dao.UserDAO;
import spark.Request;
import spark.Response;
import model.User;
import java.security.*;
import java.math.*;

public class UserService {
	private UserDAO userDAO = new UserDAO();
	private FavoritoDAO favoritoDAO = new FavoritoDAO();
	private AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();

	public User getUserById(int id){
        return userDAO.getById(id);
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

	public Object remove(Request request, Response response){
    	int user_id = Integer.parseInt(request.cookies().get("session"));
        
       	User user = userDAO.getById(user_id);

        if(favoritoDAO.deleteByUserId(user_id) && avaliacaoDAO.deleteByUserId(user_id) && userDAO.delete(user.getId())){
            response.status(201);
            response.redirect("/logout");
        }else{
            response.status(404);
        }

        return response;
    }

	public Object atualizar(Request request, Response response){
		int user_id = Integer.parseInt(request.cookies().get("session"));
		User user = userDAO.getById(user_id);
	
		//obtenha os valores dos campos nome e email que o usuário digitou no formulário
		String novoNome = request.queryParams("nome");
		String novoEmail = request.queryParams("email");
	
		//verifique se o usuário digitou um novo nome e atualize-o se necessário
		if (novoNome != null && !novoNome.isEmpty()) {
			user.setNome(novoNome);
		}
	
		//verifique se o usuário digitou um novo email e atualize-o se necessário
		if (novoEmail != null && !novoEmail.isEmpty()) {
			user.setEmail(novoEmail);
		}
	
		if (userDAO.update(user)) {
			response.status(201);
			response.redirect("/profile");
		} else {
			response.status(404);
		}
	
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
