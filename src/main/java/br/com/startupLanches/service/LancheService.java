package br.com.startupLanches.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.startupLanches.model.Ingrediente;
import br.com.startupLanches.model.Lanche;
import br.com.startupLanches.repository.LancheRepository;
import br.com.startupLanches.util.CalculaValorTotal;
import br.com.startupLanches.util.GeradorDePromocao;

@Service
public class LancheService
{

	@Autowired
	public LancheRepository lancheRepository;

	@Autowired
	private CalculaValorTotal calculaValorTotal;

	@Autowired
	private GeradorDePromocao geradorDePromocao;

	@Autowired
	private IngredienteService ingredienteService;

	public Lanche saveLanche(Lanche lanche)
	{
		return lancheRepository.save(lanche);
	}

	public String getLanchesValor(List<Lanche> lanches)
	{
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		for (Lanche lanche : lanches)
		{
			lanche.setPromocoes(geradorDePromocao.getPromocao());
			lanche.setValor(calculaValorTotal.calculaValorLanche(lanche));
		}
		return gson.toJson(lanches);
	}

	public String getLanchesCardapio()
	{

		Ingrediente bacon = ingredienteService.getIngrediente("bacon");
		Ingrediente hamburguerCarne = ingredienteService.getIngrediente("hamburguer de carne");
		Ingrediente ovo = ingredienteService.getIngrediente("ovo");
		Ingrediente queijo = ingredienteService.getIngrediente("queijo");

		bacon.setLanches(null);
		hamburguerCarne.setLanches(null);
		ovo.setLanches(null);
		queijo.setLanches(null);

		List<Lanche> lanches = new ArrayList<Lanche>();
		lanches.add(new Lanche("X-Bacon", Arrays.asList(bacon, hamburguerCarne, queijo)));
		lanches.add(new Lanche("X-Burguer", Arrays.asList(hamburguerCarne, queijo)));
		lanches.add(new Lanche("X-Egg", Arrays.asList(ovo, hamburguerCarne, queijo)));
		lanches.add(new Lanche("X-Egg Bacon", Arrays.asList(ovo, bacon, hamburguerCarne, queijo)));

		return getLanchesValor(lanches);
	}

}