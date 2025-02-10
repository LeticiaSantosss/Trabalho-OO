package entidades;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Consulta extends Medico {
private LocalDate dataConsulta;
 private LocalTime horarioConsulta;
 private int duração;
 Paciente paciente;
 Medico medico;
 private List<Exame> examesPrescritos;
 private List<String> medicamentosPrescritos;
 private double valorConsulta;
 
 Consulta(LocalDate dataConsulta, LocalTime horarioConsulta, Paciente paciente, Medico medico ){
	 this.setDataConsulta(dataConsulta);
	 this.setHorarioConsulta(horarioConsulta) ;
	 this.paciente = paciente;
	 this.medico = medico; 
 }
 public boolean verificarDisponibilidadeMedico() {
     return medico.getHistoricoConsultasAtendidas().stream()
         .noneMatch(c -> c.getDataConsulta().equals(this.dataConsulta) && c.getHorarioConsulta().equals(this.horarioConsulta));
 }
 public boolean verificarDisponibilidadePaciente() {
     return paciente.getHistoricoConsultas().stream()
         .noneMatch(c -> c.getDataConsulta().equals(this.dataConsulta));
 }
 public boolean verificarEspecialidadeRequerida(String especialidadeRequerida) {
     return medico.verificarEspecialidade(especialidadeRequerida);
 }
public void prescreverExame (Exame exame) {
	 this.examesPrescritos.add(exame);
 }
 public void prescreverMedicamento (String medicamento) {
	 this.medicamentosPrescritos.add(medicamento);
 }
public LocalTime getHorarioConsulta() {
	return horarioConsulta;
}
public void setHorarioConsulta(LocalTime horarioConsulta) {
	this.horarioConsulta = horarioConsulta;
}
public int getDuração() {
	return duração;
}
public void setDuração(int duração) {
	this.duração = duração;
}
public LocalDate getDataConsulta() {
	return dataConsulta; 
}
public void setDataConsulta(LocalDate dataConsulta) {
	this.dataConsulta = dataConsulta;
}
public double getValorConsulta() {
	return valorConsulta;
}
public void setValorConsulta(double valorConsulta) {
	this.valorConsulta = valorConsulta;
}
public Medico getMedico() {
    return this.medico;
}

public Paciente getPaciente() {
    return this.paciente;
}
}

