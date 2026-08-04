package br.com.clinmed.paciente;

import br.com.clinmed.endereco.DadosEndereco;
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
