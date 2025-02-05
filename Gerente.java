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

    public void exibirMenuGerente(Scanner scanner) {
    int opcao = -1;
    
    do {
        System.out.println("\nMenu:");
        int contadorOpcoes = 1; 

        if (this.permissao[0]) {
            System.out.println(contadorOpcoes + ". Cadastrar Fornecedor");
            contadorOpcoes++;
            System.out.println(contadorOpcoes + ". Remover Fornecedor");
            contadorOpcoes++;
        }
        if (this.permissao[1]) {
            System.out.println(contadorOpcoes + ". Cadastrar Produto");
            contadorOpcoes++;
            System.out.println(contadorOpcoes + ". Editar Produto");
            contadorOpcoes++;
            System.out.println(contadorOpcoes + ". Remover Produto");
            contadorOpcoes++;
        }
        if (this.permissao[2]) {
            System.out.println(contadorOpcoes + ". Registrar Entrada de Produto");
            contadorOpcoes++;
            System.out.println(contadorOpcoes + ". Registrar Saída de Produto");
            contadorOpcoes++;
        }
        System.out.println(contadorOpcoes + ". Consultar produto");
        contadorOpcoes++;
        System.out.println(contadorOpcoes + ". Gerar Relatório de Movimentações");
        contadorOpcoes++;
        System.out.println(contadorOpcoes + ". Gerar Relatório de Estoque");
        contadorOpcoes++;
        System.out.println(contadorOpcoes + ". Gerar Relatório de Sugestão de Compra");
        contadorOpcoes++;
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");

        try {
            opcao = scanner.nextInt();
            scanner.nextLine(); 

            int opcaoMapeada = 0;
            if (opcao != 0) {
                if (this.permissao[0] && opcao <= 2) {
                    opcaoMapeada = opcao;
                } else if (this.permissao[1] && opcao <= 5) {
                    opcaoMapeada = opcao + 2;
                } else if (this.permissao[2] && opcao <= 7) {
                    opcaoMapeada = opcao + 5;
                } else if (opcao <= 11) {
                    opcaoMapeada = opcao + 7;
                } else {
                    System.out.println("Opção inválida. Tente novamente.");
                    continue;
                }
            }

            switch (opcaoMapeada) {
                case 1:
                    cadastrarFornecedor();
                    break;
                case 2:
                    removerFornecedor();
                    break;
                case 3:
                    cadastrarProduto();
                    break;
                case 4:
                    editarProduto();
                    break;
                case 5:
                    removerProduto();
                    break;
                case 6:
                    entradaProduto();
                    break;
                case 7:
                    saidaProduto();
                    break;
                case 8:
                    consultarProduto();
                    break;
                case 9:
                    //gerarRelatorioMovimentacoes();
                    break;
                case 10:
                    //gerarRelatorioEstoque();
                    break;
                case 11:
                    //gerarRelatorioSugestaoCompra();
                    break;
                case 0:
                    System.out.println("Saindo do sistema.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } catch (java.util.InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, digite um número.");
            scanner.nextLine(); 
        }
    } while (opcao != 0);
}
    //FALTA ARMAZENAR INFOS
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
    // IDEIA : FAZER N PODER CADASTRAR COM ID 0 E DEIXAR COMO OPÇÃO PRA SÓ CANCELAR REMOÇÃO, N SEI
    private void removerFornecedor() {
        if(Sistema.fornecedores.size()==0){
            System.out.println("Não há fornecedores registrados.");
        } else {
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
    }
    // FAZER LISTAR BONITO
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

    //ARMAZENAR INFOS
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
    
    // FALTA A MENSAGEM DE CONFIRMAÇÃO
    private void removerProduto() {
        if(Sistema.products.size()==0){
            System.out.println("Não há produtos cadastrados.");
        }else{
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
    }
    //FAZER PODER CANCELAR DEPOIS DE ESCOLHER EDITAR
    //SE PUDER CANCELAR, FAZER PEDIR DENOVO SE PRODUTO NÃO EXISTIR
    public void editarProduto() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Lista de produtos: ");
        listarProdutos();
        
        boolean IdExist;
        int id, opcao;
        do{
            System.out.print("Digite o ID do produto a ser editado: ");
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
        
        Produto produto = encontrarProdutoPorId(id);

        //FAZER MOSTRAR PRODUTO
        do {
            System.out.println("\nInforme um campo a ser alterado:");
            System.out.println("1. ID");
            System.out.println("2. NOME");
            System.out.println("3. CATEGORIA");
            System.out.println("4. PREÇO DE VENDA");
            System.out.println("5. CUSTO DE COMPRA");
            System.out.println("6. ESTOQUE MÍNIMO DESEJADO");
            System.out.println("7. CAPACIDADE MÁXIMA DE ESTOQUE");
            System.out.println("8. FORNECEDOR");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            //fazer poder cancelar?
            //fazer n poder editar e colocar o mesmo nome?
            switch (opcao) {
                case 1:
                    int novoID;
                    do{
                        System.out.print("Digite o novo ID do Produto: ");
                        novoID = scanner.nextInt();
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

                    produto.setID(novoID);
                    break;
                case 2:
                    System.out.println("Informe o novo nome do produto: ");
                    String novoNome = scanner.nextLine();
                    produto.setNome(novoNome);
                    break;
                case 3:
                    System.out.println("Informe a nova categoria do produto: ");
                    String novaCat = scanner.nextLine();
                    produto.setCategoria(novaCat);
                    break;
                case 4:
                    System.out.println("Informe o novo preço de venda do produto: ");
                    Double novoPreco = scanner.nextDouble();
                    scanner.nextLine();
                    produto.setPrice(novoPreco);
                    break;
                case 5:
                    System.out.println("Informe o novo custo de compra do produto: ");
                    Double novoCost = scanner.nextDouble();
                    scanner.nextLine();
                    produto.setCost(novoCost);
                    break;
                case 6:
                    System.out.println("Informe o novo estoque mínimo desejado do produto: ");
                    int novoMinCapacity = scanner.nextInt();
                    scanner.nextLine();
                    produto.setMinCapacity(novoMinCapacity);
                    break;
                case 7:
                    System.out.println("Informe a nova capacidade máxima de estoque do produto: ");
                    int novoMaxCapacity = scanner.nextInt();
                    scanner.nextLine();
                    produto.setMaxCapacity(novoMaxCapacity);
                    break;
                case 8:
                    int novoFornecedorID;
                    System.out.print("Digite o novo ID de fornecedor: ");
                    novoFornecedorID = scanner.nextInt();
                    scanner.nextLine();
                    Fornecedor novoFornecedor = encontrarFornecedorPorId(novoFornecedorID);
                    if(novoFornecedor!=null){
                        produto.setFornecedor(novoFornecedor);
                    }else{
                        System.out.println("Fornecedor não encontrado.");
                    }
                    break;
                case 0:
                    System.out.println("Voltando.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }
    // ARRUMAR OQ APARECE
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

    //VE COMO VAI FAZER A BUSCA A PARTIR DE QUALQUER UM DOS ELEMENTOS OU TIRAR ISSO DO DOCS
    public void consultarProduto(){
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\nOpções:");
            System.out.println("1. Consultar todos os produtos");
            System.out.println("2. Consultar produto pelo ID");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    listarProdutos();
                    break;
                case 2:
                    System.out.print("Digite o ID do Produto: ");
                    int id = scanner.nextInt();
                    Produto produto = encontrarProdutoPorId(id);
            
                    //FAZER: PEDIR CONFIRMAÇÃO PARA REMOVER
                    if (produto != null) {
                        produto.mostrarProduto();
                    } else {
                        System.out.println("Produto não encontrado.");
                    }
                    break;
                case 0:
                    System.out.println("Voltar.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }
    //fazer poder cancelar
    //tem q testar
    public void entradaProduto(){
        Scanner scanner = new Scanner(System.in);

        boolean IdExist;
        int id;
        do{
            System.out.print("Informe o ID do produto: ");
            id = scanner.nextInt();
            IdExist=false;
            for(int i=0; i<Sistema.products.size();i++){
                if(Sistema.products.get(i).getId()==id){
                    IdExist=true;
                    break;
                }
            };
            if(!IdExist){
                System.out.println("Não há produto com esse ID cadastrados, insira outro ID: ");
            }
        }while(!IdExist);
        scanner.nextLine(); 
        Produto produto = encontrarProdutoPorId(id);

        boolean validqtde;
        int qtde;
        do{
            System.out.println("Informe a quantidade a ser adicionada ao estoque:");
            qtde = scanner.nextInt();
            scanner.nextLine();
            if(qtde+produto.getQtdEstoque()>produto.getMaxCapacity()){
                validqtde = false;
                int maxEntrada = produto.getMaxCapacity() - produto.getQtdEstoque();
                System.out.println("A quantidade informada excede a capacidade máxima de estoque. O valor máximo para isso não ocorrer é "+ maxEntrada);
            } else {
                validqtde = true;
            }
        }while (!validqtde);
        
        Movimentacao mov = Sistema.movimentacoes.get(Sistema.movimentacoes.size()-1);
        Movimentacao movimentacao = new Movimentacao("entrada", qtde, produto, this, mov.getId());
    }
    //fazer poder cancelar
    //tem que testar
    public void saidaProduto(){
        Scanner scanner = new Scanner(System.in);

        boolean IdExist;
        int id;
        do{
            System.out.print("Informe o ID do produto: ");
            id = scanner.nextInt();
            IdExist=false;
            for(int i=0; i<Sistema.products.size();i++){
                if(Sistema.products.get(i).getId()==id){
                    IdExist=true;
                    break;
                }
            };
            if(!IdExist){
                System.out.println("Não há produto com esse ID cadastrados, insira outro ID: ");
            }
        }while(!IdExist);
        scanner.nextLine(); 
        Produto produto = encontrarProdutoPorId(id);

        boolean validqtde;
        int qtde;
        do{
            System.out.println("Informe a quantidade a ser removida do estoque:");
            qtde = scanner.nextInt();
            scanner.nextLine();
            if(qtde>produto.getQtdEstoque()){
                validqtde = false;
                int maxEntrada = produto.getQtdEstoque();
                System.out.println("Há apenas "+ maxEntrada + " deste produto em estoque. Informe um valor menor: ");
            } else {
                if(produto.getQtdEstoque()-qtde<produto.getMinCapacity()){
                    System.out.println("A quantidade em estoque será menor que a quantidade mínima desejada. Deseja proceguir (s/n)?");
                    char op = scanner.next().charAt(0);
                    scanner.nextLine();
                    if(op == 's'){
                        validqtde = true;
                    } else {
                        validqtde = false;
                    }
                } else {
                    validqtde = true;
                }
            }
        }while (!validqtde);
        
        Movimentacao mov = Sistema.movimentacoes.get(Sistema.movimentacoes.size()-1);
        Movimentacao movimentacao = new Movimentacao("saida", qtde, produto, this, mov.getId());
    }
}