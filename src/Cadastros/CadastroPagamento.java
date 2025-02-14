package cadastros;

import entidades.Pagamento;
import entidades.Paciente;
import java.util.ArrayList;
import java.util.List;

public class CadastroPagamento {

    private List<Pagamento> pagamentos;

    public CadastroPagamento() {
        this.pagamentos = new ArrayList<>();
    }

    public void registrarPagamento(Paciente paciente, Pagamento pagamento) {
        paciente.adicionarPagamento(pagamento);
        pagamentos.add(pagamento);
    }

    public List<Pagamento> verificarPagamentosPendentes(Paciente paciente) {
        return paciente.getPagamentosPendentes();
    }
}