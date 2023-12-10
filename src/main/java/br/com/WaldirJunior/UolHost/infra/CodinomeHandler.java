package br.com.WaldirJunior.UolHost.infra;

import br.com.WaldirJunior.UolHost.model.Grupo;
import br.com.WaldirJunior.UolHost.service.CodinomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CodinomeHandler {

    @Autowired
    private CodinomeService codinomeService;

    public String findCodinome(Grupo grupo) {
        if(grupo == Grupo.VINGADORES){

            var firstMatch = codinomeService.getListaCodinomeVingadores().stream().findFirst().orElseThrow();
            this.codinomeService.getListaCodinomeVingadores().remove(firstMatch);
            return firstMatch;

        } else if (grupo == Grupo.LIGA_DA_JUSTICA) {

            var firstMatch = codinomeService.getListaCodinomeLigaDaJustica().stream().findFirst().orElseThrow();
            this.codinomeService.getListaCodinomeLigaDaJustica().remove(firstMatch);
            return firstMatch;

        }
        throw new IllegalArgumentException("Grupo inválido.");
    }
}
