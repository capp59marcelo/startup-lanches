package br.com.startupLanches.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler
{

	@ExceptionHandler(PedidoNotFoundException.class)
	public ResponseEntity<GenericError> pedidoNotFound(PedidoNotFoundException exception, HttpServletRequest request)
	{
		GenericError error = new GenericError(HttpStatus.NOT_FOUND.value(), "Pedido não encontrado", exception.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(PedidoIncompletoException.class)
	public ResponseEntity<GenericError> pedidoIncompleto(PedidoNotFoundException exception, HttpServletRequest request)
	{
		GenericError error = new GenericError(HttpStatus.BAD_REQUEST.value(), "Pedido está faltando informações", exception.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(IngredienteNotFoundException.class)
	public ResponseEntity<GenericError> ingredienteNotFound(PedidoNotFoundException exception, HttpServletRequest request)
	{
		GenericError error = new GenericError(HttpStatus.NOT_FOUND.value(), "Erro ao processar o ingrediente", exception.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
}
