package br.com.startupLanches.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.startupLanches.model.Ingrediente;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, Long>
{
	Ingrediente findByNomeIgnoreCase(String nome);
}
