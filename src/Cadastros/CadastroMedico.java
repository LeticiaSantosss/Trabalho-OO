package Cadastros;

import entidades.Medico;
import java.util.ArrayList;
import java.util.List;

public class CadastroMedico {
    private static List<Medico> medicos = new ArrayList<>(); // Lista de médicos cadastrados

    // Adicionar um novo médico
    public static void adicionarMedico(Medico medico) {
        // Verificar se o CRM já está cadastrado
        if (buscarMedicoPorCrm(medico.getCRM()) != null) {
            System.out.println("Erro: CRM já cadastrado!");  // Exibe mensagem de erro e retorna
            return;
        }
        medicos.add(medico);
        System.out.println("Médico adicionado com sucesso!");
    }

    // Buscar médico pelo CRM
    public static Medico buscarMedicoPorCrm(String crm) {
        return medicos.stream().filter(m -> m.getCRM().equals(crm)).findFirst().orElse(null);
    }

    // Atualizar informações de um médico (somente especialidade e nome)
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

    // Remover médico pelo CRM
    public static void removerMedico(String crm) {
        boolean removido = medicos.removeIf(m -> m.getCRM().equals(crm));
        if (removido) {
            System.out.println("Médico removido com sucesso!");
        } else {
            System.out.println("Erro: Nenhum médico encontrado com o CRM informado.");
        }
    }

    // Listar todos os médicos cadastrados
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
}
