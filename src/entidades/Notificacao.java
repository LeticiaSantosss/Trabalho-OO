package entidades;
public class Notificacao {

   
    public static void notificarSucesso(String mensagem) {
        System.out.println("SUCESSO: " + mensagem);
    }
 public static void notificarErro(String mensagem) {
        System.out.println("ERRO: " + mensagem);    
    }
 public static void notificarAgendamento(Consulta consulta) {
        String mensagem = "Consulta agendada com " + consulta.getMedico().getNome() + 
                          " para o dia " + consulta.getDataConsulta().toString() + 
                          " às " + consulta.getHorarioConsulta().toString();
        notificarSucesso(mensagem);
    }
    public static void notificarErroAgendamento(String erro) {
        notificarErro("Erro ao agendar consulta: " + erro);
    }
}