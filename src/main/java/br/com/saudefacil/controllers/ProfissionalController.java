package br.com.saudefacil.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.saudefacil.dao.ProfissionalDAO;
import br.com.saudefacil.exception.ProfissionalException;
import br.com.saudefacil.models.Profissional;

@RestController
@RequestMapping("/api/profissionais")
public class ProfissionalController {
	private ProfissionalDAO profissionaldao = new ProfissionalDAO();
	private static final int PROFISSIONAL_DESATIVADO = 0;
	
	public void create(Profissional profissional) {
		validaProfissional(profissional);
		profissionaldao.create(profissional);
	}
	
	public List<Profissional> getProfissionais(){
		return profissionaldao.getProfissionais();
	}
	
	public Profissional getProfissional(String cpf) {
		return profissionaldao.getProfissional(cpf);
	}
	
	public void update(Profissional profissional) {
		validaProfissional(profissional);
		profissionaldao.update(profissional);
	}
	public void delete(Profissional profissional) {
		profissionaldao.delete(profissional);
	}
	
	public void validaProfissional(Profissional profissional) {
		if(profissional.getAtendeClinica() != 1 && profissional.getAtendeClinica() != 0) {
			throw new ProfissionalException("Digite 1 para SIM ou 0 para NÃO");
		}
		
		if(profissional.getStatusAutonomo() != 1 && profissional.getStatusAutonomo() != 0) {
			throw new ProfissionalException("Digite 1 para SIM ou 0 para NÃO");
		}
		
		if((!profissional.getCredencial().substring(0,3).equalsIgnoreCase("CRP"))
				&& (!profissional.getCredencial().substring(0,3).equalsIgnoreCase("CRO"))
				&& (!profissional.getCredencial().substring(0,3).equalsIgnoreCase("CRM"))
				&& (!profissional.getCredencial().substring(0,3).equalsIgnoreCase("CRN"))
				&& (!profissional.getCredencial().substring(0,3).equalsIgnoreCase("CFM"))){
			throw new ProfissionalException("Credencial inv�lida");
		}
	}
	
	public void desativarProfissional(Profissional profissional) {
		if(profissional == null) {
			throw new ProfissionalException("Profissional inexistente");
		}
		if(profissional.getStatusProfissional() == 0) {
			throw new ProfissionalException("Profissional já encontra-se desativado");
		}
		 profissional.setStatusProfissional(PROFISSIONAL_DESATIVADO);
		 profissionaldao.update(profissional);
	}
	
	
	@PostMapping(path = "/new")
	public ResponseEntity<Profissional> cadastrar(@RequestBody Profissional profissional, BindingResult result) {
		
		this.create(profissional);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{credencial}").buildAndExpand(profissional.getCredencial())
				.toUri();
		//response.setData(viagemSalva);
		return ResponseEntity.created(location).body(profissional);
	}

	@GetMapping
	public ResponseEntity<List<Profissional>> listar() {
		List<Profissional> profissionais = this.getProfissionais();
		return ResponseEntity.status(HttpStatus.OK).body(profissionais);
	}

	@GetMapping(path = "/{credencial}")
	public ResponseEntity<Profissional> buscar(@PathVariable("credencial") String credencial) {
  
		Profissional profissional = this.getProfissional(credencial);
		//Pessoa response = new Response<Viagem>();
		//response.setData(viagem);
		return ResponseEntity.status(HttpStatus.OK).body(profissional);
	}

}