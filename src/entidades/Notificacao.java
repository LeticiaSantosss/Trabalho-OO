package entidades;
public class Notificacao {

    // Método para enviar uma notificação de sucesso
    public static void notificarSucesso(String mensagem) {
        System.out.println("SUCESSO: " + mensagem);
        // Aqui você pode adicionar mais lógica para enviar notificações (email, SMS, etc.)
    }

    // Método para enviar uma notificação de erro
    public static void notificarErro(String mensagem) {
        System.out.println("ERRO: " + mensagem);
        // Aqui você pode adicionar mais lógica para enviar notificações de erro
    }

    // Método para notificar o agendamento de uma consulta
    public static void notificarAgendamento(Consulta consulta) {
        String mensagem = "Consulta agendada com " + consulta.getMedico().getNome() + 
                          " para o dia " + consulta.getDataConsulta().toString() + 
                          " às " + consulta.getHorarioConsulta().toString();
        notificarSucesso(mensagem);
    }

    // Método para notificar um erro de agendamento
    public static void notificarErroAgendamento(String erro) {
        notificarErro("Erro ao agendar consulta: " + erro);
    }
}
