package entidades;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Paciente extends Pessoa {
    private static Set<String> cpfsRegistrados = new HashSet<>();
    private List<Consulta> historicoConsultas;
public Paciente() {
	super();
	 this.historicoConsultas = new ArrayList<>();
}
    public Paciente(String nome, String CPF, LocalDate dataNascimento) throws Exception {
        super(nome, CPF, dataNascimento);

        // Verifica se o CPF já foi registrado
        if (cpfsRegistrados.contains(CPF)) {
            System.out.println("CPF já registrado");
        }

        cpfsRegistrados.add(CPF);
        this.historicoConsultas = new ArrayList<>();
    }

    // Método para adicionar consulta
    public void adicionarConsulta(Consulta consulta) {
        this.historicoConsultas.add(consulta);
    }

    // Método para remover consulta
    public void removerConsulta(Consulta consulta) {
        this.historicoConsultas.remove(consulta);
    }

    // Método para listar todas as consultas do paciente
    public List<Consulta> listarConsultas() {
        return this.historicoConsultas;
    }

    // Métodos estáticos para o CRUD de pacientes

    // Lista estática de pacientes para controle
    private static List<Paciente> pacientes = new ArrayList<>();

    // Adicionar paciente
    public static void adicionarPaciente(Paciente paciente) {
        if (!cpfJaCadastrado(paciente.getCPF())) {
            pacientes.add(paciente);
        }
    }

    // Buscar paciente por CPF
    public static Paciente buscarPacientePorCpf(String CPF) {
        return pacientes.stream()
                .filter(p -> p.getCPF().equals(CPF))
                .findFirst()
                .orElse(null); // Retorna null se não encontrar
    }

    // Atualizar paciente (Nome e Data de Nascimento)
    public static boolean atualizarPaciente(String CPF, String novoNome, LocalDate novaDataNascimento) {
        Paciente paciente = buscarPacientePorCpf(CPF);
        if (paciente != null) {
            paciente.setNome(novoNome);
            paciente.setDataNascimento(novaDataNascimento);
            return true; // Atualização bem-sucedida
        }
        return false; // Paciente não encontrado
    }

    // Remover paciente
    public static boolean removerPaciente(String CPF) {
        Paciente paciente = buscarPacientePorCpf(CPF);
        if (paciente != null) {
            pacientes.remove(paciente);
            return true; // Remoção bem-sucedida
        }
        return false; // Paciente não encontrado
    }

    // Listar todos os pacientes
    public static List<Paciente> listarPacientes() {
        return pacientes;
    }

    // Verificar se CPF já está cadastrado
    public static boolean cpfJaCadastrado(String CPF) {
        return cpfsRegistrados.contains(CPF);
    }

    // Métodos para exibição
    public static void exibirPacientes() {
        for (Paciente paciente : pacientes) {
            System.out.println(paciente);
        }
    }

    // ToString para exibir informações do paciente
    @Override
    public String toString() {
        return "Paciente [nome=" + getNome() + ", cpf=" + getCPF() + ", dataNascimento=" + getDataNascimento() + "]";
    }

    // Getter e Setter para o histórico de consultas
    public List<Consulta> getHistoricoConsultas() {
        return historicoConsultas;
    }

    public void setHistoricoConsultas(List<Consulta> historicoConsultas) {
        this.historicoConsultas = historicoConsultas;
    }
    public boolean verificarDisponibilidadePaciente(LocalDate dataConsulta) {
        return this.historicoConsultas.stream()
            .noneMatch(c -> c.getDataConsulta().equals(dataConsulta));
    }
}
