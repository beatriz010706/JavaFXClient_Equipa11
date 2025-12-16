package lp.JavaFXClient_Equipa11.modelDTO;


/**
 * @author miguel silva
 */

public class ProgramaVoluntariadoDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private int horasServico;
    private String local;
    private int vagas;
    private int vagasOcupadas;

    public ProgramaVoluntariadoDTO() {}

    public ProgramaVoluntariadoDTO(Long id, String titulo, String descricao, int horasServico, String local, int vagas, int vagasOcupadas) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.horasServico = horasServico;
        this.local = local;
        this.vagas = vagas;
        this.vagasOcupadas = vagasOcupadas;
    }
 // CORREÇÃO: construtor que recebe a entidade ProgramaVoluntariado
    // Necessário para conversão Entity -> DTO no service (stream.map)
    public ProgramaVoluntariadoDTO(ProgramaVoluntariadoDTO programa) {
        this.id = programa.getId();
        this.titulo = programa.getTitulo();
        this.descricao = programa.getDescricao();
        this.horasServico = programa.getHorasServico();
        this.local = programa.getLocal();
        this.vagas = programa.getVagas();
        this.vagasOcupadas = programa.getVagasOcupadas();
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

	public void setTipo(String string) {
		// TODO Auto-generated method stub
		
	}
}//fim classe
