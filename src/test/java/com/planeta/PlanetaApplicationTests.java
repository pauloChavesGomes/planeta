package com.planeta;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.planeta.modelo.Planeta;

import kong.unirest.json.JSONObject;

@SpringBootTest
class PlanetaApplicationTests {

	@Test
	void contextLoads() {
		PopulaBancoTeste popula = new PopulaBancoTeste();
		List<JSONObject> lista = popula.listarPlanetas();
    	List<Planeta> listaPlaneta = new ArrayList<Planeta>();
    	
	    for(JSONObject obj:lista) {
	    	Planeta planeta = new Planeta();
	    	planeta.setId(null);
	    	planeta.setNome(obj.getString("name"));
	    	planeta.setClima(obj.getString("climate"));
	    	planeta.setTerreno( obj.getString("terrain"));
	    	listaPlaneta.add(planeta);
	    	popula.salvar(planeta);
	    	
	      }
		}
	}


