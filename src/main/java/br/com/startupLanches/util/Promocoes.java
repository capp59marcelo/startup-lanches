package br.com.startupLanches.util;

public enum Promocoes
{

	LIGHT("Se o lanche tem alface e não tem bacon, ganha 10% de desconto."),
	MUITA_CARNE("A cada 3 porções de carne o cliente só paga 2"),
	MUITO_QUEIJO("A cada 3 porções de queijo o cliente só paga 2");

	public String mensagem;

	Promocoes(String mensagem)
	{
		this.mensagem = mensagem;
	}

	public String getMensagem()
	{
		return mensagem;
	}
}
