package br.com.startupLanches.util;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.startupLanches.model.Ingrediente;
import br.com.startupLanches.service.IngredienteService;

@RunWith(SpringRunner.class)
@EnableJpaRepositories("br.com.startupLanches.*")
@ComponentScan(basePackages = { "br.com.startupLanches.*" })
@EntityScan("br.com.startupLanches.*")
@AutoConfigureDataJpa
public class CalculaValorTotalTest
{

	@Autowired
	private CalculaValorTotal calculaValorTotal;

	@Autowired
	private IngredienteService ingredienteService;

	private static boolean setUpIsDone = false;

	@Before
	public void init()
	{
		if (setUpIsDone == false)
		{
			List<Ingrediente> ingredientes = Arrays.asList(new Ingrediente("Alface", new BigDecimal(0.40)),
					new Ingrediente("Hamburger de Carne", new BigDecimal(3.00)),
					new Ingrediente("Ovo", new BigDecimal(0.80)), new Ingrediente("Queijo", new BigDecimal(1.50)),
					new Ingrediente("Bacon", new BigDecimal(2.00)));
			ingredientes.forEach(ingrediente -> ingredienteService.saveIngrediente(ingrediente));
		}
		setUpIsDone = true;
	}

	@Test
	public void testaCalculaValorIngredientesLightPositive()
	{
		List<Ingrediente> ingredientes = Arrays.asList(new Ingrediente("Alface", new BigDecimal(0.40)),
				new Ingrediente("Hamburger de Carne", new BigDecimal(3.00)),
				new Ingrediente("Ovo", new BigDecimal(0.80)), new Ingrediente("Queijo", new BigDecimal(1.50)));

		BigDecimal valorTotalLight = calculaValorTotal.calculaValorIngredientes(calculaValorTotal.carregaIngredientes(ingredientes), "Light");
		BigDecimal valorEsperado = new BigDecimal(5.13);
		
		valorEsperado = valorEsperado.setScale(2, RoundingMode.HALF_UP);
		assertEquals(valorEsperado, valorTotalLight);
	}

	@Test
	public void testaCalculaValorIngredientesLightNegative()
	{
		List<Ingrediente> ingredientes = Arrays.asList(new Ingrediente("Alface", new BigDecimal(0.40)),
				new Ingrediente("Hamburger de Carne", new BigDecimal(3.00)),
				new Ingrediente("Ovo", new BigDecimal(0.80)), new Ingrediente("Queijo", new BigDecimal(1.50)));

		BigDecimal valorTotalLight = calculaValorTotal.calculaValorIngredientes(calculaValorTotal.carregaIngredientes(ingredientes), "Queijo");
		BigDecimal valorEsperado = new BigDecimal(5.70);
		
		valorEsperado = valorEsperado.setScale(2, RoundingMode.HALF_UP);
		assertEquals(valorEsperado, valorTotalLight);
	}

	@Test
	public void testaCalculaValorIngredientesMuitaCarnePositive()
	{
		BigDecimal valorTotalLight = calculaValorTotal.calculaValorIngredientes(calculaValorTotal
				.carregaIngredientes(Arrays.asList(new Ingrediente("Hamburger de Carne", new BigDecimal(3.00)),
						new Ingrediente("Hamburger de Carne", new BigDecimal(3.00)),
						new Ingrediente("Hamburger de Carne", new BigDecimal(3.00)),
						new Ingrediente("Queijo", new BigDecimal(1.50)))), "Hamburger de Carne");
		
		BigDecimal valorEsperado = new BigDecimal(7.50);
		valorEsperado = valorEsperado.setScale(2, RoundingMode.HALF_UP);
		assertEquals(valorEsperado, valorTotalLight);
	}

	@Test
	public void testaCalculaValorIngredientesMuitaCarneNegative()
	{
		BigDecimal valorTotalLight = calculaValorTotal.calculaValorIngredientes(calculaValorTotal
				.carregaIngredientes(Arrays.asList(new Ingrediente("Hamburger de Carne", new BigDecimal(3.00)),
						new Ingrediente("Hamburger de Carne", new BigDecimal(3.00)),
						new Ingrediente("Hamburger de Carne", new BigDecimal(3.00)),
						new Ingrediente("Queijo", new BigDecimal(1.50)))), "Queijo");
		
		BigDecimal valorEsperado = new BigDecimal(10.50);
		valorEsperado = valorEsperado.setScale(2, RoundingMode.HALF_UP);
		assertEquals(valorEsperado, valorTotalLight);
	}

	@Test
	public void testaCalculaValorIngredientesMuitoQueijoPositive()
	{

		BigDecimal valorTotalLight = calculaValorTotal.calculaValorIngredientes(calculaValorTotal
				.carregaIngredientes(Arrays.asList(new Ingrediente("Hamburger de Carne", new BigDecimal(3.00)),
						new Ingrediente("Queijo", new BigDecimal(1.50)),
						new Ingrediente("Queijo", new BigDecimal(1.50)),
						new Ingrediente("Queijo", new BigDecimal(1.50)))), "Queijo");
		
		BigDecimal valorEsperado = new BigDecimal(6.00);
		valorEsperado = valorEsperado.setScale(2, RoundingMode.HALF_UP);
		assertEquals(valorEsperado, valorTotalLight);
	}

	@Test
	public void testaCalculaValorIngredientesMuitoQueijoNegative()
	{
		BigDecimal valorTotalLight = calculaValorTotal.calculaValorIngredientes(calculaValorTotal
				.carregaIngredientes(Arrays.asList(new Ingrediente("Hamburger de Carne", new BigDecimal(3.00)),
						new Ingrediente("Queijo", new BigDecimal(1.50)),
						new Ingrediente("Queijo", new BigDecimal(1.50)),
						new Ingrediente("Queijo", new BigDecimal(1.50)))), "Light");
		BigDecimal valorEsperado = new BigDecimal(7.50);
		valorEsperado = valorEsperado.setScale(2, RoundingMode.HALF_UP);
		assertEquals(valorEsperado, valorTotalLight);
	}
}