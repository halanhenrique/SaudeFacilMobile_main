package br.com.saudefacil.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.saudefacil.dao.RelatorioDAO;
import br.com.saudefacil.models.Relatorio;

public class RelatorioController {
	RelatorioDAO relatorioDAO = new RelatorioDAO();
	
	public List<Relatorio> getRelatorio(){
		return relatorioDAO.getRelatorio();
	}
	
	/*
	@GetMapping
	public ResponseEntity<List<Relatorio>> listar() {
		List<Relatorio> relatorios = this.getRelatorio();
		return ResponseEntity.status(HttpStatus.OK).body(relatorios);
	}
	*/
}
