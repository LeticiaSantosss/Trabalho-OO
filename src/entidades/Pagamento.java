package entidades;

public class Pagamento {
private double valor;
private boolean statusPagamento ;


Pagamento(double valor){
	this.valor = valor;
	this.statusPagamento = true;
	
}

public double getValor() {
    return valor;
}

public boolean getStatusPagamento() {
    return statusPagamento;
}
public void realizarPagamento () {
		statusPagamento= false;
}

}
