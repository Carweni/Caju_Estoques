import java.io.Serializable;

public class Movimentacao implements Serializable{
    private String tipo;
    private int quantia;
    private Produto produto;
    private Gerente responsavel;
    private int ID;

    public Movimentacao(String tipo, int quantia, Produto produto, Gerente responsavel, int ID){
        this.tipo = tipo;
        this.quantia = quantia;
        this.produto = produto;
        this.responsavel = responsavel;
        this.ID = ID;
    }

    public int getId(){
        return ID;
    }

    public String getTipo(){
        return this.tipo;
    }

    public int getQuantia(){
        return this.quantia;
    }

    public Produto getProduto(){
        return this.produto;
    }

    public Gerente getGerente(){
        return this.responsavel;
    }
}