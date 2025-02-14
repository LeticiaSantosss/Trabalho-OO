package servicos;

import entidades.*;
import excecoes.*;
import java.util.ArrayList;
import java.util.List;

public class PagamentoService {
    private List<Pagamento> pagamentos;

    public PagamentoService() {
        this.pagamentos = new ArrayList<>();
    }

    // Registrar um novo pagamento
    public void registrarPagamento(Paciente paciente, double valor) {
        Pagamento pagamento = new Pagamento(valor);
        pagamento.realizarPagamento();
        paciente.adicionarPagamento(pagamento);
        pagamentos.add(pagamento);
        Notificacao.notificarSucesso("Pagamento registrado com sucesso.");
    }

    // Verificar pagamentos pendentes de um paciente
    public List<Pagamento> verificarPagamentosPendentes(Paciente paciente) {
        return paciente.getPagamentosPendentes();
    }

    // Buscar pagamento por CPF do paciente
    public Pagamento buscarPagamentoPorCpf(String cpf) throws PagamentoNaoEncontradoException {
        for (Pagamento pagamento : pagamentos) {
            if (pagamento.getPaciente().getCPF().equals(cpf)) {
                return pagamento;
            }
        }
        throw new PagamentoNaoEncontradoException("Pagamento não encontrado para o CPF informado.");
    }

    // Listar todos os pagamentos
    public List<Pagamento> listarPagamentos() {
        return new ArrayList<>(pagamentos);
    }

    // Atualizar um pagamento
    public void atualizarPagamento(Pagamento pagamentoAntigo, Pagamento pagamentoNovo) throws PagamentoNaoEncontradoException {
        if (pagamentos.contains(pagamentoAntigo)) {
            pagamentos.remove(pagamentoAntigo);
            pagamentos.add(pagamentoNovo);
            Notificacao.notificarSucesso("Pagamento atualizado com sucesso.");
        } else {
            throw new PagamentoNaoEncontradoException("Pagamento antigo não encontrado.");
        }
    }

    // Remover um pagamento
    public void removerPagamento(Pagamento pagamento) throws PagamentoNaoEncontradoException {
        if (pagamentos.remove(pagamento)) {
            Notificacao.notificarSucesso("Pagamento removido com sucesso.");
        } else {
            throw new PagamentoNaoEncontradoException("Pagamento não encontrado.");
        }
    }
}