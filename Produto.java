public class Produto {
    private String nome;
    private String categoria;
    private double price;
    private double cost;
    private int minCapacity;
    private int maxCapacity;
    private int qtdEstoque;
    private Fornecedor fornecedor;
    private int ID;

    public Produto(String nome, int id, String categoria, double price, double cost, int minCapacity, int maxCapacity, Fornecedor fornecedor) {
        this.nome = nome;
        this.ID = id;
        this.categoria = categoria;
        this.price = price;
        this.cost = cost;
        this.minCapacity = minCapacity;
        this.maxCapacity = maxCapacity;
        this.qtdEstoque = 0;
        this.fornecedor = fornecedor;
    }

    public int getId(){
        return ID;
    }

    public String getNome(){
        return nome;
    }

    public int getQtdEstoque(){
        return qtdEstoque;
    }
}