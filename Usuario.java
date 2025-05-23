import java.io.Serializable;

public class Usuario implements Serializable{
    private String nome;
    private int id;
    private String cargo;
    private String senha;

    public Usuario(String nome, int id, String cargo, String senha) {
        this.nome = nome;
        this.id = id;
        this.cargo = cargo;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getSenha() {
        return senha;
    }

    protected void setSenha(String senha) {
        this.senha = senha;
    }
}