package model;

public class Favorito {
    // Atributos
    private int id;
    private int user_id;
    private int lugar_id;

    // Construtores
    public Favorito(int id, int user_id, int lugar_id) {
        setId(id);
        setUserId(user_id);
        setLugarId(lugar_id);
    }

    public Favorito(int user_id, int lugar_id) {
        setUserId(user_id);
        setLugarId(lugar_id);
    }

    // Getters
    public int getId() {
        return this.id;
    }

    public int getUserId() {
        return this.user_id;
    }

    public int getLugarId() {
        return this.lugar_id;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public void setLugarId(int lugar_id) {
        this.lugar_id = lugar_id;
    }
}