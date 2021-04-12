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


import br.com.saudefacil.dao.PacienteDAO;
import br.com.saudefacil.exception.PacienteException;
import br.com.saudefacil.models.Paciente;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {
	private static final int PACIENTE_DESATIVADO = 0;
	private PacienteDAO pacienteDAO = new PacienteDAO();
	
	public void create(Paciente paciente) {
		pacienteDAO.create(paciente);
	}
	
	public void update(Paciente paciente) {
		pacienteDAO.update(paciente);
	}
	
	public void desativarPaciente(Paciente paciente) {
		if(paciente == null) {
			throw new PacienteException("Paciente inexistente");
		}
		if(paciente.getStatusPaciente() == 0) {
			throw new PacienteException("Paciente já encontra-se desativado");
		}
		paciente.setStatusPaciente(PACIENTE_DESATIVADO);
		pacienteDAO.update(paciente);
	}
	
	public Paciente getPaciente(String cpf) {
		return pacienteDAO.getPaciente(cpf);
	}
	
	public List<Paciente> getPacientes() {
		return pacienteDAO.getPacientes();
	}
	
	@PostMapping(path = "/new")
	public ResponseEntity<Paciente> cadastrar(@RequestBody Paciente paciente, BindingResult result) {
		
		this.create(paciente);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(paciente.getPacienteID())
				.toUri();
		//response.setData(viagemSalva);
		return ResponseEntity.created(location).body(paciente);
	}

	@GetMapping
	public ResponseEntity<List<Paciente>> listar() {
		List<Paciente> pacientes = this.getPacientes();
		return ResponseEntity.status(HttpStatus.OK).body(pacientes);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Paciente> buscar(@PathVariable("id") String id) {
  
		Paciente paciente = this.getPaciente(id);
		//Pessoa response = new Response<Viagem>();
		//response.setData(viagem);
		return ResponseEntity.status(HttpStatus.OK).body(paciente);
	}
}
