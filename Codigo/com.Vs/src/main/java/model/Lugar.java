package model;

public class Lugar {
   // Atributos
   private String nome;
   private String descricao;
   private String cidade;
   private String bairro;
   private String rua;
   private int complemento;
   
   // Construtores
   public Lugar() {
	   this("","","","","",0);
   }
   
   public Lugar(String nome, String descricao, String cidade, String bairro, String rua, int complemento) {
	   setNome(nome);
	   setDescricao(descricao);
	   setCidade(cidade);
	   setBairro(bairro);
	   setRua(rua);
	   setComplemento(complemento);
   }
   
   // Getters
   public String getNome() {
	   return this.nome;
   }
   
   public String getDescricao() {
	   return this.descricao;
   }
   
   public String getCidade() {
	   return this.cidade;
   }
   
   public String getBairro() {
	   return this.bairro;
   }
   
   public String getRua() {
	   return this.rua;
   }
   
   public int getComplemento() {
	   return this.complemento;
   }
   
   // Setters
   public void setNome(String nome) {
	   this.nome = nome;
   }
   
   public void setDescricao(String descricao) {
	   this.descricao = descricao;
   }
   
   public void setCidade(String cidade) {
	   this.cidade = cidade;
   }
   
   public void setBairro(String bairro) {
	   this.bairro = bairro;
   }
   
   public void setRua(String rua) {
	   this.rua = rua;
   }
   
   public void setComplemento(int complemento) {
	   this.complemento = complemento;
   }
   
   // Outros métodos
   
}
