package med.voll.api.consulta.validacoes;

import med.voll.api.consulta.DadosAgendamentoConsulta;
import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.repositories.MedicoRepository;

public class ValidadorMedicoAtivo {
	
private MedicoRepository repository;
	
	@SuppressWarnings("unused")
	private void validar(DadosAgendamentoConsulta dados) {
		if (dados.idMedico()== null) {
			return;
		}
		
		var medicoEstaAtivo = repository.findAtivoById(dados.idMedico());
		if(!medicoEstaAtivo) {
			throw new ValidacaoException("Consulta não pode ser agendada com médico intativo");
		}
		
	}
}
