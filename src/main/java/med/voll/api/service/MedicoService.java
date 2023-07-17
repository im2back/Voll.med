package med.voll.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voll.api.medico.Medico;
import med.voll.api.repositories.MedicoRepository;

@Service
public class MedicoService {
	
	@Autowired
	private MedicoRepository repository;

	
	public Medico insert(Medico obj) {
		return repository.save(obj);
	}

	
	
}
