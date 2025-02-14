package entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Medico extends Pessoa {
    private String CRM;
    private String especialidade;
    private List<Consulta> consultasAtendidas;

    public Medico() {
        super();
        this.consultasAtendidas = new ArrayList<>();
    }

    public Medico(String nome, String Cpf, LocalDate dataNascimento, String CRM, String especialidade) {
        super(nome, CRM, dataNascimento);
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
    @Override
    public String toString() {
        return "Nome: " + getNome() + ", CRM: " + CRM + ", Especialidade: " + especialidade;
    }
    public boolean verificarDisponibilidade(LocalDate dataConsulta, LocalTime horarioConsulta) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'verificarDisponibilidade'");
    }
}
