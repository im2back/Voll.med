package med.voll.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import med.voll.api.model.paciente.DadosCadastroPaciente;
import med.voll.api.model.paciente.DadosListagemPaciente;
import med.voll.api.model.paciente.Paciente;
import med.voll.api.repositories.PacienteRepository;

@Service
public class PacienteService {
	
	@Autowired
	private PacienteRepository repository;
	
	public Paciente insert(DadosCadastroPaciente dados) {
		 
		return repository.save(new Paciente(dados));
	}
	
	
	public Page<DadosListagemPaciente> findAllByAtivoTrue(Pageable paginacao) {
		return repository.
				findAllByAtivoTrue(paginacao).
				map(DadosListagemPaciente::new);
	}

	public Paciente getReferenceById(Long id) {
		return repository.getReferenceById(id);
		
	}
	
	
}


