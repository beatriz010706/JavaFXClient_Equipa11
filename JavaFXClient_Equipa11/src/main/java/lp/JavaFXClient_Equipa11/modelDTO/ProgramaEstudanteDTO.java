package lp.JavaFXClient_Equipa11.modelDTO;
/**
 * @author diogo garcia
 */
import java.time.LocalDate;

public class ProgramaEstudanteDTO {

    private Long id;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private int horasFeitas;
    private boolean emitirDiploma;

    public ProgramaEstudanteDTO() {}

    public ProgramaEstudanteDTO(Long id, LocalDate dataInicio, LocalDate dataFim, int horasFeitas, boolean emitirDiploma) {
        this.id = id;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.horasFeitas = horasFeitas;
        this.emitirDiploma = emitirDiploma;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }

    public LocalDate getDataFim() { return dataFim; }
    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }

    public int getHorasFeitas() { return horasFeitas; }
    public void setHorasFeitas(int horasFeitas) { this.horasFeitas = horasFeitas; }

    public boolean isEmitirDiploma() { return emitirDiploma; }
    public void setEmitirDiploma(boolean emitirDiploma) { this.emitirDiploma = emitirDiploma; }
}//fim classe
