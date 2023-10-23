package model;

public class Lugar {
   // Atributos
   private int id;
   private String nome;
   private String descricao;
   private String categoria;
   private String cidade;
   private String bairro;
   private String rua;
   private int complemento;
   private double nota;
   
   // Construtores
   public Lugar(int id, String nome, String categoria, String descricao, String cidade, String bairro, String rua, int complemento) {
	   setId(id);
      setNome(nome);
      setCategoria(categoria);
	   setDescricao(descricao);
	   setCidade(cidade);
	   setBairro(bairro);
	   setRua(rua);
	   setComplemento(complemento);
   }
   
   public Lugar(String nome, String categoria, String descricao, String cidade, String bairro, String rua, int complemento) {
	   setNome(nome);
      setCategoria(categoria);
	   setDescricao(descricao);
	   setCidade(cidade);
	   setBairro(bairro);
	   setRua(rua);
	   setComplemento(complemento);
   }
   
   // Getters
   public int getId(){
      return this.id;
   }

   public String getNome() {
	   return this.nome;
   }

   public String getCategoria() {
	   return this.categoria;
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

   public double getNota() {
	   return this.nota;
   }
   
   // Setters
   public void setId(int id){
      this.id = id;
   }

   public void setNome(String nome) {
	   this.nome = nome;
   }

   public void setCategoria(String categoria){
      this.categoria = categoria;
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

   public void setNota(double nota) {
	   this.nota = nota;
   }
   
   // Outros métodos
   
}
