package br.com.clinmed.domain.consulta.validacoes.agendamento;

import br.com.clinmed.domain.ValidacaoException;
import br.com.clinmed.domain.consulta.DadosAgendamentoConsulta;
import br.com.clinmed.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private PacienteRepository pacienteRepository;

    public void validar(DadosAgendamentoConsulta dados) {
        Boolean pacienteEstaAtivo = pacienteRepository.findAtivoById(dados.idPaciente());
        if(!pacienteEstaAtivo) {
            throw new ValidacaoException("paciente não está ativo no sistema, então não pode marcar uma consulta");
        }
    }
}
