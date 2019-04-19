package br.com.startupLanches.util;

import java.util.Random;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class GeradorDePromocao
{

	private static Promocoes promocoes;

	@Scheduled(fixedRate = 86400)
	public void atualizaPromocao()
	{
		promocoes = Promocoes.values()[new Random().nextInt(Promocoes.values().length)];
	}

	public Promocoes getPromocao()
	{
		return promocoes;
	}
}
