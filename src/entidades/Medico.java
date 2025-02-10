package entidades;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Medico extends Pessoa {
    String CRM, especialidade;
    private List<Consulta> consultasAtendidas;
    private static List<Medico> medicos = new ArrayList<>(); // Lista de médicos
    
    public Medico() {
    	super();
    	this.consultasAtendidas = new ArrayList<>();
    }
    public Medico(String nome, String Cpf, LocalDate dataNascimento, String CRM, String especialidade) {
        super(nome, Cpf, dataNascimento);
        this.CRM = CRM;
        this.especialidade = especialidade;
        this.consultasAtendidas = new ArrayList<>();
    }

   

	public boolean verificarEspecialidade(String especialidadeRequerida) {
        return this.especialidade.equalsIgnoreCase(especialidadeRequerida);
    }

    public List<Consulta> getHistoricoConsultasAtendidas() {
        return consultasAtendidas;
    }

    public void adicionarConsultasAtendidas(Consulta consulta) {
        this.consultasAtendidas.add(consulta);
    }

    // Métodos de serviço dentro da classe Medico

    public static void adicionarMedico(Medico medico) {
        medicos.add(medico);
    }

    public static Medico buscarMedicoPorCpf(String cpf) {
        return medicos.stream().filter(m -> m.getCPF().equals(cpf)).findFirst().orElse(null);
    }

    public static void atualizarMedico(Medico medicoAtualizado) {
        Medico medico = buscarMedicoPorCpf(medicoAtualizado.getCPF());
        if (medico != null) {
            medico.setEspecialidade(medicoAtualizado.getEspecialidade());
            medico.setCRM(medicoAtualizado.getCRM());
        }
    }

    public static void removerMedico(String cpf) {
        medicos.removeIf(m -> m.getCPF().equals(cpf));
    }

    // Getters e Setters
    public String getCRM() {
        return CRM;
    }

    public void setCRM(String CRM) {
        this.CRM = CRM;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}
