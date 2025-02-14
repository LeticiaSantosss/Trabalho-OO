package entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Paciente extends Pessoa {
    private static Set<String> cpfsRegistrados = new HashSet<>();
    private static List<Paciente> pacientes = new ArrayList<>();

    public Paciente() {
        super();
    }

    public Paciente(String nome, String CPF, LocalDate dataNascimento) throws Exception {
        super(nome, CPF, dataNascimento);
        if (cpfsRegistrados.contains(CPF)) {
            throw new Exception("CPF já registrado.");
        }

        cpfsRegistrados.add(CPF);
    }
 
    	public static void adicionarPaciente(Paciente paciente) throws Exception {
        if (cpfJaCadastrado(paciente.getCPF())) {
            throw new Exception("Paciente com CPF já cadastrado: " + paciente.getCPF());
        }
        pacientes.add(paciente);
        cpfsRegistrados.add(paciente.getCPF());
        System.out.println("Paciente adicionado com sucesso: " + paciente.getNome());
    }
    public static Paciente buscarPacientePorCpf(String CPF) {
        return pacientes.stream()
                .filter(p -> p.getCPF().equals(CPF))
                .findFirst()
                .orElse(null); 
    }
    	public static boolean atualizarPaciente(String CPF, String novoNome, LocalDate novaDataNascimento) {
        Paciente paciente = buscarPacientePorCpf(CPF);
        if (paciente != null) {
            paciente.setNome(novoNome);
            paciente.setDataNascimento(novaDataNascimento);
            System.out.println("Paciente atualizado com sucesso: " + paciente.getNome());
            return true;
        }
        System.out.println("Paciente não encontrado para o CPF: " + CPF);
        return false;
    }
    	public static boolean removerPaciente(String CPF) {
        Paciente paciente = buscarPacientePorCpf(CPF);
        if (paciente != null) {
            pacientes.remove(paciente);
            cpfsRegistrados.remove(CPF);
            System.out.println("Paciente removido com sucesso: " + paciente.getNome());
            return true;
        }
        System.out.println("Paciente não encontrado para o CPF: " + CPF);
        return false;
    }
    	public static List<Paciente> listarPacientes() {
        return pacientes;
    }
    	public static boolean cpfJaCadastrado(String CPF) {
        return cpfsRegistrados.contains(CPF);
    }

    @Override
    public String toString() {
        return "Paciente [nome=" + getNome() + ", CPF=" + getCPF() + ", dataNascimento=" + getDataNascimento() + "]";
    }
}
