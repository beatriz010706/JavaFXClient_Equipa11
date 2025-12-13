package lp.JavaFXClient_Equipa11.modelDTO;
/**
 * @author beatriz silva
 */
public class EstudanteDTO {

    private Long id;
    private String nome;
    private String email;
    private String password;
    private String curso;
    private int numeroAluno;

    private int totalCandidaturas;
    private int programasConcluidos;

    public EstudanteDTO() {}

    public EstudanteDTO(Long id, String nome, String email, String password,
                        String curso, int numeroAluno,
                        int totalCandidaturas, int programasConcluidos) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.curso = curso;
        this.numeroAluno = numeroAluno;
        this.totalCandidaturas = totalCandidaturas;
        this.programasConcluidos = programasConcluidos;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }

    public int getNumeroAluno() { return numeroAluno; }
    public void setNumeroAluno(int numeroAluno) { this.numeroAluno = numeroAluno; }

    public int getTotalCandidaturas() { return totalCandidaturas; }
    public void setTotalCandidaturas(int totalCandidaturas) { this.totalCandidaturas = totalCandidaturas; }

    public int getProgramasConcluidos() { return programasConcluidos; }
    public void setProgramasConcluidos(int programasConcluidos) { this.programasConcluidos = programasConcluidos; }
}//fim classe
