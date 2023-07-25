package med.voll.api.consulta;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public record DadosAgendamentoConsulta(
		Long idMedico,
		
		@NotNull
		Long idPaciente,
		
		@Future
		@NotNull
		LocalDateTime data
		
		) {

}
