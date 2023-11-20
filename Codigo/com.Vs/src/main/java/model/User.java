package model;

public class User {
  // Atributos
  private int id;
  private String nome;
  private String email;
  private String senha;
  private Integer nivel;
  
  // Construtores
  public User(String nome, String email, String senha) {
	  setNome(nome);
	  setEmail(email);
	  setSenha(senha);
  }

  public User(int id,String nome, String email, String senha, Integer nivel) {
	  setId(id);
	  setNome(nome);
	  setEmail(email);
	  setSenha(senha);
    setNivel(nivel);
  }
  
  // Getters
  public int getId() {
	  return this.id;
  }
  
  public String getNome() {
	  return this.nome;
  }
  
  public String getEmail() {
	  return this.email;
  }
  
  public String getSenha() {
	  return this.senha;
  }

  public Integer getNivel() {
	  return this.nivel;
  }
  
  // Setters
  public void setId(int id) {
	  this.id = id;
  }
  
  public void setNome(String nome) {
	  this.nome = nome;
  }
  
  public void setEmail(String email) {
	  this.email = email;
  }
  
  public void setSenha(String senha) {
	  this.senha = senha;
  }

  public void setNivel(Integer nivel){
    this.nivel = nivel;
  }
}

