package com.planeta;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.planeta.modelo.Planeta;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

public class PopulaBancoTeste {
	
	//CLASSE USADA APENAS PARA POPULAR O BANCO COM DADOS DA API SWAPI A API de Guerra nas Estrelas
	public static List<JSONObject> listarPlanetas(){
		List<JSONObject> lista = new ArrayList<JSONObject>();
		boolean saida = false;
    	int cont = 1;   	
	    	while(saida == false) {
    		HttpResponse<JsonNode> bookResponse = Unirest.get("https://swapi.dev/api/planets/"+cont+"/").asJson(); 
    		
    		JSONObject json = new JSONObject();
    		json = bookResponse.getBody().getObject();
    		if(bookResponse.getStatus()!=404) {
    			lista.add(json);
    		}else {
    			saida = true;
    		}  		
    		cont++;   		
    	}
		
		return lista;
	}

	public static void main(String[] args) {		
		List<JSONObject> lista = listarPlanetas();
    	List<Planeta> listaPlaneta = new ArrayList<Planeta>();
    	
	    for(JSONObject obj:lista) {
	    	Planeta planeta = new Planeta();
	    	planeta.setId(null);
	    	planeta.setNome(obj.getString("name"));
	    	planeta.setClima(obj.getString("climate"));
	    	planeta.setTerreno( obj.getString("terrain"));
	    	listaPlaneta.add(planeta);
	    	salvar(planeta);
	    	
	      }
		}
	
	
		
	private static SessionFactory factory(Object classe){
		
		Configuration configuration = new Configuration();
		configuration.setProperty("hibernate.connection.username", "root");
		configuration.setProperty("hibernate.connection.password", "123456");
		configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/planeta?useTimezone=true&serverTimezone=UTC");
		configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
		configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
		configuration.setProperty("hibernate.hbm2ddl.auto", "update");
		configuration.setProperty("show_sql", "true");
		configuration.setProperty("format_sql", "true");
		configuration.setProperty("hibernate.archive.autodetection", "class, hbm");		
		configuration.addClass(((Planeta)classe).getClass());         
          return configuration.buildSessionFactory();
    }
	
	public static void salvar(Planeta planeta) {
			SessionFactory factory = factory(planeta);         
	       Session session = null;
	       Transaction tx = null;
	       try {
	           session = factory.openSession();
	           tx = session.beginTransaction();
	           session.save(planeta);
	           tx.commit();
	       } catch (Exception e) {
	           if (tx != null) tx.rollback();
	           System.out.println("Transação falhou : ");
	           e.printStackTrace();
	       } finally {
	           session.close();
	       }
	}
		

}
