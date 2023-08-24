package med.voll.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voll.api.consulta.Consulta;
import med.voll.api.consulta.DadosAgendamentoConsulta;
import med.voll.api.consulta.DadosCancelamentoConsulta;
import med.voll.api.consulta.DadosDetalhamentoConsulta;
import med.voll.api.consulta.validacoes.agendamento.ValidadorAgendamentoDeConsultas;
import med.voll.api.consulta.validacoes.cancelamento.ValidadorCancelamentoConsulta;
import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.model.medico.Medico;
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
    
    @Autowired
    private List<ValidadorCancelamentoConsulta> validadoresCancelamento;
    
	public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados) {
		
		
		
		if(!pacienteRepository.existsById(dados.idPaciente())) {
			throw new ValidacaoException("Id do paciente informado não existe");
		}
		if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
			throw new ValidacaoException("Id do medico informado não existe");
		}
		
		validadores.forEach(v -> v.validar(dados));
		
		 	var paciente = pacienteRepository.getReferenceById(dados.idPaciente());		 
	        var medico = escolherMedico(dados);
	        if (medico == null) {
	            throw new ValidacaoException("Não existe médico disponível nessa data!");
	        }
	        var consulta = new Consulta(null, medico, paciente, dados.data(), null);
	        repository.save(consulta);
	        
	        return  new DadosDetalhamentoConsulta(consulta);
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
	
	public void cancelarConsulta(DadosCancelamentoConsulta dados) {
        if (!repository.existsById(dados.idConsulta())) {
            throw new ValidacaoException("Id da consulta informado não existe!");
        }
        validadoresCancelamento.forEach(v -> v.validar(dados));

        var consulta = repository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivo());
	}
	
	
	
	

}
