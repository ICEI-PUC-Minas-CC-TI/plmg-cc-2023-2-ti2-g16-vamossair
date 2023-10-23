package app;

import static spark.Spark.*;
import java.util.HashMap;
import java.util.List;
import model.Avaliacao;
import model.User;
import service.AvaliacaoService;
import service.FavoritoService;
import service.LugarService;
import service.UserService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.velocity.VelocityTemplateEngine;

public class Aplicacao {

	private static UserService userService = new UserService();
	private static LugarService lugarService = new LugarService();
	private static FavoritoService favoritoService = new FavoritoService();
	private static VelocityTemplateEngine engine = new VelocityTemplateEngine();
	private static AvaliacaoService avaliacaoService = new AvaliacaoService();

	public static void main(String[] args) {

		staticFiles.location("/public");

		get("/login", Aplicacao::login, engine);
		post("/login", (request, response) -> userService.login(request, response));

		get("/register", Aplicacao::cadastro, engine);
		post("/register", (request, response) -> userService.register(request, response));

		get("/profile", Aplicacao::profile, engine);
		get("/logout", (request, response) -> userService.logout(request, response));

		get("/", Aplicacao::home, engine);

		get("/favorite", Aplicacao::favorite, engine);
		post("/favorite/:id", (request, response) -> favoritoService.favoritar(request, response));
		post("/favorite/delete/:id", (request, response) -> favoritoService.remove(request, response));

		get("/avaliacoes/:id", Aplicacao::avaliacoes, engine);
		post("/avaliar/:id", (request, response) -> avaliacaoService.avaliar(request, response));
	}

	public static ModelAndView cadastro(Request request, Response response) {
		HashMap<String, Object> model = new HashMap<>();

		return new ModelAndView(model, "view/cadastro.html");
	}

	public static ModelAndView login(Request request, Response response) {
		HashMap<String, Object> model = new HashMap<>();

		return new ModelAndView(model, "view/login.html");
	}

	public static ModelAndView profile(Request request, Response response) {
		HashMap<String, Object> model = new HashMap<>();

		if (request.cookies().get("session") == null) {

			response.redirect("/login");
			return null;

		} else {

			int userId = Integer.parseInt(request.cookies().get("session"));
			model.put("user", userService.getUserById(userId));
			return new ModelAndView(model, "view/perfil.vm");

		}
	}

	public static ModelAndView home(Request request, Response response) {
		HashMap<String, Object> model = new HashMap<>();

		if (request.cookies().get("session") == null) {

			response.redirect("/login");
			return null;

		} else {
			model.put("lugares", lugarService.getAll());

			return new ModelAndView(model, "view/index.vm");

		}
	}

	public static ModelAndView favorite(Request request, Response response){
		HashMap<String, Object> model = new HashMap<>();

		if (request.cookies().get("session") == null) {

			response.redirect("/login");
			return null;

		} else {

			int userId = Integer.parseInt(request.cookies().get("session"));
			List<Integer> lugaresFavoritos = favoritoService.getByUserId(userId);
			
			model.put("lugares", lugarService.getLugaresFavoritos(lugaresFavoritos));

			return new ModelAndView(model, "view/favoritos.vm");

		}
	}

	public static ModelAndView avaliacoes(Request request, Response response){
		HashMap<String, Object> model = new HashMap<>();

		if (request.cookies().get("session") == null) {

			response.redirect("/login");
			return null;

		} else {

			int lugarId = Integer.parseInt(request.params(":id"));

			List<Avaliacao> avaliacoes = avaliacaoService.getAvaliacoes(lugarId);

			for (Avaliacao avaliacao : avaliacoes) {
				User usuario = userService.getUserById(avaliacao.getUserId());
				avaliacao.setUserName(usuario.getNome());
			}

			model.put("lugarId", lugarId);
			model.put("avaliacoes", avaliacoes);
			
			return new ModelAndView(model, "view/avaliacao.vm");

		}
	}


}
