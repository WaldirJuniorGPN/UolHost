package br.com.WaldirJunior.UolHost.dto.response;

import br.com.WaldirJunior.UolHost.model.Grupo;
import br.com.WaldirJunior.UolHost.model.Jogador;

public record DadosListagemJogador(Long id, String codinome, Grupo grupo) {
    public DadosListagemJogador(Jogador jogador) {
        this(jogador.getId(), jogador.getCodinome(), jogador.getGrupo());
    }
}
