package Cadastros;

import entidades.Paciente;
import entidades.Medico;
import java.time.LocalDate;

public class TesteCadastro {
    public static void main(String[] args) throws Exception {
        // Criar novos pacientes
        Paciente paciente1 = null;
		try {
			paciente1 = new Paciente("João Silva", "12345678901", LocalDate.of(1990, 5, 15));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Paciente paciente2 = new Paciente("Maria Oliveira", "98765432100", LocalDate.of(1985, 8, 20));
        Paciente paciente3 = new Paciente("Carlos Souza", "11223344556", LocalDate.of(2000, 3, 10));

        // Adicionar pacientes ao cadastro
        CadastroPaciente.adicionarPaciente(paciente1);
        CadastroPaciente.adicionarPaciente(paciente2);
        CadastroPaciente.adicionarPaciente(paciente3);

        // Listar todos os pacientes cadastrados
        System.out.println("Lista de pacientes após adição:");
        CadastroPaciente.listarPacientes();
        System.out.println();

        // Tentar adicionar um paciente com CPF duplicado
        Paciente pacienteDuplicado = new Paciente("João Silva", "12345678901", LocalDate.of(1990, 5, 15));
        CadastroPaciente.adicionarPaciente(pacienteDuplicado); // Não será adicionado devido ao CPF duplicado

        // Listar pacientes após tentativa de adição de CPF duplicado
        System.out.println("Lista de pacientes após tentativa de adição de CPF duplicado:");
        CadastroPaciente.listarPacientes();
        System.out.println();

        // Criar novos médicos
        Medico medico1 = new Medico("Dr. João Silva", "12345678901", LocalDate.of(1980, 5, 15), "CRM1234", "Cardiologia");
        Medico medico2 = new Medico("Dra. Maria Oliveira", "98765432100", LocalDate.of(1975, 8, 20), "CRM5678", "Pediatria");
        Medico medico3 = new Medico("Dr. Carlos Souza", "11223344556", LocalDate.of(1990, 3, 10), "CRM9101", "Ortopedia");

        // Adicionar médicos ao cadastro
        CadastroMedico.adicionarMedico(medico1);
        CadastroMedico.adicionarMedico(medico2);
        CadastroMedico.adicionarMedico(medico3);

        // Listar todos os médicos cadastrados
        System.out.println("Lista de médicos após adição:");
        CadastroMedico.listarMedicos();
        System.out.println();

        // Tentar adicionar um médico com CRM duplicado
        Medico medicoDuplicado = new Medico("Dr. João Silva", "12345678901", LocalDate.of(1980, 5, 15), "CRM1234", "Cardiologia");
        CadastroMedico.adicionarMedico(medicoDuplicado); // Não será adicionado devido ao CRM duplicado

        // Listar médicos após tentativa de adição de CRM duplicado
        System.out.println("Lista de médicos após tentativa de adição de CRM duplicado:");
        CadastroMedico.listarMedicos();
    }
}
