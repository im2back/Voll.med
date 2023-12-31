package med.voll.api.consulta;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.model.medico.Medico;
import med.voll.api.model.paciente.Paciente;

@Table(name = "consultas")
@Entity(name = "Consulta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JoinColumn(name = "medico_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Medico medico;
	
	@JoinColumn(name = "paciente_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Paciente paciente;
	
	private LocalDateTime data;
	
	private MotivoCancelamento motivoCancelamento;
	
	
	
	public void cancelar(MotivoCancelamento motivo) {
        this.motivoCancelamento = motivo;
    }



	
	
}
