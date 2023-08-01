package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.consulta.DadosAgendamentoConsulta;
import med.voll.api.consulta.DadosCancelamentoConsulta;
import med.voll.api.service.ConsultaService;

@RestController
@RequestMapping("consultas")
public class ConsultaController {
	
	@Autowired
	private ConsultaService service;

	@SuppressWarnings("rawtypes")
	@PostMapping
	@Transactional
	public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
		var dto = service.agendar(dados);
		
		return ResponseEntity.ok(dto);
		
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping
	@Transactional
	public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados) {
		
		service.cancelarConsulta(dados);
		
		return ResponseEntity.noContent().build();
	}
	
	
}
