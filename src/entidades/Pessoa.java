package entidades;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pessoa {
private String nome, 
				CPF;
LocalDate dataNascimento;
private List<Consulta>historicoConsultas;
private List<Exame>historicoExames ;
private List<Pagamento>historicoPagamentos;
Pessoa(String nome, String Cpf, LocalDate dataNascimento){
	this.nome= nome;
	this.CPF= Cpf;
	this.dataNascimento= dataNascimento;
	this.historicoConsultas = new ArrayList<>();
	 this.setHistoricoExames(new ArrayList<>());  // Inicialização de historicoExames
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
public void adicionarConsulta(Consulta consulta) {
	this.historicoConsultas.add(consulta);
}
public void adicionarExame(Exame exame) {
	this.getHistoricoExames().add(exame);
}

public void adicionarPagamento (Pagamento pagamento) {
	this.historicoPagamentos.add(pagamento);
}

public List<Pagamento>  getPagamentosPendentes(){
	List <Pagamento> pendentes = new ArrayList <>();
	for(Pagamento pagamento: historicoPagamentos) {
		if(pagamento.getStatusPagamento())  
			pendentes.add(pagamento);
		
	}return pendentes;
}

public List<Exame> getHistoricoExames() {
	return historicoExames; 
}

public void setHistoricoExames(List<Exame> historicoExames) {
	this.historicoExames = historicoExames;
}

public List<Consulta> getHistoricoConsultas() {
    return historicoConsultas;
}

public void setHistoricoConsultas(List<Consulta> historicoConsultas) {
    this.historicoConsultas = historicoConsultas;
}
public List<Pagamento> getHistoricoPagamentos() {
    return historicoPagamentos;
}

public void setHistoricoPagamentos(List<Pagamento> historicoPagamentos) {
    this.historicoPagamentos = historicoPagamentos;
}
}
