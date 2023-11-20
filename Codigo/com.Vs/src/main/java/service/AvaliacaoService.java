package service;

import java.util.List;
import dao.AvaliacaoDAO;
import dao.UserDAO;
import model.Avaliacao;
import model.User;
import spark.Request;
import spark.Response;

public class AvaliacaoService {

    private AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
    private UserDAO userDAO = new UserDAO();

    public List<Avaliacao> getAvaliacoes(int lugarId) {
        return avaliacaoDAO.getByLugarId(lugarId);
    }

    public Object avaliar(Request request, Response response) {
        int user_id = Integer.parseInt(request.cookies().get("session"));
        int lugar_id = Integer.parseInt(request.params(":id"));
        int nota = 6 - Integer.valueOf(request.queryParams("rate"));
        String comentario = request.queryParams("comentario");

        User user = userDAO.getById(user_id);
        user.setNivel(user.getNivel() + 10);
        Avaliacao avalicao = new Avaliacao(user_id, lugar_id, nota, comentario);

        if (avaliacaoDAO.insert(avalicao) == true && userDAO.update(user) == true) {
            response.status(201);
            response.redirect("/");
        } else {
            response.status(404);
        }

        return response;
    }
}
