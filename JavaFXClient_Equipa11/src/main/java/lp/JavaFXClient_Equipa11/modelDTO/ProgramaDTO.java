package lp.JavaFXClient_Equipa11.modelDTO;

/**
 * @author miguel silva
 */

public class ProgramaDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private int horasServico;
    private String local;
    private int vagas;
    private int vagasOcupadas;

    public ProgramaDTO() {}

    public ProgramaDTO(Long id, String titulo, String descricao, int horasServico, String local, int vagas, int vagasOcupadas) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.horasServico = horasServico;
        this.local = local;
        this.vagas = vagas;
        this.vagasOcupadas = vagasOcupadas;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public int getHorasServico() { return horasServico; }
    public void setHorasServico(int horasServico) { this.horasServico = horasServico; }

    public String getLocal() { return local; }
    public void setLocal(String local) { this.local = local; }

    public int getVagas() { return vagas; }
    public void setVagas(int vagas) { this.vagas = vagas; }

    public int getVagasOcupadas() { return vagasOcupadas; }
    public void setVagasOcupadas(int vagasOcupadas) { this.vagasOcupadas = vagasOcupadas; }
}//fim classe
