package entidades;

import java.time.LocalDate;
import java.util.List;

public class Prescricao {
private Consulta consultaAssociada;
private List<Exame> examesPrescritos;
private List<String> medicamentos;
private LocalDate dataValidade;

public Prescricao(Consulta consultaAssociada, List<Exame> examesPrescritos, List<String>medicamentos,LocalDate dataValidade) {
	this.setConsultaAssociada(consultaAssociada);
	this.setExamesPrescritos(examesPrescritos);
	this.setMedicamentos(medicamentos);
	this.setDataValidade(dataValidade);
}

public Consulta getConsultaAssociada() {
	return consultaAssociada;
}

public void setConsultaAssociada(Consulta consultaAssociada) {
	this.consultaAssociada = consultaAssociada;
}

public List<Exame> getExamesPrescritos() {
	return examesPrescritos;
}

public void setExamesPrescritos(List<Exame> examesPrescritos) {
	this.examesPrescritos = examesPrescritos;
}

public List<String> getMedicamentos() {
	return medicamentos;
}

public void setMedicamentos(List<String> medicamentos) {
	this.medicamentos = medicamentos;
}

public LocalDate getDataValidade() {
	return dataValidade;
}

public void setDataValidade(LocalDate dataValidade) {
	this.dataValidade = dataValidade;
}
public boolean Validade() {
	return LocalDate.now().isBefore(dataValidade) || LocalDate.now().isEqual(dataValidade);
}

}
