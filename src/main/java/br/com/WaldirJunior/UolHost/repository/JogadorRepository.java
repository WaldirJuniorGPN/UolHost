package br.com.WaldirJunior.UolHost.repository;

import br.com.WaldirJunior.UolHost.model.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JogadorRepository extends JpaRepository<Jogador, Long> {
}
