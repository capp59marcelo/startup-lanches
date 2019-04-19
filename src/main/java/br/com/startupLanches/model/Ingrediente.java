package br.com.startupLanches.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Ingrediente implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	private BigDecimal preco;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "lanche_ingredientes", joinColumns = @JoinColumn(name = "ingredient_id"), inverseJoinColumns = @JoinColumn(name = "lanche_id"))
	@JsonIgnore
	public List<Lanche> lanches;

	public Ingrediente() { }

	public Ingrediente(Long id, String nome, BigDecimal preco)
	{
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}

	public Ingrediente(String nome, BigDecimal preco)
	{
		this.nome = nome;
		this.preco = preco;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public BigDecimal getPreco()
	{
		return preco;
	}

	public void setPreco(BigDecimal preco)
	{
		this.preco = preco;
	}

	public List<Lanche> getLanches()
	{
		return lanches;
	}

	public void setLanches(List<Lanche> lanches)
	{
		this.lanches = lanches;
	}

	@Override
	public String toString()
	{
		return "Ingrediente{ id=" + id + ", nome='" + nome + '\'' + ", preco=" + preco + "}";
	}
}
