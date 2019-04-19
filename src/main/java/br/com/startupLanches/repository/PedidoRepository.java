package br.com.startupLanches.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.startupLanches.model.Pedido;

@Repository
public interface PedidoRepository extends CrudRepository<Pedido, Long>
{
}
