package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.paciente.DadosAtualizacaoPaciente;
import med.voll.api.paciente.DadosCadastroPaciente;
import med.voll.api.paciente.DadosListagemPaciente;
import med.voll.api.service.PacienteService;

@RestController
@RequestMapping("pacientes")
public class PacienteController {
	
	@Autowired
    private PacienteService service;
	
	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody @Valid DadosCadastroPaciente dados) {
		service.insert(dados);
	}
	
	@GetMapping
	public Page<DadosListagemPaciente> listar(@PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
		return service.findAllByAtivoTrue(paginacao);
	}

	@PutMapping
	@Transactional
	public void atualizar(@RequestBody @Valid DadosAtualizacaoPaciente dados) {
		var paciente = service.getReferenceById(dados.id());
		
		paciente.atualizarInformacoes(dados);
		
	}
<<<<<<< HEAD
	
	@DeleteMapping(value = "/{id}")
	@Transactional
	public  void excluir(@PathVariable Long id){
		var paciente = service.getReferenceById(id);
		paciente.excluir();
	
	}
	
	
=======
>>>>>>> f9b8145151f4eb4b2cec62b49c6d6e1db262fdf2
	
}
