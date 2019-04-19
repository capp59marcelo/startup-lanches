package br.com.startupLanches.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.startupLanches.exception.PedidoIncompletoException;
import br.com.startupLanches.exception.PedidoNotFoundException;
import br.com.startupLanches.model.Pedido;
import br.com.startupLanches.service.PedidoService;

@CrossOrigin
@RestController
@RequestMapping(value="/pedidos")
public class PedidoController
{

	@Autowired
	private PedidoService pedidoService;

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Pedido>> getPedidos()
	{
		return ResponseEntity.ok(pedidoService.getPedidos());
	}

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Pedido> savePedido(@RequestBody Pedido pedido)
	{
		return ResponseEntity.ok(pedidoService.savePedido(pedido));
	}

	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<Pedido> updatePedido(@RequestBody Pedido pedido)
			throws PedidoNotFoundException, PedidoIncompletoException
	{
		return ResponseEntity.ok(pedidoService.updatePedido(pedido));
	}
}
