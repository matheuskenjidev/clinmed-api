package br.com.clinmed.domain.consulta.validacoes.cancelamento;

import br.com.clinmed.domain.ValidacaoException;
import br.com.clinmed.domain.consulta.Consulta;
import br.com.clinmed.domain.consulta.ConsultaRepository;
import br.com.clinmed.domain.consulta.DadosCancelamentoConsulta;
import br.com.clinmed.domain.consulta.validacoes.agendamento.ValidadorCancelamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidadorHorarioAntecedenciaCancelamento")
public class ValidarHorarioAntecedencia implements ValidadorCancelamentoConsulta {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public void validar(DadosCancelamentoConsulta dados) {
        Consulta consulta = consultaRepository.getReferenceById(dados.idConsulta());
        LocalDateTime horarioAtual = LocalDateTime.now();
        Long diferencaEmHoras = Duration.between(horarioAtual, consulta.getData()).toHours();

        if(diferencaEmHoras < 24) {
            throw new ValidacaoException("Consulta somente pode ser cancelada com antecedência mínima de 24h!");
        }
    }
}
