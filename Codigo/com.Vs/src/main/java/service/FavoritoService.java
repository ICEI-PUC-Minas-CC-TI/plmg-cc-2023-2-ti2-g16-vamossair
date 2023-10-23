package service;

import java.util.List;
import dao.FavoritoDAO;
import model.Favorito;
import spark.Request;
import spark.Response;

public class FavoritoService {
    private FavoritoDAO favoritoDAO = new FavoritoDAO();

    public List<Integer> getByUserId(int id) {
        return favoritoDAO.getByUserId(id);
    }

    public Object favoritar(Request request, Response response) {
        int user_id = Integer.parseInt(request.cookies().get("session"));
        Integer lugar_id = Integer.parseInt(request.params(":id"));

        Favorito favorito = favoritoDAO.get(user_id, lugar_id);

        if (favorito != null) {
            response.status(409);
            response.redirect("/");
        } else {
            favorito = new Favorito(user_id, lugar_id);
            if (favoritoDAO.insert(favorito) == true) {
                response.status(201);
                response.redirect("/");
            } else {
                response.status(404);
            }
        }

        return response;
    }

    public Object remove(Request request, Response response){
        int user_id = Integer.parseInt(request.cookies().get("session"));
        Integer lugar_id = Integer.parseInt(request.params(":id"));
        
        Favorito favorito = favoritoDAO.get(user_id, lugar_id);

        if(favoritoDAO.delete(favorito.getId()) == true){
            response.status(201);
            response.redirect("/");
        }else{
             response.status(404);
        }

        return response;
    }

}
