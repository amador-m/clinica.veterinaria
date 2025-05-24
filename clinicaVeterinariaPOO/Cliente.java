package clinicaVeterinariaPOO;

import javax.swing.JOptionPane; 
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.concurrent.ThreadLocalRandom;

public class Cliente {
    private String nomePet, nomeTutor, raçaPet, contatoTutor;
    private LocalDate dataNascimento;
    private int codigo;

    public Cliente() {
        this.codigo = gerarCodigoAleatorio();
    }

    private int gerarCodigoAleatorio() {
        return ThreadLocalRandom.current().nextInt(10000, 99999);
    }

    public void setInfoCliente() {
        DateTimeFormatter formatarData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataNascimento = null;
        
        this.nomePet = JOptionPane.showInputDialog("Digite o nome do pet:");
        this.raçaPet = JOptionPane.showInputDialog("Digite a raça do pet:");
        
        while(dataNascimento==null) {
            String dtNascimentoS = JOptionPane.showInputDialog("Digite a data de nascimento (dd/MM/yyyy):");
            if(dtNascimentoS == null || dtNascimentoS.isBlank()) {
                JOptionPane.showMessageDialog(null, "Erro: data inválida. Digite uma data válida", "Atenção", JOptionPane.ERROR_MESSAGE);
                continue;
            }
            try {
                dataNascimento = LocalDate.parse(dtNascimentoS.trim(), formatarData);
            } catch(DateTimeParseException erro) {
                JOptionPane.showMessageDialog(null, "Erro: formato de data inválido. Use dd/MM/yyyy", "Atenção", JOptionPane.ERROR_MESSAGE);
            }
        }
        this.dataNascimento = dataNascimento;
        this.nomeTutor = JOptionPane.showInputDialog("Digite o nome do tutor:");
        this.contatoTutor = JOptionPane.showInputDialog("Digite o contato do tutor (e-mail ou telefone):");
    }


    public void imprimirCliente() {
        int idadePet = LocalDate.now().getYear() - dataNascimento.getYear();

        String fichaCad = "Nome do pet: " + nomePet +
			        	  "\nRaça: " + raçaPet +
			              "\nIdade: " + idadePet + " anos" +
			              "\nNome do tutor: " + nomeTutor +
			              "\nContato do tutor: " + contatoTutor +
			              "\nCódigo do cliente: " + codigo;

        JOptionPane.showMessageDialog(null, fichaCad, "Ficha Pet", JOptionPane.INFORMATION_MESSAGE);
    }
    
	public String getNomePet() {
		return nomePet;
	}
	public String getNomeTutor() {
		return nomeTutor;
	}
	public String getRaçaPet() {
		return raçaPet;
	}
	public String getContatoTutor() {
		return contatoTutor;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public int getCodigo() {
		return codigo;
	}
	
}
