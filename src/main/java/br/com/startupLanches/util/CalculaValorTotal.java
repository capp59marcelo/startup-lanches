package br.com.startupLanches.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.startupLanches.model.Ingrediente;
import br.com.startupLanches.model.Lanche;
import br.com.startupLanches.service.IngredienteService;

@Component
public class CalculaValorTotal
{

	@Autowired
	private IngredienteService ingredienteService;

	public BigDecimal calculaValorTotal(List<Lanche> lanches)
	{
		BigDecimal valor = new BigDecimal(0.00);
		for (Lanche lanche : lanches)
		{
			valor = valor.add(calculaValorLanche(lanche));
		}
		return valor;
	}

	public BigDecimal calculaValorLanche(Lanche lanche)
	{
		BigDecimal valor = new BigDecimal(0.00);
		HashMap<String, Integer> mapaDeIngredientes = carregaIngredientes(lanche.getIngredientes());
		switch (lanche.promocoes)
		{
		case LIGHT:
			valor = valor.add(calculaValorIngredientes(mapaDeIngredientes, "Light"));
			break;
		case MUITA_CARNE:
			valor = valor.add(calculaValorIngredientes(mapaDeIngredientes, "Hamburguer de Carne"));
			break;
		case MUITO_QUEIJO:
			valor = valor.add(calculaValorIngredientes(mapaDeIngredientes, "Queijo"));
			break;
		default:
			valor = valor.add(calculaValorIngredientes(mapaDeIngredientes, "Nada"));
		}
		return valor;
	}

	public BigDecimal calculaValorIngredientes(HashMap<String, Integer> mapaDeIngredientes,
			String ingredienteComDesconto)
	{
		BigDecimal valorTotal = new BigDecimal(0.00);
		for (Map.Entry<String, Integer> entry : mapaDeIngredientes.entrySet())
		{
			String k = entry.getKey();
			Integer v = entry.getValue();
			Ingrediente ingrediente = ingredienteService.getIngrediente(k);
			valorTotal = valorTotal.add(new BigDecimal(v).multiply(ingrediente.getPreco()));
			if (ingrediente.getNome().equalsIgnoreCase(ingredienteComDesconto))
			{
				Integer ingredientesDesconto = v / 3;
				valorTotal = valorTotal.subtract(new BigDecimal(ingredientesDesconto).multiply(ingrediente.getPreco()));
			}
		}
		if (ingredienteComDesconto.equalsIgnoreCase("Light") && mapaDeIngredientes.containsKey("Alface")
				&& !mapaDeIngredientes.containsKey("Bacon"))
		{
			valorTotal = valorTotal.subtract(valorTotal.multiply(new BigDecimal(0.10)));
		}
		valorTotal = valorTotal.setScale(2, RoundingMode.HALF_UP);
		return valorTotal;
	}

	public HashMap<String, Integer> carregaIngredientes(List<Ingrediente> ingredientes)
	{
		HashMap<String, Integer> mapaDeIngredientes = new HashMap<>();
		for (Ingrediente ingrediente : ingredientes)
		{
			if (mapaDeIngredientes.get(ingrediente.getNome()) == null)
			{
				mapaDeIngredientes.putIfAbsent(ingrediente.getNome(), 1);
			} else
			{
				mapaDeIngredientes.computeIfPresent(ingrediente.getNome(), (k, v) -> v + 1);
			}
		}
		return mapaDeIngredientes;
	}
}