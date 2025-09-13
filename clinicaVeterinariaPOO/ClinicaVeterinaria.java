package clinicaVeterinariaPOO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ClinicaVeterinaria {
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Atendimento> atendimentos = new ArrayList<>();
    
    public void menu() {
        while(true) {
            String menuOpcoes = """
                       MENU DE SERVIÇOS       
                1 - Adicionar cliente
                2 - Remover cliente
                3 - Listar todos os clientes
                4 - Buscar cliente por código
                5 - Agendar atendimento
                6 - Listar atendimentos
                0 - Sair
                
                Escolha uma opção:
                
                """; 
            String opc = JOptionPane.showInputDialog(null, menuOpcoes, "Clínica Veterinária", JOptionPane.QUESTION_MESSAGE);

            if(opc==null||opc.isBlank()) { 
                JOptionPane.showMessageDialog(null, "Erro: operação cancelada ou entrada inválida", "Atenção", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            int op;
            try {
                op = Integer.parseInt(opc.trim()); // elimina espaços vazios
            } catch(NumberFormatException errito) {
                JOptionPane.showMessageDialog(null, "Erro: digite um número válido", "Atenção", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            switch(op) { // (= lambda =)
                case 1 -> clientes.add(novoCliente());
                case 2 -> removerCliente();
                case 3 -> listarClientes();
                case 4 -> buscarCliente();
                case 5 -> atendimentos.add(novoAtendimento());
                case 6 -> listarAtendimentos();
                case 0 -> {
                    JOptionPane.showMessageDialog(null, "Saindo...", "Fechando", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                default -> JOptionPane.showMessageDialog(null, "Erro: opção inválida", "Atenção", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private Cliente novoCliente() {
        Cliente cliNovo = new Cliente();
        cliNovo.setInfoCliente();
        return cliNovo;
    }

    private Atendimento novoAtendimento() {
        String dsc = JOptionPane.showInputDialog("Digite a descrição do atendimento:");
        LocalDate dataAtendimento;
        
        if(dsc==null||dsc.isBlank()) {
            JOptionPane.showMessageDialog(null, "Erro: descrição inválida", "Atenção", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        while(true) {
            String dt = JOptionPane.showInputDialog("Digite a data do atendimento (dd/MM/yyyy):");
            if(dt == null || dt.isBlank()) {
                JOptionPane.showMessageDialog(null, "Erro: data inválida!", "Atenção", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            try {
                dataAtendimento = LocalDate.parse(dt.trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                break;
            } catch(DateTimeParseException erroo) {
                JOptionPane.showMessageDialog(null, "Erro: formato de data inválido. Use dd/MM/yyyy", "Atenção", JOptionPane.ERROR_MESSAGE);
            }
        }

        return new Atendimento(dsc.trim(), dataAtendimento);
    }

    private void removerCliente() {
        String cod = JOptionPane.showInputDialog("Digite o código do cliente a ser removido:");
        int codBuscado;
        boolean removido = false;

        if(cod == null || cod.isBlank()) {
            JOptionPane.showMessageDialog(null, "Erro: Código inválido", "Atenção", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            codBuscado = Integer.parseInt(cod.trim());
        } catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro: digite um número válido", "Atenção", JOptionPane.ERROR_MESSAGE);
            return;
        }

        for(int i = 0; i < clientes.size(); i++) {
            if(clientes.get(i).getCodigo() == codBuscado) {
                clientes.remove(i);
                removido = true;
                break;
            }
        }
        if(removido) {
            JOptionPane.showMessageDialog(null, "Cliente removido com sucesso", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Erro: cliente não encontrado", "Atenção", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarCliente() {
        int codBuscado;
        try {
            codBuscado = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do cliente:")); // string pra int
        } catch(NumberFormatException errinho) {
            JOptionPane.showMessageDialog(null, "Código inválido", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        boolean encontrado = false;
        for(int x=0;x<clientes.size();x++) {
            if(clientes.get(x).getCodigo() == codBuscado) {
                clientes.get(x).imprimirCliente();
                encontrado = true;
                break;
            }
        }
        if(!encontrado) {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void listarClientes() {
        if(clientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum cliente cadastrado", "Lista de Clientes", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String listaClientes = "";
        for(int x=0;x<clientes.size();x++) {
            listaClientes += "Cliente nº" + (x + 1) +
                             "\nNome: " + clientes.get(x).getNomePet() +
                             "\nTutor: " + clientes.get(x).getNomeTutor() +
                             "\nCódigo: " + clientes.get(x).getCodigo() +
                             "\n-------------------------------\n\n";
        }
        JOptionPane.showMessageDialog(null, listaClientes, "Lista de Clientes", JOptionPane.INFORMATION_MESSAGE);
    }

    private void listarAtendimentos() {
        if(atendimentos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum atendimento cadastrado", "Lista de Atendimentos", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String listaAtendimentos = "";
        for (int x = 0; x < atendimentos.size(); x++) {
            Atendimento atendimento = atendimentos.get(x);
            if(atendimento == null) {
                continue; // ignora atendimentos vazios (só o enter) pra não dar erro
            }
            listaAtendimentos += "Atendimento nº" + (x + 1) +
                                 "\nData: " + atendimento.getDtAtendimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
                                 "\nDescrição: " + atendimento.getDescricao() +
                                 "\n-------------------------------\n\n";
        }
        JOptionPane.showMessageDialog(null, listaAtendimentos, "Lista de Atendimentos", JOptionPane.INFORMATION_MESSAGE);
    }



}


