package excecoes;

public class PagamentoNaoEncontradoException extends Exception {
    public PagamentoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}