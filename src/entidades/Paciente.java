package entidades;
import java.time.LocalDate;
import java.util.List;

public class Paciente extends Pessoa {
public Paciente (String nome, String CPF, LocalDate dataNascimento){
	super(nome, CPF, dataNascimento);
}
public List<Consulta> getHistoricoConsultas(){
	return super.getHistoricoConsultas();
}
public List<Exame> getHistoricoExames(){
	return super.getHistoricoExames();
}
}
