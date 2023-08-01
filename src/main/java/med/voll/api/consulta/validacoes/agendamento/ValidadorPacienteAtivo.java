package med.voll.api.consulta.validacoes.agendamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.consulta.DadosAgendamentoConsulta;
import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.repositories.PacienteRepository;
@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsultas {
@Autowired	
private PacienteRepository repository;
	
	public void validar(DadosAgendamentoConsulta dados) {
		if (dados.idPaciente()== null) {
			return;
		}
		
		var pacienteEstaAtivo = repository.findAtivoById(dados.idPaciente());
		if(!pacienteEstaAtivo) {
			throw new ValidacaoException("Consulta não pode ser agendada com paciente inativo");
		}
		
	}
}
