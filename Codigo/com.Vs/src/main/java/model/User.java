package model;

public class User {
  // Atributos
  private String nome;
  private String email;
  private String senha;
  
  // Construtores
  public User() {
	  this("","","");
  }
  
  public User(String nome, String email, String senha) {
	  setNome(nome);
	  setEmail(email);
	  setSenha(senha);
  }
  
  // Getters
  public String getNome() {
	  return this.nome;
  }
  
  public String getEmail() {
	  return this.email;
  }
  
  public String getSenha() {
	  return this.senha;
  }
  
  // Setters
  public void setNome(String nome) {
	  this.nome = nome;
  }
  
  public void setEmail(String email) {
	  this.email = email;
  }
  
  public void setSenha(String senha) {
	  this.senha = senha;
  }
}
