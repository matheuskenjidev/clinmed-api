package br.com.clinmed.domain.consulta.validacoes.agendamento;

import br.com.clinmed.domain.consulta.DadosCancelamentoConsulta;

public interface ValidadorCancelamentoConsulta {
    void validar(DadosCancelamentoConsulta dados);
}
