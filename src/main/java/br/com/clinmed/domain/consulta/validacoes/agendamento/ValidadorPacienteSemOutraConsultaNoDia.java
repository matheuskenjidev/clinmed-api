package br.com.clinmed.domain.consulta.validacoes.agendamento;

import br.com.clinmed.domain.ValidacaoException;
import br.com.clinmed.domain.consulta.ConsultaRepository;
import br.com.clinmed.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ValidadorPacienteSemOutraConsultaNoDia implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoConsulta dados) {
        LocalDateTime primeiroHorario = dados.data().withHour(7);
        LocalDateTime ultimoHorario = dados.data().withHour(18);
        boolean pacientePossuiOutraConsultaNoDia = consultaRepository.existsByPacienteIdAndDataBetween(dados.idPaciente(), primeiroHorario, ultimoHorario);

        if(pacientePossuiOutraConsultaNoDia) {
            throw new ValidacaoException("Paciente já possui consulta agendada no dia, limite 1 por dia");
        }
    }
}
