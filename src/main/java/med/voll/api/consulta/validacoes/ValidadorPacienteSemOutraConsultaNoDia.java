package med.voll.api.consulta.validacoes;

import med.voll.api.consulta.DadosAgendamentoConsulta;
import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.repositories.ConsultaRepository;

public class ValidadorPacienteSemOutraConsultaNoDia {
		
	private ConsultaRepository repository;
	
	public void validar(DadosAgendamentoConsulta dados) {
		var primeiroHorario = dados.data().withHour(7);
		var ultimoHorario = dados.data().withHour(18);
		var pacientePossuiOutraConsultaNoDia  = repository.existByPacienteIdAndDataBetween(dados.idPaciente(),primeiroHorario, ultimoHorario);
			if(pacientePossuiOutraConsultaNoDia) {
				throw new ValidacaoException("Paciente já possui uma consulta agendada nesse dia");
			}
		
	}
		
		
	
}
