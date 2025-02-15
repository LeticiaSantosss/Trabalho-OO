package Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

import entidades.Consulta;
import entidades.Medico;
import entidades.Paciente;
import entidades.Pessoa;

public class Principal {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        AgendamentoService agendamentoService = new AgendamentoService();

        while (true) {
            System.out.println("\n*** Menu de Agendamento de Consultas ***");
            System.out.println("1. Adicionar Médico");
            System.out.println("2. Adicionar Paciente");
            System.out.println("3. Agendar Consulta");
            System.out.println("4. Listar Médicos");
            System.out.println("5. Listar Pacientes");
            System.out.println("6. Remover Médico");
            System.out.println("7. Remover Consulta");
            System.out.println("8. Sair");
            System.out.print("Escolha uma opção: ");
            
            int opcao = scanner.nextInt();
            scanner.nextLine();  // Consumir a linha pendente após nextInt()

            switch (opcao) {
                case 1: // Adicionar Médico
                    System.out.print("Digite o nome do médico: ");
                    String nomeMedico = scanner.nextLine();
                    System.out.print("Digite o CRM do médico: ");
                    String crm = scanner.nextLine();
                    System.out.print("Digite a especialidade do médico: ");
                    String especialidade = scanner.nextLine();
                    System.out.print("Digite a data de nascimento do médico (yyyy-mm-dd): ");
                    LocalDate dataNascimentoMedico = LocalDate.parse(scanner.nextLine());

                    Medico medico = new Medico(nomeMedico, crm, dataNascimentoMedico, crm, especialidade);
                    AgendamentoService.adicionarMedico(medico);
                    break;
                
                case 2: // Adicionar Paciente
                    System.out.print("Digite o nome do paciente: ");
                    String nomePaciente = scanner.nextLine();
                    System.out.print("Digite o CPF do paciente: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Digite a data de nascimento do paciente (yyyy-mm-dd): ");
                    LocalDate dataNascimentoPaciente = LocalDate.parse(scanner.nextLine());

                    Paciente paciente = new Paciente(nomePaciente, cpf, dataNascimentoPaciente);
                    agendamentoService.adicionarPaciente(paciente);
                    break;

                case 3: // Agendar Consulta
                    System.out.print("Digite o CPF do paciente para agendar consulta: ");
                    String cpfPaciente = scanner.nextLine();  // Use o CPF para procurar
                    Paciente pacienteConsulta = agendamentoService.buscarPacientePorCpf(cpfPaciente);
                    if (pacienteConsulta == null) {
                        System.out.println("Paciente não encontrado.");
                        break;
                        
                    }

                    System.out.print("Digite o CRM do médico: ");
                    String crmConsulta = scanner.nextLine();
                    Medico medicoConsulta = agendamentoService.buscarMedicoPorCrm(crmConsulta);
                    if (medicoConsulta == null) {
                        System.out.println("Médico não encontrado.");
                        break;
                    }

                    System.out.print("Digite a data da consulta (yyyy-mm-dd): ");
                    LocalDate dataConsulta = LocalDate.parse(scanner.nextLine());
                    System.out.print("Digite o horário da consulta (HH:mm): ");
                    LocalTime horarioConsulta = LocalTime.parse(scanner.nextLine());

                    Consulta consulta = new Consulta(dataConsulta, horarioConsulta, pacienteConsulta, medicoConsulta);
                    agendamentoService.agendarConsulta(consulta);
                    break;

                case 4: // Listar Médicos
                    agendamentoService.listarMedicos();
                    break;

                case 5: // Listar Pacientes
                    agendamentoService.listarPacientes();
                    break;

                case 6: // Remover Médico
                    System.out.print("Digite o CRM do médico a ser removido: ");
                    String crmRemover = scanner.nextLine();
                    agendamentoService.removerMedico(crmRemover);
                    break;
                case 7: // Remover Consulta
                    System.out.print("Digite o nome do paciente ou CRM do médico para remover consulta: ");
                    String nomeOuCrm = scanner.nextLine();

                    // Verifica se é um paciente ou médico
                    Pessoa pessoaRemover = null;
                    Paciente pacienteRemover = agendamentoService.buscarPacientePorCpf(nomeOuCrm);
                    if (pacienteRemover != null) {
                        pessoaRemover = pacienteRemover;
                    } else {
                        Medico medicoRemover = agendamentoService.buscarMedicoPorCrm(nomeOuCrm);
                        if (medicoRemover != null) {
                            pessoaRemover = medicoRemover;
                        }
                    }

                    if (pessoaRemover != null) {
                        // Obtém as consultas dessa pessoa (paciente ou médico)
                        List<Consulta> consultasPessoa = agendamentoService.getConsultasPorPessoa(pessoaRemover);

                        if (consultasPessoa.isEmpty()) {
                            System.out.println("Nenhuma consulta encontrada para " + pessoaRemover.getNome() + ".");
                        } else {
                            // Exibe as consultas encontradas
                            System.out.println("Consultas encontradas:");
                            for (Consulta c : consultasPessoa) {
                                System.out.println("Data: " + c.getDataConsulta() + ", Hora: " + c.getHorarioConsulta());
                            }

                            // Solicita ao usuário escolher qual consulta remover
                            System.out.print("Digite a data da consulta (yyyy-mm-dd) que deseja remover: ");
                            LocalDate dataConsultaRemover = LocalDate.parse(scanner.nextLine());
                            System.out.print("Digite o horário da consulta (HH:mm) que deseja remover: ");
                            LocalTime horarioConsultaRemover = LocalTime.parse(scanner.nextLine());

                            // Encontra a consulta correspondente
                            Consulta consultaRemover = null;
                            for (Consulta c : consultasPessoa) {
                                if (c.getDataConsulta().equals(dataConsultaRemover) &&
                                    c.getHorarioConsulta().equals(horarioConsultaRemover)) {
                                    consultaRemover = c;
                                    break;
                                }
                            }

                            if (consultaRemover != null) {
                                agendamentoService.removerConsulta(consultaRemover);
                            } else {
                                System.out.println("Consulta não encontrada.");
                            }
                        }
                    } else {
                        System.out.println("Paciente ou médico não encontrado.");
                    }
                    break;



                case 8: // Sair
                    System.out.println("Saindo...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
