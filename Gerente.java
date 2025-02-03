import java.util.Scanner;

public class Gerente extends Usuario {
    private boolean[] permissao;

    public Gerente(String nome, int id, String cargo, String senha, boolean[] permissao) {
        super(nome, id, cargo, senha);
        this.permissao = permissao;
    }

    public boolean[] getPermissao() {
        return permissao;
    }

    public void setPermissao(boolean[] permissao) {
        this.permissao = permissao;
    }

    public void exibirMenuGerente() {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        
        do {
            System.out.println("\nMenu:");
            if(this.permissao[0]){
                System.out.println( "1. Cadastrar Fornecedor");
                System.out.println("2. Remover Fornecedor");
            }
            if(this.permissao[1]){
                System.out.println("3. Cadastrar Produto"); 
                System.out.println("4. Editar Produto"); 
                System.out.println("5. Remover Produto"); 
            }
            if(this.permissao[2]){
                System.out.println("Registrar Entrada de Produto"); 
                System.out.println("Registrar Saída de Produto");
            }
            System.out.println("Gerar Relatório de Movimentações");
            System.out.println("Gerar Relatório de Estoque");
            System.out.println("Gerar Relatório de Sugestão de Compra");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    if(permissao[0]){
                        cadastrarFornecedor();
                    }
                    break;
                case 2:
                    if(permissao[0]){
                        removerFornecedor();
                    }
                    break;
                case 3:
                    if(permissao[1]){
                        cadastrarProduto();
                    }
                    break;
                case 4:
                    if(permissao[1]){
                        removerProduto();
                    }
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 0:
                    System.out.println("Saindo do sistema.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    public void cadastrarFornecedor(){
        Scanner scanner = new Scanner(System.in);
        boolean IdExist;

        System.out.println("\nCadastrar Fornecedor:");

        System.out.print("Digite o nome do Fornecedor: ");
        String nome = scanner.nextLine();

        int id;
        do{
            System.out.print("Digite o ID do Fornecedor: ");
            id = scanner.nextInt();
            IdExist=false;
            for(int i=0; i<Sistema.fornecedores.size();i++){
                if(Sistema.fornecedores.get(i).getId()==id){
                    IdExist=true;
                    System.out.println("ID de fornecedor já existente no sistema, insira outro id: ");
                    break;
                }
            };
        }while(IdExist);
        scanner.nextLine(); 
        
        System.out.print("Digite o contato do Fornecedor: ");
        String contato = scanner.nextLine();

        Sistema.fornecedores.add(new Fornecedor(nome, id, contato));
        System.out.println("Fornecedor cadastrado com sucesso.");
    }

    // FAZER PRODUTO N RECEBER MAIS ENTRADAS SE FORNECEDOR N EXISTIR MAIS
    //IDEIA : FAZER N PODER CADASTRAR COM ID 0 E DEIXAR COMO OPÇÃO PRA SÓ CANCELAR REMOÇÃO, N SEI
    // FAZER SE N TIVER FORNECEDOR ELE CANCELAR AÇÃO DIRETO
    private void removerFornecedor() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Lista de Fornecedores:");
        listarFornecedores();

        System.out.print("Digite o ID do Fornecedor para remover: ");
        int id = scanner.nextInt();
        Fornecedor fornecedor = encontrarFornecedorPorId(id);

        if (fornecedor != null) {
            Sistema.fornecedores.remove(fornecedor);
            System.out.println("Fornecedor removido com sucesso.");
        } else {
            System.out.println("Fornecedor não encontrado.");
        }
    }

    public void listarFornecedores() {
        for(int i=0; i<Sistema.fornecedores.size();i++){
            Fornecedor fornecedor = Sistema.fornecedores.get(i);
            System.out.print(fornecedor.getId()+ "- ");
            System.out.println(fornecedor.getNome());
        }
    }

    private Fornecedor encontrarFornecedorPorId(int id) {
        for (Fornecedor fornecedor : Sistema.fornecedores) {
            if (fornecedor.getId() == id) {
                return fornecedor;
            }
        }
        return null;
    }

    public void cadastrarProduto(){
        Scanner scanner = new Scanner(System.in);
        boolean IdExist;

        System.out.println("\nCadastrar Produto:");

        System.out.print("Digite o nome do Produto: ");
        String nome = scanner.nextLine();

        int id;
        do{
            System.out.print("Digite o ID do Produto: ");
            id = scanner.nextInt();
            IdExist=false;
            for(int i=0; i<Sistema.products.size();i++){
                if(Sistema.products.get(i).getId()==id){
                    IdExist=true;
                    System.out.println("ID de produto já existente no sistema, insira outro id: ");
                    break;
                }
            };
        }while(IdExist);
        scanner.nextLine(); 
        
        System.out.print("Digite a categoria do produto: ");
        String categoria = scanner.nextLine();

        System.out.print("Digite o preço de venda do produto: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Digite o custo de compra do produto: ");
        double cost = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Digite o estoque minimo desejado do produto: ");
        int minCapacity = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite o estoque máximo do produto: ");
        int maxCapacity = scanner.nextInt();
        scanner.nextLine();

        //PRA FAZER - N DEIXAR CADASTRAR COM FORNECEDOR VAZIO (ID Q N TEM FORNECEDOR)
        System.out.print("Qual o fornecedor do produto: ");
        listarFornecedores();
        System.out.println("Selecione o ID do fornecedor:");
        int fornecedorID = scanner.nextInt();
        scanner.nextLine();

        Fornecedor fornecedor = encontrarFornecedorPorId(fornecedorID);
        Produto produto = new Produto(nome, id, categoria, price, cost, minCapacity, maxCapacity, fornecedor);

        Sistema.products.add(produto);
        System.out.println("Produto cadastrado com sucesso.");
    }
    
    //PRA FAZER - FAZER SE N TIVER PRODUTO ELE CANCELAR AÇÃO DIRETO
    private void removerProduto() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Lista de Produtos:");
        listarProdutos();

        System.out.print("Digite o ID do Produto para remover: ");
        int id = scanner.nextInt();
        Produto produto = encontrarProdutoPorId(id);

        //FAZER: PEDIR CONFIRMAÇÃO PARA REMOVER
        if (produto != null) {
            if(produto.getQtdEstoque() == 0){
                Sistema.products.remove(produto);
                System.out.println("Produto removido com sucesso.");
            }else{
                System.out.println("Ainda há unidades em estoque, remova antes de deletar o produto.");
            }
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    public void listarProdutos() {
        for(int i=0; i<Sistema.products.size();i++){
            Produto produto = Sistema.products.get(i);
            System.out.print(produto.getId()+ "- ");
            System.out.println(produto.getNome());
        }
    }

    private Produto encontrarProdutoPorId(int id) {
        for (Produto produto : Sistema.products) {
            if (produto.getId() == id) {
                return produto;
            }
        }
        return null;
    }
}