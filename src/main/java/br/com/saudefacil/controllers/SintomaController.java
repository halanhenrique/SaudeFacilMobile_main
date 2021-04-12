package br.com.saudefacil.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.saudefacil.dao.SintomaDAO;
import br.com.saudefacil.models.Sintoma;

public class SintomaController {
	private SintomaDAO SintomaDAO = new SintomaDAO();

	public List<Sintoma> getSintoma() {
		return SintomaDAO.getSintoma();
	}

	public void update(Sintoma sintoma) {
		SintomaDAO.update(sintoma);
	}
	
	/*
	@GetMapping
	public ResponseEntity<List<Sintoma>> listar() {
		List<Sintoma> sintomas = this.getSintoma();
		return ResponseEntity.status(HttpStatus.OK).body(sintomas);
	}
	*/
}
