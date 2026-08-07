package br.com.clinmed.domain.consulta.validacoes.agendamento;

import br.com.clinmed.domain.ValidacaoException;
import br.com.clinmed.domain.consulta.DadosAgendamentoConsulta;
import br.com.clinmed.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(DadosAgendamentoConsulta dados) {
        if(dados.idMedico() == null) {
            return;
        }

        Boolean medicoEstaAtivo = medicoRepository.findAtivoById(dados.idMedico());
        if(!medicoEstaAtivo) {
            throw new ValidacaoException("consulta não pode ser agendada, pois o médico não está ativo no sistema no momento");
        }
    }
}
