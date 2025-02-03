package entidades;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Medico extends Pessoa {
	String CRM,
	especialidade;
	private List<Consulta> consultasAtendidas;
	Medico(String nome, String Cpf, LocalDate dataNascimento, String CRM, String especialidade) {
		super(nome, Cpf, dataNascimento);
		this.CRM= CRM;
		this.especialidade = especialidade;
		this.consultasAtendidas= new ArrayList<>();
		}
public boolean verificarEspecialidade (String especialidadeRequerida)
{	return this.especialidade.equalsIgnoreCase(especialidadeRequerida);
	}
public List <Consulta>getHistoricoConsultasAtendidas() {
	return consultasAtendidas;
}
public void adicionarConsultasAtendidadas(Consulta consulta) {
	this.consultasAtendidas.add(consulta);
}
//Getters e Setters
public String getCRM() {
    return CRM;
}

public void setCRM(String CRM) {
    this.CRM = CRM;
}

public String getEspecialidade() {
    return especialidade;
}

public void setEspecialidade(String especialidade) {
    this.especialidade = especialidade;
}
}
