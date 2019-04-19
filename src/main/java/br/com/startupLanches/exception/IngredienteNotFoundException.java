package br.com.startupLanches.exception;

public class IngredienteNotFoundException extends Exception
{
	private static final long serialVersionUID = 1L;

	public IngredienteNotFoundException(String message)
	{
		super(message);
	}
}
