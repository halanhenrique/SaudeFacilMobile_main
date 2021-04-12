package br.com.saudefacil.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.saudefacil.dao.ConsultaPacienteDAO;
import br.com.saudefacil.models.ConsultaPaciente;
import br.com.saudefacil.models.Profissional;

public class ConsultaPacienteController {
	
ConsultaPacienteDAO consultaPacienteDAO = new ConsultaPacienteDAO();
	
	public List<ConsultaPaciente> getEspecialista(String sintoma){
		return consultaPacienteDAO.getEspecialista(sintoma);
	}
	

}
