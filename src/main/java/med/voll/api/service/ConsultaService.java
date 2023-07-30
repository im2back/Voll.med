package med.voll.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voll.api.consulta.Consulta;
import med.voll.api.consulta.DadosAgendamentoConsulta;
import med.voll.api.consulta.validacoes.ValidadorAgendamentoDeConsultas;
import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.medico.Medico;
import med.voll.api.repositories.ConsultaRepository;
import med.voll.api.repositories.MedicoRepository;
import med.voll.api.repositories.PacienteRepository;

@Service
public class ConsultaService {
	
	@Autowired
	private ConsultaRepository repository;
	
    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;
	
    @Autowired
    private List<ValidadorAgendamentoDeConsultas> validadores;
    
	public void agendar(DadosAgendamentoConsulta dados) {
		
		validadores.forEach(v -> v.validar(dados));
		
		if(!pacienteRepository.existsById(dados.idPaciente())) {
			throw new ValidacaoException("Id do paciente informado não existe");
		}
		if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
			throw new ValidacaoException("Id do medico informado não existe");
		}
		
		 	var paciente = pacienteRepository.getReferenceById(dados.idPaciente());		 
	        var medico = escolherMedico(dados);
	        var consulta = new Consulta(null, medico, paciente, dados.data());
	        repository.save(consulta);
	}
	
	
	
	
	private Medico escolherMedico(DadosAgendamentoConsulta dados) {
	    if (dados.idMedico() != null) {
	        return medicoRepository.getReferenceById(dados.idMedico());
	    }
	    if (dados.especialidade() == null) {
	        throw new ValidacaoException("Especialidade é obrigatória quando médico não for escolhido!");
	    }
	    
	    return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(),dados.data());
	}
	
	
	
	
	
	

}
