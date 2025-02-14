package entidades;

public class Pagamento {
private double valor;
private static boolean statusPagamento ;


Pagamento(double valor){
	this.valor = valor;
	Pagamento.statusPagamento = true;
	
}

public Pagamento() {
	// TODO Auto-generated constructor stub
}

public double getValor() {
    return valor;
}

public static boolean getStatusPagamento() {
    return statusPagamento;
}
public void realizarPagamento () {
		statusPagamento= false;
}

public Pessoa getPaciente() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getPaciente'");
}

}
