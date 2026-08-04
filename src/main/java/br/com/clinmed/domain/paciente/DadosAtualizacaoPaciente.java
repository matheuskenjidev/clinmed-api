package br.com.clinmed.domain.paciente;

import br.com.clinmed.domain.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPaciente(

        @NotNull
        Long id,
        String nome,
        String telefone,
        @Valid
        DadosEndereco endereco

) {
}
