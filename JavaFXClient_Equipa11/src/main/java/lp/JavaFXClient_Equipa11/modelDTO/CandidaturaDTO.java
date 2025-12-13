package lp.JavaFXClient_Equipa11.modelDTO;
/**
 * DTO simples para Candidatura
 */
public class CandidaturaDTO {

    private Long id;
    private String dataSubmissao;
    private Long estudanteId;
    private Long programaId;
    private Estado estado;

    public CandidaturaDTO() {}

    public CandidaturaDTO(Long id, String dataSubmissao, Long estudanteId, Long programaId, Estado estado) {
        this.id = id;
        this.dataSubmissao = dataSubmissao;
        this.estudanteId = estudanteId;
        this.programaId = programaId;
        this.estado = estado;
    }

    // Getters / Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDataSubmissao() { return dataSubmissao; }
    public void setDataSubmissao(String dataSubmissao) { this.dataSubmissao = dataSubmissao; }

    public Long getEstudanteId() { return estudanteId; }
    public void setEstudanteId(Long estudanteId) { this.estudanteId = estudanteId; }

    public Long getProgramaId() { return programaId; }
    public void setProgramaId(Long programaId) { this.programaId = programaId; }

    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }
}//fim classe
