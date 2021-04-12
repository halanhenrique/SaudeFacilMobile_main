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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.saudefacil.dao.ClinicaDAO;
import br.com.saudefacil.models.Clinica;
import br.com.saudefacil.models.Paciente;

public class ClinicaController {
	private static final int STATUS_CLINICA_ATIVADO = 1;
	ClinicaDAO clinicaDao = new ClinicaDAO();
	
	public void cadastrarClinica(Clinica clinica) {
		clinica.setStatusClinica(STATUS_CLINICA_ATIVADO);
		clinicaDao.create(clinica);
	}
	
	public List<Clinica> getClinicas() {
		return clinicaDao.getClinicas();
	}
	
}
