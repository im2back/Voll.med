package med.voll.api.consulta.validacoes.agendamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.consulta.DadosAgendamentoConsulta;
import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.repositories.MedicoRepository;
@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsultas {

@Autowired
private MedicoRepository repository;
	
	
	public void validar(DadosAgendamentoConsulta dados) {
		if (dados.idMedico()== null) {
			return;
		}
		
		var medicoEstaAtivo = repository.findAtivoById(dados.idMedico());
		if(!medicoEstaAtivo) {
			throw new ValidacaoException("Consulta não pode ser agendada com médico intativo");
		}
		
	}
}
