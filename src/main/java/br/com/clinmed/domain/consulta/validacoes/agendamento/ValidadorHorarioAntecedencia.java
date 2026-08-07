package br.com.clinmed.domain.consulta.validacoes.agendamento;

import br.com.clinmed.domain.ValidacaoException;
import br.com.clinmed.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidadorHorarioAntecedenciaAgendamento")
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoDeConsulta {

    public void validar(DadosAgendamentoConsulta dados) {
        LocalDateTime dataConsulta = dados.data();
        LocalDateTime dataAgora = LocalDateTime.now();
        long diferencaEmMinutos = Duration.between(dataAgora, dataConsulta).toMinutes();

        if(diferencaEmMinutos < 30) {
            throw new ValidacaoException("Consulta deve ser agendada com meia hora de antecedencia");
        }
    }
}
