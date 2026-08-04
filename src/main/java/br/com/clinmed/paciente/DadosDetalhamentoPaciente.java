package br.com.clinmed.paciente;

import br.com.clinmed.endereco.Endereco;

public record DadosDetalhamentoPaciente(
        String nome,
        String email,
        String telefone,
        String cpf,
        Endereco endereco
) {
        public DadosDetalhamentoPaciente(Paciente paciente) {
            this(paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco());
        }
}
