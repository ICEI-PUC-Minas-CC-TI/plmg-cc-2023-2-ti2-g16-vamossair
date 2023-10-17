package app;

import static spark.Spark.*;
import java.util.HashMap;
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

	public static void main(String[] args) {

		staticFiles.location("/public");

		get("/login", Aplicacao::login, engine);
		post("/login", (request, response) -> userService.login(request, response));

		get("/register", Aplicacao::cadastro, engine);
		post("/register", (request, response) -> userService.register(request, response));

		get("/profile", Aplicacao::profile, engine);
		get("/logout", (request, response) -> userService.logout(request, response));

		get("/", Aplicacao::home, engine);

		post("/favorite/:id", (request, response) -> favoritoService.favoritar(request, response));
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
			model.put("user", userService.getUserDAO().getById(userId));
			return new ModelAndView(model, "view/perfil.vm");

		}
	}

	public static ModelAndView home(Request request, Response response) {
		HashMap<String, Object> model = new HashMap<>();

		if (request.cookies().get("session") == null) {

			response.redirect("/login");
			return null;

		} else {
			model.put("lugares", lugarService.getLugarDAO().getAll());
			model.put("nota", 4.5);
			return new ModelAndView(model, "view/index.vm");

		}
	}

}
