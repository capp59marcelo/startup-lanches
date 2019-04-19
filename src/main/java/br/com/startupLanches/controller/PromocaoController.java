package br.com.startupLanches.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.startupLanches.util.GeradorDePromocao;

@RestController
@CrossOrigin
@RequestMapping(value = "/promocao")
public class PromocaoController
{

	@Autowired
	private GeradorDePromocao geradorDePromocao;

	@RequestMapping(produces = "application/json", method=RequestMethod.GET)
	public ResponseEntity<?> getPromocoes()
	{
		return ResponseEntity.ok(geradorDePromocao.getPromocao());
	}
}
