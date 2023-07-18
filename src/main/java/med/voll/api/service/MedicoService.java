package med.voll.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.medico.DadosListagemMedico;
import med.voll.api.medico.Medico;
import med.voll.api.repositories.MedicoRepository;

@Service
public class MedicoService {
	
	@Autowired
	private MedicoRepository repository;

	
	public Medico insert(DadosCadastroMedico dados) {
		 
		return repository.save(new Medico(dados));
	}
	
	public Page<DadosListagemMedico> findAllByAtivoTrue(Pageable paginacao) {
		return repository.
				findAllByAtivoTrue(paginacao).
				map(DadosListagemMedico::new);
	}
	
	public Medico getReferenceById(Long id) {
		return repository.getReferenceById(id);
		
	}

	public void delete(Long id) { 
		 repository.deleteById(id);
		                                      }

	
}
