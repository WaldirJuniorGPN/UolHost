package br.com.WaldirJunior.UolHost.model;

import br.com.WaldirJunior.UolHost.dto.request.DadosCadastroJogador;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Jogador")
@Table(name = "jogadores")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String codinome;

    @Enumerated(EnumType.STRING)
    private Grupo grupo;

    public Jogador(DadosCadastroJogador dados, String codinome) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.grupo = dados.grupo();
        this.codinome = codinome;
    }
}
