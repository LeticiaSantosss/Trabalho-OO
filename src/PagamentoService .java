import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

class Sistema {
    private ArrayList<Medico> medicos;
    private ArrayList<Paciente> pacientes;
    private ArrayList<Pagamento> pagamentos;

    public Sistema() {
        medicos = new ArrayList<>();
        pacientes = new ArrayList<>();
        pagamentos = new ArrayList<>();
    }
    
    public class PagamentoService  {
    public boolean verificarPendencias(Pessoa pessoa) {
        for (Pagamento pagamento : pessoa.getPagamentos()) {
            if (pagamento.getStatusPagamento()) {
                return true;
            }
        }
        return false;
    }

    public void registrarPagamento(Pagamento pagamento) {
        pagamento.realizarPagamento();
    }
    // Create
    public void adicionarPagamento(Pagamento pagamento) {
        pagamentos.add(pagamento);
        System.out.println("Pagamento adicionado: " + pagamento);
    }
    // Read
    public Pagamento buscarPagamentoPorCpf(String cpf) {
        for (Pagamento pagamento : pagamentos) {
            if (pagamento.getCpfPaciente().equals(cpf)) {
                return pagamento;
            }
        }
        return null; // Pagamento não encontrado
    }

    public void listarPagamentos() {
        System.out.println("Lista de pagamentos:");
        for (Pagamento pagamento : pagamentos) {
            System.out.println(pagamento);
        }
    }
    // Update
    public void atualizarPagamento(String cpf, double novoValor) {
        for (Pagamento pagamento : pagamentos) {
            if (pagamento.getCpfPaciente().equals(cpf)) {
                // Para simplificação, vamos criar um novo pagamento com o novo valor e a mesma data
                Pagamento novoPagamento = new Pagamento(novoValor, pagamento.getData(), cpf);
                pagamentos.remove(pagamento);
                pagamentos.add(novoPagamento);
                System.out.println("Pagamento atualizado: " + novoPagamento);
                return;
            }
        }
        System.out.println("Pagamento não encontrado.");
    }
    // Delete
    public void removerPagamento(String cpf) {
        Pagamento pagamento = buscarPagamentoPorCpf(cpf);
        if (pagamento != null) {
            pagamentos.remove(pagamento);
            System.out.println("Pagamento removido: " + pagamento);
        } else {
            System.out.println("Pagamento não encontrado.");
        }
    }
 }
}

    

