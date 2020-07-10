package com.planeta.resoucers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planeta.modelo.Planeta;
import com.planeta.repository.PlanetaRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api")
@Api(value = "PI REST Planetas" )
@CrossOrigin(origins = "*")
public class PlanetaResource {
	@Autowired
	PlanetaRepository planetaRepository;
	@GetMapping("/planetas")
	@ApiOperation(value = "Retirna todos os planetas")
	public List<Planeta> listaPlaneta(){
		return planetaRepository.findAll();
	}
	@GetMapping("/planeta/{id}")
	@ApiOperation(value = "Busca pelo identificador")
	public Planeta listaPlanetaUnico(@PathVariable(value = "id") long id){
		return planetaRepository.findById(id);
	}
	@GetMapping("/planeta/nome/{nome}")
	@ApiOperation(value = "Busca por nome")
	public Planeta listaPlanetaUnicoString(@PathVariable(value = "nome") String nome){
		return planetaRepository.findByNome(nome);
	}
	@PostMapping("/salvar")
	@ApiOperation(value = "Salva um planeta")
	public Planeta salvaPlaneta(@RequestBody Planeta planeta) {
		return planetaRepository.save(planeta);
	}
	@DeleteMapping("/planeta")
	@ApiOperation(value = "Exclui um planeta")
	public void deletaPlaneta(@RequestBody Planeta planeta) {
		planetaRepository.delete(planeta);
	}
	@PutMapping("/planeta")
	@ApiOperation(value = "Atualiza um planeta")
	public Planeta  atualizaPlaneta(@RequestBody Planeta planeta) {
		return planetaRepository.save(planeta);
	}

	
	
	
}
