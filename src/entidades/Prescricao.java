package entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Prescricao {
    private Consulta consultaAssociada;
    private List<Exame> examesPrescritos;
    private List<String> medicamentos;
    private LocalDate dataValidade;

    private static List<Prescricao> prescricoes = new ArrayList<>();

    // Construtor
    public Prescricao(Consulta consultaAssociada, List<Exame> examesPrescritos, List<String> medicamentos, LocalDate dataValidade) {
        this.consultaAssociada = consultaAssociada;
        this.examesPrescritos = examesPrescritos;
        this.medicamentos = medicamentos;
        this.dataValidade = dataValidade;
    }

    // CRUD - CREATE - Adicionar uma nova prescrição
    public static void adicionarPrescricao(Prescricao prescricao) {
        prescricoes.add(prescricao);
    }

    // CRUD - READ - Buscar prescrição pela consulta associada
    public static Prescricao buscarPrescricaoPorConsulta(Consulta consulta) {
        return prescricoes.stream()
                          .filter(p -> p.getConsultaAssociada().equals(consulta))
                          .findFirst()
                          .orElse(null); // Retorna null caso não encontre
    }

    // CRUD - UPDATE - Atualizar uma prescrição existente
    public static void atualizarPrescricao(Consulta consultaAssociada, Prescricao prescricaoNova) {
        Optional<Prescricao> prescricaoOptional = prescricoes.stream()
                                                           .filter(p -> p.getConsultaAssociada().equals(consultaAssociada))
                                                           .findFirst();

        if (prescricaoOptional.isPresent()) {
            Prescricao prescricao = prescricaoOptional.get();
            prescricao.setExamesPrescritos(prescricaoNova.getExamesPrescritos());
            prescricao.setMedicamentos(prescricaoNova.getMedicamentos());
            prescricao.setDataValidade(prescricaoNova.getDataValidade());
        }
    }

    // CRUD - DELETE - Remover uma prescrição
    public static void removerPrescricao(Consulta consulta) {
        prescricoes.removeIf(p -> p.getConsultaAssociada().equals(consulta));
    }

    // CRUD - READ - Listar todas as prescrições
    public static List<Prescricao> listarPrescricoes() {
        return new ArrayList<>(prescricoes);
    }

    // Verificar validade de uma prescrição
    public boolean Validade() {
        return LocalDate.now().isBefore(dataValidade) || LocalDate.now().isEqual(dataValidade);
    }

    // Getters e Setters
    public Consulta getConsultaAssociada() {
        return consultaAssociada;
    }

    public void setConsultaAssociada(Consulta consultaAssociada) {
        this.consultaAssociada = consultaAssociada;
    }

    public List<Exame> getExamesPrescritos() {
        return examesPrescritos;
    }

    public void setExamesPrescritos(List<Exame> examesPrescritos) {
        this.examesPrescritos = examesPrescritos;
    }

    public List<String> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(List<String> medicamentos) {
        this.medicamentos = medicamentos;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }
}
