package br.com.WaldirJunior.UolHost.controller;

import br.com.WaldirJunior.UolHost.dto.request.DadosCadastroJogador;
import br.com.WaldirJunior.UolHost.dto.response.DadosDetalhamentoJogador;
import br.com.WaldirJunior.UolHost.dto.response.DadosListagemJogador;
import br.com.WaldirJunior.UolHost.service.JogadorService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/jogador")
public class JogadorController {

    @Autowired
    private JogadorService jogadorService;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoJogador> cadastrar(@RequestBody @Valid DadosCadastroJogador dados, UriComponentsBuilder uriComponentsBuilder) {
        var jogadorDto = this.jogadorService.cadastrar(dados);
        var uri = uriComponentsBuilder.path("/jogador/{id}").buildAndExpand(jogadorDto.id()).toUri();
        return ResponseEntity.created(uri).body(jogadorDto);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemJogador>> listar(Pageable pageable) {
        var page = this.jogadorService.listar(pageable);
        return ResponseEntity.ok(page);
    }
}
