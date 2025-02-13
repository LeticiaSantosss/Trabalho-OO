package Cadastros;

import entidades.Paciente;
import java.util.ArrayList;
import java.util.List;

public class CadastroPaciente {
    private static List<Paciente> pacientes = new ArrayList<>(); // Lista de pacientes cadastrados

    // Adicionar um novo paciente
    public static void adicionarPaciente(Paciente paciente) {
        // Verificar se o CPF já está cadastrado
        if (buscarPacientePorCpf(paciente.getCPF()) != null) {
            System.out.println("Erro: CPF já cadastrado!");  // Exibe mensagem de erro e retorna
            return;
        }
        pacientes.add(paciente);
        System.out.println("Paciente adicionado com sucesso!");
    }

    // Buscar paciente pelo CPF
    public static Paciente buscarPacientePorCpf(String cpf) {
        return pacientes.stream().filter(p -> p.getCPF().equals(cpf)).findFirst().orElse(null);
    }

    // Atualizar informações de um paciente (somente nome e data de nascimento)
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

    // Remover paciente pelo CPF
    public static void removerPaciente(String cpf) {
        boolean removido = pacientes.removeIf(p -> p.getCPF().equals(cpf));
        if (removido) {
            System.out.println("Paciente removido com sucesso!");
        } else {
            System.out.println("Erro: Nenhum paciente encontrado com o CPF informado.");
        }
    }

    // Listar todos os pacientes cadastrados
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
