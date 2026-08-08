package br.com.clinmed.controller;

import br.com.clinmed.domain.consulta.AgendaDeConsultas;
import br.com.clinmed.domain.consulta.DadosAgendamentoConsulta;
import br.com.clinmed.domain.consulta.DadosCancelamentoConsulta;
import br.com.clinmed.domain.consulta.DadosDetalhamentoConsulta;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultas agenda;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {

        DadosDetalhamentoConsulta dadosDetalhamentoConsulta = agenda.agendar(dados);
        return ResponseEntity.ok(dadosDetalhamentoConsulta);
    }

    @PutMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados){
        agenda.cancelar(dados);
        return ResponseEntity.ok("Consulta cancelada com sucesso!!!");
    }
}
