package br.com.saudefacil.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.saudefacil.dao.EspecialidadesDAO;
import br.com.saudefacil.models.Especialidades;

public class EspecialidadesController {
	private EspecialidadesDAO especialidadesDAO = new EspecialidadesDAO();

	public List<Especialidades> getEspecialidades() {
		return especialidadesDAO.getEspecialidades();
		
	}
	
	@GetMapping
	public ResponseEntity<List<Especialidades>> listar() {
		List<Especialidades> especialidades = this.getEspecialidades();
		return ResponseEntity.status(HttpStatus.OK).body(especialidades);
	}
	
}
