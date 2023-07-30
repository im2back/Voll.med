package med.voll.api.consulta.validacoes;

import med.voll.api.consulta.DadosAgendamentoConsulta;
import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.repositories.PacienteRepository;

public class ValidadorPacienteAtivo {
	
private PacienteRepository repository;
	
	@SuppressWarnings("unused")
	private void validar(DadosAgendamentoConsulta dados) {
		if (dados.idPaciente()== null) {
			return;
		}
		
		var pacienteEstaAtivo = repository.findAtivoById(dados.idPaciente());
		if(!pacienteEstaAtivo) {
			throw new ValidacaoException("Consulta não pode ser agendada com paciente intativo");
		}
		
	}
}
