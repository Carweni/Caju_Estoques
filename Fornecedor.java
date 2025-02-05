import java.io.Serializable;

public class Fornecedor implements Serializable{
    private String nome;
    private int id;
    private String contato;
    
    public Fornecedor(String nome, int id, String contato) {
        this.nome = nome;
        this.id = id;
        this.contato=contato;
    }
    
    public int getId(){
        return this.id;
    }

    public String getNome(){
        return this.nome;
    }

    public String getContato(){
        return this.contato;
    }
}

