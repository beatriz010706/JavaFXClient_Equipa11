package lp.JavaFXClient_Equipa11.modelDTO;
/**
 * @author beatriz silva
 */
import java.util.List;

public class ParceiroDTO {

    private Long id;
    private String nome;
    private String email;
    private String password;
    private String local;
    private String tipo;
    private List<String> programas; // Lista de títulos dos programas

    public ParceiroDTO() {}

    public ParceiroDTO(Long id, String nome, String email, String password, String local, List<String> programas) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.local = local;
        this.programas = programas;
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

    public String getLocal() { return local; }
    public void setLocal(String local) { this.local = local; }

    public List<String> getProgramas() { return programas; }
    public void setProgramas(List<String> programas) { this.programas = programas; }
    
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

}//fim classe

