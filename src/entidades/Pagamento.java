package entidades;

public class Pagamento {
    private double valor;
    private boolean statusPagamento; 
    Pessoa pessoa;
    
    public Pagamento(double valor) {
        this.valor = valor;
        this.statusPagamento = false; 
       
    }
    public Paciente getPaciente() {
        if (this.pessoa instanceof Paciente) { // se a pessoa é um paciente
            return (Paciente) this.pessoa;    // cast para Paciente
        }
        return null;
    }
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
   
    public Pagamento() {
        this.statusPagamento = false; 
    }

    
    public double getValor() {
        return valor;
    }

    public boolean getStatusPagamento() {
        return statusPagamento;
    }

    
    public void realizarPagamento() {
        this.statusPagamento = true;
    }
}
