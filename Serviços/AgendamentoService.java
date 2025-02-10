package entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AgendamentoService {
    private List<Consulta> consultas;
    
    public AgendamentoService() {
        this.consultas = new ArrayList<>();
    }

    // CREATE - Agendar uma nova consulta
    public void agendarConsulta(Consulta consulta) {
        Medico medico = consulta.getMedico();
        Paciente paciente = consulta.getPaciente();

        try {
            // Verifica se o médico está disponível
            if (!consulta.verificarDisponibilidadeMedico()) {
                throw new HorarioIndisponivelException();
            }

            // Verifica se o paciente já tem consulta no mesmo dia
            if (!consulta.verificarDisponibilidadePaciente()) {
                throw new HorarioIndisponivelException(); // Paciente não disponível no dia
            }

            // Verifica a especialidade do médico
            if (!consulta.verificarEspecialidadeRequerida(medico.getEspecialidade())) {
                throw new EspecialidadeInvalidaException(); // Especialidade não compatível
            }

            // Verifica se o paciente possui pagamentos pendentes
            Pagamento pagamentoService = new Pagamento();
            if (Pagamento.getStatusPagamento()) {
                throw new PagamentoPendenteException(); // Paciente com pagamento pendente
            }

            consultas.add(consulta); // Agendando a consulta
            Notificacao.notificarAgendamento(consulta); // Notifica sucesso do agendamento

        } catch (HorarioIndisponivelException e) {
            Notificacao.notificarErroAgendamento("Horário do médico ou paciente indisponível.");
        } catch (EspecialidadeInvalidaException e) {
            Notificacao.notificarErroAgendamento("Especialidade do médico incompatível com a consulta.");
        } catch (PagamentoPendenteException e) {
            Notificacao.notificarErroAgendamento("Paciente com pagamento pendente.");
        } catch (Exception e) {
            Notificacao.notificarErroAgendamento("Erro inesperado: " + e.getMessage());
        }
    }

    // READ - Obter todas as consultas de uma pessoa (Paciente ou Médico)
    public List<Consulta> getConsultasPorPessoa(Pessoa pessoa) {
        List<Consulta> consultasPessoa = new ArrayList<>();
        for (Consulta consulta : consultas) {
            if (consulta.getPaciente().equals(pessoa) || consulta.getMedico().equals(pessoa)) {
                consultasPessoa.add(consulta);
            }
        }
        return consultasPessoa;
    }

    // UPDATE - Atualizar dados de uma consulta
    public void atualizarConsulta(Consulta consultaAntiga, Consulta consultaNova) {
        try {
            Optional<Consulta> consultaOptional = consultas.stream()
                .filter(c -> c.equals(consultaAntiga))
                .findFirst();

            if (consultaOptional.isPresent()) {
                Consulta consulta = consultaOptional.get();

                // Verifica se o horário e médico estão disponíveis para a nova consulta
                if (!consultaNova.verificarDisponibilidadeMedico()) {
                    throw new HorarioIndisponivelException();
                }

                // Atualiza os dados da consulta
                consulta.setDataConsulta(consultaNova.getDataConsulta());
                consulta.setHorarioConsulta(consultaNova.getHorarioConsulta());
                
                Notificacao.notificarSucesso("Consulta atualizada com sucesso para " + consulta.getDataConsulta() + " às " + consulta.getHorarioConsulta());
            } else {
                throw new Exception("Consulta antiga não encontrada.");
            }
        } catch (HorarioIndisponivelException e) {
            Notificacao.notificarErroAgendamento("Horário ou médico não disponível para atualização.");
        } catch (Exception e) {
            Notificacao.notificarErroAgendamento("Erro ao atualizar consulta: " + e.getMessage());
        }
    }

    // DELETE - Remover uma consulta
    public void removerConsulta(Consulta consulta) {
        if (consultas.contains(consulta)) {
            consultas.remove(consulta);
            Notificacao.notificarSucesso("Consulta com " + consulta.getMedico().getNome() + " removida com sucesso.");
        } else {
            Notificacao.notificarErroAgendamento("Consulta não encontrada.");
        }
    }

    // Método para remover um médico
    public void removerMedico(String cpf) {
        Medico medico = Medico.buscarMedicoPorCpf(cpf);
        if (medico != null) {
            Medico.removerMedico(cpf);
            System.out.println("Médico removido: " + medico);
        } else {
            System.out.println("Médico não encontrado.");
        }
    }
}