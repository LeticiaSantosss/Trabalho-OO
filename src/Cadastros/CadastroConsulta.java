package cadastros;

import entidades.Consulta;
import java.util.ArrayList;
import java.util.List;

public class CadastroConsulta {

    private List<Consulta> consultas;

    public CadastroConsulta() {
        this.consultas = new ArrayList<>();
    }

    public void agendarConsulta(Consulta consulta) {
        consultas.add(consulta);
    }

    public List<Consulta> listarConsultas() {
        return new ArrayList<>(consultas);
    }

    public boolean cancelarConsulta(String CRMConsulta) {
        return consultas.removeIf(c -> c.getCRM().equals(CRMConsulta));
    }
}