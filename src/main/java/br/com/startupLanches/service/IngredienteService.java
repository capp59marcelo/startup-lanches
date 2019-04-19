package br.com.startupLanches.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.startupLanches.exception.IngredienteNotFoundException;
import br.com.startupLanches.model.Ingrediente;
import br.com.startupLanches.repository.IngredienteRepository;

@Service
public class IngredienteService
{
	@Autowired
	private IngredienteRepository ingredienteRepository;

	public Ingrediente saveIngrediente(Ingrediente ingrediente)
	{
		return ingredienteRepository.save(ingrediente);
	}

	public Ingrediente getIngrediente(Long id) throws IngredienteNotFoundException
	{
		Optional<Ingrediente> pedido = ingredienteRepository.findById(id);
		if (pedido == null)
		{
			throw new IngredienteNotFoundException("Ingrediente nao encontrado");
		}
		return pedido.get();
	}

	public Ingrediente updateIngrediente(Ingrediente ingrediente) throws IngredienteNotFoundException
	{
		if (ingrediente.getId() == null)
		{
			throw new IngredienteNotFoundException("Id do pedido foi enviado com valor null");
		}
		Ingrediente possivelIngrediente = getIngrediente(ingrediente.getId());
		possivelIngrediente.setNome(ingrediente.getNome());
		possivelIngrediente.setPreco(ingrediente.getPreco());
		saveIngrediente(possivelIngrediente);
		return possivelIngrediente;
	}

	public List<Ingrediente> getIngredientes()
	{
		return ingredienteRepository.findAll();
	}

	public Ingrediente getIngrediente(String nome)
	{
		return ingredienteRepository.findByNomeIgnoreCase(nome);
	}
}
