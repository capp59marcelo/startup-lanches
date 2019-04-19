package br.com.startupLanches;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.startupLanches.model.Ingrediente;
import br.com.startupLanches.service.IngredienteService;

@SpringBootApplication
public class StartupLanchesApplication implements CommandLineRunner
{

	@Autowired
	private IngredienteService ingredienteService;

	public static void main(String[] args)
	{
		SpringApplication.run(StartupLanchesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception
	{
		List<Ingrediente> ingredientes = Arrays.asList(new Ingrediente("Alface", new BigDecimal(0.40)),
				new Ingrediente("Bacon", new BigDecimal(2.00)),
				new Ingrediente("Hamburguer de Carne", new BigDecimal(3.00)),
				new Ingrediente("Ovo", new BigDecimal(0.80)), new Ingrediente("Queijo", new BigDecimal(1.50)));
		ingredientes.forEach(ingrediente -> ingredienteService.saveIngrediente(ingrediente));
	}
}
