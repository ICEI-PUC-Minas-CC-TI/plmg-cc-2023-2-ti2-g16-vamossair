package model;

public class Avaliacao {
    // Atributos
    private int id;
    private int user_id;
    private int lugar_id;
    private int nota;
    private String comentario;

    // Construtores
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
}
