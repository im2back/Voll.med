package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import med.voll.api.medico.DadosAtualizacaoMedico;
import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.medico.DadosDetalhamentoMedico;
import med.voll.api.medico.DadosListagemMedico;
import med.voll.api.service.MedicoService;

@RestController
@RequestMapping("medicos")
public class MedicoController {

	@Autowired
	private MedicoService service;

	@SuppressWarnings("rawtypes")
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) {

		var medico = service.save(dados);

		var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

		return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));

	}

	@GetMapping
	public ResponseEntity<Page<DadosListagemMedico>> listar(
			@PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
		var page = service.findAllByAtivoTrue(paginacao);
		return ResponseEntity.ok(page);
	}

	@SuppressWarnings("rawtypes")
	@PutMapping
	@Transactional
	public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
		var medico = service.getReferenceById(dados.id());
		medico.atualizarInformacoes(dados);
		return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));

	}

	@SuppressWarnings("rawtypes")
	@DeleteMapping(value = "/{id}")
	@Transactional
	public ResponseEntity excluir(@PathVariable Long id) {
		var medico = service.getReferenceById(id);
		medico.excluir();

		return ResponseEntity.noContent().build();
	}



	@SuppressWarnings("rawtypes")
	@GetMapping(value = "/{id}")
	public  ResponseEntity detalhar(@PathVariable Long id){
		
		var medico = service.getReferenceById(id);
		
		return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
			}
}