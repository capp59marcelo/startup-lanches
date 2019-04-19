package br.com.startupLanches.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.startupLanches.util.Promocoes;

@Entity
public class Lanche implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;

	public String nomeLanche;
	public BigDecimal valor;

	@ManyToOne
	@JoinColumn(name = "pedido_id")
	@JsonIgnore
	public Pedido pedido;

	@Enumerated(EnumType.STRING)
	public Promocoes promocoes;

	@ManyToMany(mappedBy = "lanches", fetch = FetchType.EAGER)
	public List<Ingrediente> ingredientes;

	public Lanche() { }

	public Lanche(Long id, String nomeLanche, Promocoes promocoes, List<Ingrediente> ingredientes)
	{
		this.id = id;
		this.nomeLanche = nomeLanche;
		this.promocoes = promocoes;
		this.ingredientes = ingredientes;
	}

	public Lanche(String nomeLanche, List<Ingrediente> ingredientes)
	{
		this.nomeLanche = nomeLanche;
		this.ingredientes = ingredientes;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getNomeLanche()
	{
		return nomeLanche;
	}

	public void setNomeLanche(String nomeLanche)
	{
		this.nomeLanche = nomeLanche;
	}

	public List<Ingrediente> getIngredientes()
	{
		return ingredientes;
	}

	public void setIngredientes(List<Ingrediente> ingredientes)
	{
		this.ingredientes = ingredientes;
	}

	public Pedido getPedido()
	{
		return pedido;
	}

	public void setPedido(Pedido pedido)
	{
		this.pedido = pedido;
	}

	public Promocoes getPromocoes()
	{
		return promocoes;
	}

	public void setPromocoes(Promocoes promocoes)
	{
		this.promocoes = promocoes;
	}

	public BigDecimal getValor()
	{
		return valor;
	}

	public void setValor(BigDecimal valor)
	{
		this.valor = valor;
	}

	@Override
	public String toString()
	{
		return "Lanche{" + "id=" + id + ", nomeLanche='" + nomeLanche + '\'' + ", pedido=" + pedido + ", ingredientes="+ ingredientes + '}';
	}
}