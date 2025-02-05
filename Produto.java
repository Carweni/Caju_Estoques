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

    public int getMaxCapacity(){
        return maxCapacity;
    }

    public int getMinCapacity(){
        return minCapacity;
    }

    public Fornecedor getFornecedor(){
        return this.fornecedor;
    }

    public void setID(int id){
        this.ID = id;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setCategoria(String categoria){
        this.categoria = categoria;
    }

    public void setPrice(Double price){
        this.price = price;
    }

    public void setCost(Double cost){
        this.cost = cost;
    }

    public void setMinCapacity(int minCapacity){
        this.minCapacity=minCapacity;
    }

    public void setMaxCapacity(int maxCapacity){
        this.maxCapacity=maxCapacity;
    }

    public void setFornecedor(Fornecedor fornecedor){
        this.fornecedor=fornecedor;
    }

    //fazer imprimir todos os atributos
    public void mostrarProduto(){
        System.out.println(ID + ' ' + nome + ' ');
    }
}