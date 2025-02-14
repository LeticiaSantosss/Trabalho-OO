package servicos;

import entidades.*;
import excecoes.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AgendamentoService {
    private List<Consulta> consultas;

    public AgendamentoService() {
        this.consultas = new ArrayList<>();
    }

    // Agendar uma nova consulta
    public void agendarConsulta(Consulta consulta) throws HorarioIndisponivelException, PagamentoPendenteException, EspecialidadeInvalidaException {
        Medico medico = consulta.getMedico();
        Paciente paciente = consulta.getPaciente();

        // Verifica se o médico está disponível no horário
        if (!medico.verificarDisponibilidade(consulta.getDataConsulta(), consulta.getHorarioConsulta())) {
            throw new HorarioIndisponivelException("Médico não está disponível no horário solicitado.");
        }

        // Verifica se o paciente já tem consulta no mesmo dia
        if (!paciente.verificarDisponibilidadePaciente(consulta.getDataConsulta())) {
            throw new HorarioIndisponivelException("Paciente já possui uma consulta agendada para este dia.");
        }

        // Verifica se o médico possui a especialidade requerida
        if (!medico.verificarEspecialidade(consulta.getEspecialidade())) {
            throw new EspecialidadeInvalidaException("Médico não possui a especialidade requerida.");
        }

        // Verifica se o paciente possui pagamentos pendentes
        if (!paciente.getPagamentosPendentes().isEmpty()) {
            throw new PagamentoPendenteException("Paciente possui pagamentos pendentes.");
        }

        // Adiciona a consulta à lista de consultas
        consultas.add(consulta);
        Notificacao.notificarAgendamento(consulta);
    }

    // Obter todas as consultas de um paciente ou médico
    public List<Consulta> getConsultasPorPessoa(Pessoa pessoa) {
        List<Consulta> consultasPessoa = new ArrayList<>();
        for (Consulta consulta : consultas) {
            if (consulta.getPaciente().equals(pessoa) || consulta.getMedico().equals(pessoa)) {
                consultasPessoa.add(consulta);
            }
        }
        return consultasPessoa;
    }

    // Atualizar uma consulta existente
    public void atualizarConsulta(Consulta consultaAntiga, Consulta consultaNova) throws HorarioIndisponivelException {
        Optional<Consulta> consultaOptional = consultas.stream()
            .filter(c -> c.equals(consultaAntiga))
            .findFirst();

        if (consultaOptional.isPresent()) {
            Consulta consulta = consultaOptional.get();

            // Verifica se o novo horário está disponível
            if (!consultaNova.getMedico().verificarDisponibilidade(consultaNova.getDataConsulta(), consultaNova.getHorarioConsulta())) {
                throw new HorarioIndisponivelException("Novo horário indisponível para o médico.");
            }

            // Atualiza os dados da consulta
            consulta.setDataConsulta(consultaNova.getDataConsulta());
            consulta.setHorarioConsulta(consultaNova.getHorarioConsulta());
            Notificacao.notificarSucesso("Consulta atualizada com sucesso.");
        } else {
            try {
                throw new ConsultaNaoEncontradaException("Consulta antiga não encontrada.");
            } catch (ConsultaNaoEncontradaException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    // Remover uma consulta
    public void removerConsulta(Consulta consulta) throws ConsultaNaoEncontradaException {
        if (consultas.remove(consulta)) {
            Notificacao.notificarSucesso("Consulta removida com sucesso.");
        } else {
            throw new ConsultaNaoEncontradaException("Consulta não encontrada.");
        }
    }
}