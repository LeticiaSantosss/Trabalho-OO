package entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Exame {
    private String tipo;
    private LocalDate dataRealizacao;
    private String resultado;
    private double custo;

    private static List<Exame> exames = new ArrayList<>();
 public Exame(String tipo, LocalDate dataRealizacao, String resultado, double custo) {
        this.tipo = tipo;
        this.dataRealizacao = dataRealizacao;
        this.resultado = resultado;
        this.custo = custo;
    }
    public static void adicionarExame(Exame exame) {
        exames.add(exame);
    }
    public static Exame buscarExamePorTipo(String tipo) {
        return exames.stream()
                     .filter(exame -> exame.getTipo().equalsIgnoreCase(tipo))
                     .findFirst()
                     .orElse(null); 
    }
    public static void atualizarExame(String tipoAntigo, Exame exameNovo) {
        Optional<Exame> exameOptional = exames.stream()
                                              .filter(exame -> exame.getTipo().equalsIgnoreCase(tipoAntigo))
                                              .findFirst();
        if (exameOptional.isPresent()) {
            Exame exame = exameOptional.get();
            exame.setTipo(exameNovo.getTipo());
            exame.setDataRealizacao(exameNovo.getDataRealizacao());
            exame.setResultado(exameNovo.getResultado());
            exame.setCusto(exameNovo.getCusto());
        }
    }
    public static void removerExame(String tipo) {
        exames.removeIf(exame -> exame.getTipo().equalsIgnoreCase(tipo));
    }
    public static List<Exame> listarExames() {
        return new ArrayList<>(exames);
    }
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getDataRealizacao() {
        return dataRealizacao;
    }

    public void setDataRealizacao(LocalDate dataRealizacao) {
        this.dataRealizacao = dataRealizacao;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }
}
