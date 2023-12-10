package br.com.WaldirJunior.UolHost.service;

import br.com.WaldirJunior.UolHost.dto.request.DadosCadastroJogador;
import br.com.WaldirJunior.UolHost.dto.response.DadosDetalhamentoJogador;
import br.com.WaldirJunior.UolHost.dto.response.DadosListagemJogador;
import br.com.WaldirJunior.UolHost.infra.CodinomeHandler;
import br.com.WaldirJunior.UolHost.model.Grupo;
import br.com.WaldirJunior.UolHost.model.Jogador;
import br.com.WaldirJunior.UolHost.repository.JogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class JogadorService {

    @Autowired
    private JogadorRepository repository;

    @Autowired
    private CodinomeHandler handler;

    public DadosDetalhamentoJogador cadastrar(DadosCadastroJogador dados) {
        var codinome = this.getCodinome(dados.grupo());
        var jogador = new Jogador(dados, codinome);
        this.repository.save(jogador);
        return new DadosDetalhamentoJogador(jogador);
    }

    public Page<DadosListagemJogador> listar(Pageable pageable) {
        var page = this.repository.findAll(pageable).map(DadosListagemJogador::new);
        return page;
    }

    private String getCodinome(Grupo grupo) {
        return handler.findCodinome(grupo);
    }
}
