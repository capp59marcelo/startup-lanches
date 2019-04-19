package br.com.startupLanches.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.startupLanches.model.Lanche;

@Repository
public interface LancheRepository extends JpaRepository<Lanche, Long>
{
}
