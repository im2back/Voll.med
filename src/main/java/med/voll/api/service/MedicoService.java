package med.voll.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.medico.DadosListagemMedico;
import med.voll.api.medico.Medico;
import med.voll.api.repositories.MedicoRepository;

@Service
public class MedicoService {

	@Autowired
	private MedicoRepository repository;

	public Medico save(DadosCadastroMedico dados) {

		Medico medico = new Medico(dados);
		crmJaExiste(medico);
		emailJaExiste(medico);
		return repository.save(medico);

	}

	private boolean crmJaExiste(Medico medico) {
		var result = repository.findByCrm(medico.getCrm());
		String crm;
		for (Medico x : result) {
			crm = x.getCrm();
			if (crm != null) {
				throw new ResponseStatusException(HttpStatus.CONFLICT, "CRM já cadastrado.");

			}

		}

		return true;

	}

	private boolean emailJaExiste(Medico medico) {
		var result = repository.findByEmail(medico.getEmail());
		String email;
		for (Medico x : result) {
			email = x.getEmail();
			if (email != null) {
				throw new ResponseStatusException(HttpStatus.CONFLICT, "Email já cadastrado.");

			}

		}

		return true;

	}

	public Page<DadosListagemMedico> findAllByAtivoTrue(Pageable paginacao) {
		return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
	}

	public Medico getReferenceById(Long id) {
		return repository.getReferenceById(id);

	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

}
