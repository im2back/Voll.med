package med.voll.api.consulta.validacoes;

import med.voll.api.consulta.DadosAgendamentoConsulta;
import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.repositories.ConsultaRepository;

public class ValidadorMedicoComOutraConsultaNoMesmoHorario {

	private ConsultaRepository repository;
	
	public void validar(DadosAgendamentoConsulta dados) {
		var medicoPossuiConsultaNoMesmoHorario = repository.existsByMedicoIdAndData(dados.idMedico(), dados.data());
		if(medicoPossuiConsultaNoMesmoHorario) {
			throw new ValidacaoException ("Médico já possui consulta agendada nesse horário");
		}
	}
	
	
	
}
