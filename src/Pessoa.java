import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pessoa {
private String nome, 
				CPF;
LocalDate dataNascimento;
private List<Consulta>historicoConsultas;
private List<Exame>historicoExames ;
private List<Pagamentos>historicoPagamentos;
Pessoa(String nome, String Cpf, LocalDate dataNascimento){
	this.nome= nome;
	this.CPF= Cpf;
	this.dataNascimento= dataNascimento;
	this.historicoConsultas = new ArrayList<>();
	 this.historicoExames = new ArrayList<>();  // Inicialização de historicoExames
     this.historicoPagamentos = new ArrayList<>(); 
}

public String getNome() {
    return nome;
}

public void setNome(String nome) {
    this.nome = nome;
}

public String getCPF() {
    return CPF;
}

public void setCPF(String CPF) {
    this.CPF = CPF;
}
public LocalDate getDataNascimento() {
    return dataNascimento;
}
}
