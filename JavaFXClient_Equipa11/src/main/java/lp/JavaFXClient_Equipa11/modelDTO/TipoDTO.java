package lp.JavaFXClient_Equipa11.modelDTO;
/**
 * @author diogo garcia
 */
public class TipoDTO {

    private Long idTipo;
    private String descricaoTipo;

    public TipoDTO() {}

    public TipoDTO(Long idTipo, String descricaoTipo) {
        this.idTipo = idTipo;
        this.descricaoTipo = descricaoTipo;
    }

    public Long getIdTipo() { return idTipo; }
    public void setIdTipo(Long idTipo) { this.idTipo = idTipo; }

    public String getDescricaoTipo() { return descricaoTipo; }
    public void setDescricaoTipo(String descricaoTipo) { this.descricaoTipo = descricaoTipo; }
}//fim classe
