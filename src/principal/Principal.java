package principal;

import javax.swing.JOptionPane;
import cadastros.CadastroPaciente;
import cadastros.CadastroMedico;
import cadastros.CadastroConsulta;
import cadastros.CadastroPagamento;
import view.MenuPaciente;
import view.MenuMedico;
import view.MenuConsulta;
import view.MenuPagamento;
import view.MenuPrincipal;

public class Principal {

    static CadastroPaciente cadPaciente;
    static CadastroMedico cadMedico;
    static CadastroConsulta cadConsulta;
    static CadastroPagamento cadPagamento;

    public static void main(String[] args) {
        cadPaciente = new CadastroPaciente();
        cadMedico = new CadastroMedico();
        cadConsulta = new CadastroConsulta();
        cadPagamento = new CadastroPagamento();

        MenuPaciente menuPaciente = new MenuPaciente(cadPaciente);
        MenuMedico menuMedico = new MenuMedico(); // Não precisa mais de CadastroMedico
        MenuConsulta menuConsulta = new MenuConsulta(cadConsulta);
        MenuPagamento menuPagamento = new MenuPagamento(cadPagamento);

        int opcao = 0;

        do {
            opcao = MenuPrincipal.menuOpcoes();
            switch (opcao) {
                case 1:
                    menuPaciente.exibirMenu();
                    break;
                case 2:
                    menuMedico.exibirMenu();
                    break;
                case 3:
                    menuConsulta.exibirMenu();
                    break;
                case 4:
                    menuPagamento.exibirMenu();
                    break;
                case 0:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida");
                    opcao = -1;
                    break;
            }
        } while (opcao != 0);
    }
}