package excecoes;

public class ConsultaNaoEncontradaException extends Exception {
    public ConsultaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}