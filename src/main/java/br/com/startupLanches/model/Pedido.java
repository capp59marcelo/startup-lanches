package br.com.startupLanches.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Pedido implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private BigDecimal preco;

	@OneToMany
	@JoinColumn(name = "pedido_id")
	private List<Lanche> lanche;

	public Pedido() { }

	public Pedido(Long id, BigDecimal preco, List<Lanche> lanche)
	{
		this.id = id;
		this.preco = preco;
		this.lanche = lanche;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public BigDecimal getPreco()
	{
		return preco;
	}

	public void setPreco(BigDecimal preco)
	{
		this.preco = preco;
	}

	public List<Lanche> getLanche()
	{
		return lanche;
	}

	public void setLanche(List<Lanche> lanche)
	{
		this.lanche = lanche;
	}
}