package app;

import static spark.Spark.*;
import java.util.HashMap;
import service.UserService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.velocity.VelocityTemplateEngine;

public class Aplicacao {

	public static UserService userService = new UserService();
	private static VelocityTemplateEngine engine = new VelocityTemplateEngine();

	public static void main(String[] args) {

		staticFiles.location("/public");

		get("/login", Aplicacao::login, engine);
		post("/login", (request, response) -> userService.login(request, response));

		get("/register", Aplicacao::cadastro, engine);
		post("/register", (request, response) -> userService.register(request, response));

		get("/profile", Aplicacao::profile, engine);
		get("/logout", (request, response) -> userService.logout(request, response));

	}

	public static ModelAndView cadastro(Request req, Response response) {
		HashMap<String, Object> model = new HashMap<>();

		return new ModelAndView(model, "view/cadastro.html");
	}

	public static ModelAndView login(Request req, Response response) {
		HashMap<String, Object> model = new HashMap<>();

		return new ModelAndView(model, "view/login.html");
	}

	public static ModelAndView profile(Request req, Response response) {
		HashMap<String, Object> model = new HashMap<>();
		
		if (req.cookies().get("session") == null) {

			response.redirect("/login");
			return null;

		} else {

			int userId = Integer.parseInt(req.cookies().get("session"));
			model.put("user", userService.getUserDAO().getById(userId));
			return new ModelAndView(model, "view/perfil.vm");

		}
	}

}
