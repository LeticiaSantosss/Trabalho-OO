package entidades;

import java.time.LocalDate;

public class Notificacao {
private String mensagem;
private LocalDate dataEnvio;

public void notificacao(String mensagem, LocalDate dataEnvio) {
	this.setMensagem(mensagem);
	this.setDataEnvio(dataEnvio);
}

public String getMensagem() {
	return mensagem;
}

public void setMensagem(String mensagem) {
	this.mensagem = mensagem;
}

public LocalDate getDataEnvio() {
	return dataEnvio;
}

public void setDataEnvio(LocalDate dataEnvio) {
	this.dataEnvio = dataEnvio;
}

}
