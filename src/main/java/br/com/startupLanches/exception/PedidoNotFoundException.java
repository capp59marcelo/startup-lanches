package br.com.startupLanches.exception;

public class PedidoNotFoundException extends Exception
{
	private static final long serialVersionUID = 1L;

	public PedidoNotFoundException(String message)
	{
		super(message);
	}
}
