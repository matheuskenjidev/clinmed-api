package br.com.clinmed.controller;

import br.com.clinmed.medico.DadosCadastroMedico;
import br.com.clinmed.medico.DadosListagemMedicos;
import br.com.clinmed.medico.Medico;
import br.com.clinmed.medico.MedicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {

        repository.save(new Medico(dados));

    }

    @GetMapping
    public Page<DadosListagemMedicos> listar(Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosListagemMedicos::new);
    }
}
