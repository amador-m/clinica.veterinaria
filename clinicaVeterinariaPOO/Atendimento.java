package clinicaVeterinariaPOO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Atendimento {
    private LocalDate dtAtendimento;
    private String descricao;

    public Atendimento(String descricao, LocalDate dataAtendimento) {
        this.dtAtendimento = dataAtendimento; // Agora a data pode ser escolhida pelo usuário
        this.descricao = descricao;
    }

    public LocalDate getDtAtendimento() {
        return dtAtendimento;
    }

    public String getDescricao() {
        return descricao;
    }

}
