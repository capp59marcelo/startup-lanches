package br.com.startupLanches.exception;

public class PedidoIncompletoException extends Exception
{
	private static final long serialVersionUID = 1L;

	public PedidoIncompletoException(String message)
	{
		super(message);
	}
}