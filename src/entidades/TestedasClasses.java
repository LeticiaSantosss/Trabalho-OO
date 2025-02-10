package entidades;
import java.time.LocalDate;
import java.time.LocalTime;

public class TestedasClasses {

    public static void main(String[] args) throws Exception {
        // Criando objetos de exemplo com os parâmetros corretos de acordo com o UML

        // Criando um paciente
   
        Paciente paciente = new Paciente("João Silva", "123456789", LocalDate.of(1985, 5, 10));

        // Criando um médico
    
        Medico medico = new Medico("Dr. Pedro Souza", "987654321", LocalDate.of(1978, 11, 20), "CRM12345", "Cardiologia");

        // Criando uma consulta
        Consulta consulta = new Consulta(LocalDate.of(2025, 2, 10), LocalTime.of(10, 30), paciente, medico);

        // Criando um exame
        Exame exame = new Exame("ECG", LocalDate.of(2025, 2, 5), "ok", 100.0);

        // Adicionando consulta e exame ao histórico
        paciente.adicionarConsulta(consulta);
        medico.adicionarConsulta(consulta);
        paciente.adicionarExame(exame);

        // Criando um pagamento
        Pagamento pagamento = new Pagamento(150.0);

        // Adicionando pagamento ao histórico
        paciente.adicionarPagamento(pagamento);

        // Exibindo dados
        System.out.println("Histórico de Consultas do Paciente:");
        for (Consulta c : paciente.getHistoricoConsultas()) {
            System.out.println("Consulta: " + c.getDataConsulta() + " - " + c.getHorarioConsulta());
        }

        System.out.println("\nHistórico de Exames do Paciente:");
        for (Exame e : paciente.getHistoricoExames()) {
            System.out.println("Exame: " + e.getTipo() + " - Custo: " + e.getCusto());
        }

        System.out.println("\nHistórico de Pagamentos do Paciente:");
        for (Pagamento p : paciente.getHistoricoPagamentos()) {
            System.out.println("Pagamento: " + p.getValor() + " - Status: " + Pagamento.getStatusPagamento());
        }

        // Verificando se há pagamentos pendentes
        System.out.println("\nPagamentos Pendentes:");
        for (Pagamento p : paciente.getPagamentosPendentes()) {
            System.out.println("Pagamento pendente de: " + p.getValor());
        }
    }
}
