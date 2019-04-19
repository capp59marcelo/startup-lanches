package br.com.startupLanches.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.startupLanches.exception.PedidoIncompletoException;
import br.com.startupLanches.exception.PedidoNotFoundException;
import br.com.startupLanches.model.Ingrediente;
import br.com.startupLanches.model.Lanche;
import br.com.startupLanches.model.Pedido;
import br.com.startupLanches.repository.PedidoRepository;
import br.com.startupLanches.util.CalculaValorTotal;
import br.com.startupLanches.util.GeradorDePromocao;

@Service
public class PedidoService
{

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private LancheService lancheService;

	@Autowired
	private IngredienteService ingredienteService;

	@Autowired
	private CalculaValorTotal calculaValor;

	@Autowired
	private GeradorDePromocao geradorDePromocao;

	public Pedido savePedido(Pedido pedidoToSave)
	{
		List<Lanche> lanches = pedidoToSave.getLanche();
		lanches.forEach(lanche -> {
			lanche.setPromocoes(geradorDePromocao.getPromocao());
			List<Ingrediente> ingredientes = lanche.getIngredientes();
			ingredientes.forEach(ingrediente -> {
				ingrediente.setLanches(lanches);
				ingredienteService.saveIngrediente(ingrediente);
			});
			lancheService.saveLanche(lanche);
		});
		pedidoToSave.setPreco(calculaValor.calculaValorTotal(pedidoToSave.getLanche()));
		return pedidoRepository.save(pedidoToSave);
	}

	public Pedido getPedido(Long id) throws PedidoNotFoundException
	{
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		if (pedido == null)
		{
			throw new PedidoNotFoundException("Pedido nao foi encontrado");
		}
		return pedido.get();
	}

	public List<Pedido> getPedidos()
	{
		Iterable<Pedido> pedidos = pedidoRepository.findAll();
		List<Pedido> pedidosAchados = new ArrayList<>();
		pedidos.forEach(pedido -> pedidosAchados.add(pedido));
		return pedidosAchados;
	}

	public Pedido updatePedido(Pedido pedido) throws PedidoNotFoundException, PedidoIncompletoException
	{
		if (pedido.getId() == null)
		{
			throw new PedidoIncompletoException("Id do pedido foi enviado com valor null");
		}
		Pedido pedidoUpdate = getPedido(pedido.getId());
		Pedido pedidoSalvo = pedidoRepository.save(pedidoUpdate);
		return pedidoSalvo;
	}

	public Pedido updatePedidoAntigo(Pedido pedidoNovo, Pedido pedidoAntigo)
	{
		pedidoAntigo.setPreco(calculaValor.calculaValorTotal(pedidoAntigo.getLanche()));
		return pedidoAntigo;
	}
}