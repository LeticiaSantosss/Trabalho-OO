package Service;
import entidades.*; 

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AgendamentoService {
    private List<Consulta> consultas;
    
    public AgendamentoService() {
        this.consultas = new ArrayList<>();
        Notificacao.notificarSucesso("Serviço de agendamento inicializado.");
    }

    public void agendarConsulta(Consulta consulta) {
        Medico medico = consulta.getMedico();
       

        try {
           
            if (!consulta.verificarDisponibilidadeMedico()) {
                throw new HorarioIndisponivelException("HorarioIndisponivel");
            }

            if (!consulta.verificarDisponibilidadePaciente()) {
                throw new HorarioIndisponivelException("Horario Indisponivel");
            }

          
            if (!consulta.verificarEspecialidade(medico.getEspecialidade())) {
                throw new EspecialidadeInvalidaException("Especialidade Inválida"); 
            }

            if (!consulta.isPagamentoRealizado()) {
                throw new PagamentoPendenteException("Pagamento pendente.");
            }


            consultas.add(consulta); 
            Notificacao.notificarAgendamento(consulta); 

        } catch (HorarioIndisponivelException e) {
            Notificacao.notificarErroAgendamento("Horário do médico ou paciente indisponível.");
        } catch (EspecialidadeInvalidaException e) {
            Notificacao.notificarSucesso("Especialidade do médico incompatível com a consulta.");
        } catch (PagamentoPendenteException e) {
            Notificacao.notificarSucesso("Paciente com pagamento pendente.");
        } catch (Exception e) {
            Notificacao.notificarSucesso("Erro inesperado: " + e.getMessage());
        }
    }

    
    public List<Consulta> getConsultasPorPessoa(Pessoa pessoa) {
        List<Consulta> consultasPessoa = new ArrayList<>();
        for (Consulta consulta : consultas) {
            if (consulta.getPaciente().equals(pessoa) || consulta.getMedico().equals(pessoa)) {
                consultasPessoa.add(consulta);
            }
        }

        if (consultasPessoa.isEmpty()) {
            Notificacao.notificarSucesso("Nenhuma consulta encontrada para " + pessoa.getNome());
        } else {
            Notificacao.notificarSucesso("Consultas encontradas para " + pessoa.getNome() + ": " + consultasPessoa.size());
        }

        return consultasPessoa;
    }
    public void atualizarConsulta(Consulta consultaAntiga, Consulta consultaNova) {
        try {
            Optional<Consulta> consultaOptional = consultas.stream()
                .filter(c -> c.equals(consultaAntiga))
                .findFirst();

            if (consultaOptional.isPresent()) {
                Consulta consulta = consultaOptional.get();

                
                if (!consultaNova.verificarDisponibilidadeMedico()) {
                    throw new HorarioIndisponivelException("Horario Indisponivel");
                }

                
                consulta.setDataConsulta(consultaNova.getDataConsulta());
                consulta.setHorarioConsulta(consultaNova.getHorarioConsulta());
                
                Notificacao.notificarSucesso("Consulta atualizada com sucesso para o dia " 
                        + consulta.getDataConsulta() + " às " + consulta.getHorarioConsulta());
            } else {
                throw new Exception("Consulta antiga não encontrada.");
            }
        } catch (HorarioIndisponivelException e) {
            Notificacao.notificarErroAgendamento("Horário ou médico não disponível para atualização.");
        } catch (Exception e) {
            Notificacao.notificarErroAgendamento("Erro ao atualizar consulta: " + e.getMessage());
        }
    }

    
    public void removerConsulta(Consulta consulta) {
        if (consultas.contains(consulta)) {
            consultas.remove(consulta);
            Notificacao.notificarSucesso("Consulta com " + consulta.getMedico().getNome() + " removida com sucesso.");
        } else {
            Notificacao.notificarErro("Consulta não encontrada para remoção.");
        }
    }
    private static List<Medico> medicos = new ArrayList<>(); // Lista de médicos cadastrados

    public static void adicionarMedico(Medico medico) {
        if (buscarMedicoPorCrm(medico.getCRM()) != null) {
            System.out.println("Erro: CRM já cadastrado!");  // Exibe mensagem de erro e retorna
            return;
        }
        medicos.add(medico);
        System.out.println("Médico adicionado com sucesso!");
    }

    public static Medico buscarMedicoPorCrm(String crm) {
        return medicos.stream().filter(m -> m.getCRM().equals(crm)).findFirst().orElse(null);
    }

    public static void atualizarMedico(Medico medicoAtualizado) {
        Medico medico = buscarMedicoPorCrm(medicoAtualizado.getCRM());
        if (medico != null) {
            medico.setEspecialidade(medicoAtualizado.getEspecialidade());
            medico.setNome(medicoAtualizado.getNome());
            System.out.println("Dados do médico atualizados com sucesso!");
        } else {
            System.out.println("Erro: Médico não encontrado para atualização.");
        }
    }

  
    public static void removerMedico(String crm) {
        boolean removido = medicos.removeIf(m -> m.getCRM().equals(crm));
        if (removido) {
           Notificacao.notificarSucesso("Médico removido com sucesso!");
        } else {
        	Notificacao.notificarErro("Erro: Nenhum médico encontrado com o CRM informado.");
        }
    }

   
    public static void listarMedicos() {
        if (medicos.isEmpty()) {
            System.out.println("Nenhum médico cadastrado.");
            return;
        }
        System.out.println("=== Lista de Médicos Cadastrados ===");
        for (Medico medico : medicos) {
            System.out.println(medico);
        }
    }
    private static List<Paciente> pacientes = new ArrayList<>();
    public static void adicionarPaciente(Paciente paciente) {
      
        if (buscarPacientePorCpf(paciente.getCPF()) != null) {
            System.out.println("Erro: CPF já cadastrado!");  
            return;
        }
        pacientes.add(paciente);
        System.out.println("Paciente adicionado com sucesso!");
    }
    public Paciente buscarPacientePorCpf(String cpf) {
        return pacientes.stream().filter(p -> p.getCPF().equals(cpf)).findFirst().orElse(null);
    }
    public static void atualizarPaciente(Paciente pacienteAtualizado) {
        Paciente paciente = buscarPacientePorCpf(pacienteAtualizado.getCPF());
        if (paciente != null) {
            paciente.setNome(pacienteAtualizado.getNome());
            paciente.setDataNascimento(pacienteAtualizado.getDataNascimento());
            System.out.println("Dados do paciente atualizados com sucesso!");
        } else {
            System.out.println("Erro: Paciente não encontrado para atualização.");
        }
    }
    public static void removerPaciente(String cpf) {
        boolean removido = pacientes.removeIf(p -> p.getCPF().equals(cpf));
        if (removido) {
            System.out.println("Paciente removido com sucesso!");
        } else {
            System.out.println("Erro: Nenhum paciente encontrado com o CPF informado.");
        }
    }

    
    public static void listarPacientes() {
        if (pacientes.isEmpty()) {
            System.out.println("Nenhum paciente cadastrado.");
            return;
        }
        System.out.println("=== Lista de Pacientes Cadastrados ===");
        for (Paciente paciente : pacientes) {
            System.out.println(paciente);
        }
    }
   
}

