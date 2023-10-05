package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
   
   private String url;
   private String hostname;
   private int port;
   private String database;
   private String username;
   private String password;
   
   protected Connection connection;
   
   public DAO() {
	  connection = null;
   }
   
   public void conectar() {
	   try {
		   Class.forName("org.postgresql.Driver");
		   
		   hostname = "localhost";
		   port = 5432;
		   database = "dbVs";
		   username = "postgres";
		   password = "ti@cc";
		   
		   url = "jdbc:postgresql://" + hostname + ":" + port + "/" + database;
		   
		   this.connection = DriverManager.getConnection(url,username,password);
		   
	   }catch(SQLException e) {
		   System.err.print("Erro ao conectar");
	   }catch(Exception e) {
		   System.err.print("Erro geral");
	   }
	   
   }
   
   public void closeDAO() {
	   try {
		   connection.close();
	   }catch(Exception e) {
		   System.err.print("Erro ao desconectar");
	   }
   }
}
