package br.com.WaldirJunior.UolHost.dto.request;

import br.com.WaldirJunior.UolHost.model.Grupo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroJogador(
        @NotBlank
        String nome,
        @Email
        @NotNull
        String email,
        String telefone,
        @NotNull
        Grupo grupo
) {
}
