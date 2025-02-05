public class Movimentacao {
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
}