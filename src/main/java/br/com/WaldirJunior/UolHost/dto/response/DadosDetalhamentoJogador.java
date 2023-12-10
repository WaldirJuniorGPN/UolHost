package br.com.WaldirJunior.UolHost.dto.response;

import br.com.WaldirJunior.UolHost.model.Grupo;
import br.com.WaldirJunior.UolHost.model.Jogador;

public record DadosDetalhamentoJogador(Long id, String nome, String email, String telefone, String codinome,
                                       Grupo grupo) {
    public DadosDetalhamentoJogador(Jogador jogador) {
        this(jogador.getId(), jogador.getNome(), jogador.getEmail(), jogador.getTelefone(), jogador.getCodinome(), jogador.getGrupo());
    }
}
