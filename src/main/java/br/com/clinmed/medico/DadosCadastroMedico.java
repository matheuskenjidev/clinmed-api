package br.com.clinmed.medico;

import br.com.clinmed.endereco.DadosEndereco;

public record DadosCadastroMedico(String nome,
                                  String email,
                                  String crm,
                                  Especialidade especialidade,
                                  DadosEndereco endereco) {
}
