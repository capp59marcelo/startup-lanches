package br.com.startupLanches.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.startupLanches.model.Lanche;
import br.com.startupLanches.service.LancheService;

@RestController
@CrossOrigin
@RequestMapping(value = "/lanches")
public class LancheController
{

	@Autowired
	private LancheService lancheService;

	@RequestMapping(value = "/cardapio", method = RequestMethod.GET)
	public ResponseEntity<?> getLanchesCardapio()
	{
		return ResponseEntity.ok(lancheService.getLanchesCardapio());
	}

	@RequestMapping(value = "/calcula-valor", method = RequestMethod.POST)
	public ResponseEntity<?> getLanchesDefault(@RequestBody List<Lanche> lanches)
	{
		return ResponseEntity.ok(lancheService.getLanchesValor(lanches));
	}
}
