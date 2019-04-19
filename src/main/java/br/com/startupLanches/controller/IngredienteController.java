package br.com.startupLanches.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.startupLanches.exception.IngredienteNotFoundException;
import br.com.startupLanches.model.Ingrediente;
import br.com.startupLanches.service.IngredienteService;

@CrossOrigin
@RestController
@RequestMapping(value="/ingredientes")
public class IngredienteController
{

	@Autowired
	private IngredienteService ingredienteService;

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Ingrediente>> getPedidos()
	{
		return ResponseEntity.ok(ingredienteService.getIngredientes());
	}

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Ingrediente> saveIngrediente(@RequestBody Ingrediente ingrediente)
	{
		return ResponseEntity.ok(ingredienteService.saveIngrediente(ingrediente));
	}

	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<Ingrediente> updateIngrediente(@RequestBody Ingrediente ingrediente)
			throws IngredienteNotFoundException
	{
		return ResponseEntity.ok(ingredienteService.updateIngrediente(ingrediente));
	}
}
