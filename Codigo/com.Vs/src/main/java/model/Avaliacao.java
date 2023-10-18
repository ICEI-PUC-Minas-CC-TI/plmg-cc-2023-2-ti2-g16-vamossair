package model;

public class Avaliacao {
    // Atributos
    private int id;
    private int user_id;
    private int lugar_id;
    private int nota;
    private String comentario;
    private String user_name;

    // Construtores
    public Avaliacao(int id, int user_id, int lugar_id, int nota, String comentario) {
        setId(id);
        setUserId(user_id);
        setLugarId(lugar_id);
        setNota(nota);
        setComentario(comentario);
    }

    public Avaliacao(int user_id, int lugar_id, int nota, String comentario) {
        setUserId(user_id);
        setLugarId(lugar_id);
        setNota(nota);
        setComentario(comentario);
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

    public int getNota(){
        return this.nota;
    }

    public String getComentario(){
        return this.comentario;
    }

    public String getUserName(){
        return this.user_name;
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

    public void setNota(int nota){
        this.nota = nota;
    }

    public void setComentario(String comentario){
        this.comentario = comentario;
    }

     public void setUserName(String user_name){
        this.user_name = user_name;
    }
}
