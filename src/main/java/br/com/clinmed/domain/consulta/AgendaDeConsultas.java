package br.com.clinmed.domain.consulta;

import br.com.clinmed.domain.ValidacaoException;
import br.com.clinmed.domain.consulta.validacoes.agendamento.ValidadorAgendamentoDeConsulta;
import br.com.clinmed.domain.consulta.validacoes.agendamento.ValidadorCancelamentoConsulta;
import br.com.clinmed.domain.medico.Medico;
import br.com.clinmed.domain.medico.MedicoRepository;
import br.com.clinmed.domain.paciente.Paciente;
import br.com.clinmed.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    List<ValidadorAgendamentoDeConsulta> validadores;

    @Autowired
    List<ValidadorCancelamentoConsulta> validadoresCancelamento;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados) {

        if(!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoException("id do paciente informado, não existe");
        }

        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("id do médico informado, não existe");
        }

        //validacoes
        validadores.forEach(v -> v.validar(dados));

        Paciente paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        Medico medico = escolherMedicoAleatoriamente(dados);

        if(medico == null) {
            throw new ValidacaoException("não existe médico disponível nessa data");
        }
        Consulta consulta = new Consulta(null, medico, paciente, dados.data(), null);
        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);
    }

    public void cancelar(DadosCancelamentoConsulta dados) {
        if(!consultaRepository.existsById(dados.idConsulta())){
            throw new ValidacaoException("Id da consulta informado não existe");
        }

        validadoresCancelamento.forEach(v -> v.validar(dados));

        Consulta consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivoCancelamento());
    }

    private Medico escolherMedicoAleatoriamente(DadosAgendamentoConsulta dados) {
        if(dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if(dados.especialidade() == null) {
            throw new ValidacaoException("é obrigatório selecionar a especialidade do médico quando não é escolhido um!!!!");
        }


        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }
}
