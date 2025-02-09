import java.util.InputMismatchException;
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
    
    //testar todas as possibilidades de menu
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
                    if (this.permissao[0] && this.permissao[1] && this.permissao[2]) { //SSS
                        opcaoMapeada = opcao;
                    } else if (this.permissao[0] && !this.permissao[1]  && !this.permissao[2]) {  // SNN
                        if(opcao <= 2){
                            opcaoMapeada = opcao;
                        }
                        else{
                            opcaoMapeada = opcao + 5;
                        }
                    } else if (!this.permissao[0] && this.permissao[1]  && !this.permissao[2]) { // NSN
                        if(opcao <= 3){
                            opcaoMapeada = opcao + 2;
                        }
                        else{
                            opcaoMapeada = opcao + 4;
                        }
                    } else if (!this.permissao[0] && !this.permissao[1] && this.permissao[2]) { // NNS
                        opcaoMapeada = opcao + 5;
                    } else if (this.permissao[0] && !this.permissao[1]  && this.permissao[2]) { // SNS
                        if(opcao <= 2 ){
                            opcaoMapeada = opcao;
                        }
                        else{
                            opcaoMapeada = opcao + 3;
                        }
                    } else if (this.permissao[0] && this.permissao[1]  && !this.permissao[2]) { // SSN
                        if(opcao < 6){
                            opcaoMapeada = opcao;
                        }
                        else{
                            opcaoMapeada = opcao + 2;
                        }
                    } else if (!this.permissao[0] && this.permissao[1]  && this.permissao[2]) { // NSS
                        opcaoMapeada = opcao + 2;
                    }else if (!this.permissao[0] && !this.permissao[1] && !this.permissao[2]) { // NNN
                        opcaoMapeada = opcao + 7;
                    } else {
                        System.out.println("Opção inválida. Tente novamente.");
                        continue;
                    }
                }

                switch (opcaoMapeada) {
                    case 1:
                        cadastrarFornecedor(scanner);
                        break;
                    case 2:
                        removerFornecedor(scanner);
                        break;
                    case 3:
                        cadastrarProduto(scanner);
                        break;
                    case 4:
                        editarProduto(scanner);
                        break;
                    case 5:
                        removerProduto(scanner);
                        break;
                    case 6:
                        entradaProduto(scanner);
                        break;
                    case 7:
                        saidaProduto(scanner);
                        break;
                    case 8:
                        consultarProduto(scanner);
                        break;
                    case 9:
                        gerarRelatorioMovimentacoes();
                        break;
                    case 10:
                        gerarRelatorioEstoque(scanner);
                        break;
                    case 11:
                        gerarRelatorioSugestaoCompra();
                        break;
                    case 0:
                        limparConsole();
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

    public void cadastrarFornecedor(Scanner scanner){
        
        boolean IdExist = true;

        System.out.println("\nCadastrar Fornecedor:");

        System.out.print("Digite o nome do Fornecedor: ");
        String nome = scanner.nextLine();

        int id = -1;
        do{
            try{
                do{
                    System.out.print("Digite o ID do Fornecedor: ");
                    id = scanner.nextInt();
    
                    if(id < 1){
                        System.out.println("ID inválido. Insira outro número, positivo e diferente de 0. ");
                    }
                }while(id <1);
                IdExist=false;
                for(int i=0; i<Sistema.fornecedores.size();i++){
                    if(Sistema.fornecedores.get(i).getId()==id){
                        IdExist=true;
                        System.out.println("ID de fornecedor já existente no sistema, insira outro id: ");
                        break;
                    }
                };
            } catch(InputMismatchException e) {
                System.out.println("Entrada inválida! Digite um número válido.");
                scanner.nextLine(); 
            }
        }while(IdExist);
        scanner.nextLine(); 
        
        System.out.print("Digite o contato do Fornecedor: ");
        String contato = scanner.nextLine();

        Sistema.fornecedores.add(new Fornecedor(nome, id, contato));
        Sistema.salvarFornecedores();
        limparConsole();
        System.out.println("Fornecedor cadastrado com sucesso.");
    }

    private void removerFornecedor(Scanner scanner) {
        limparConsole();
        if(Sistema.fornecedores.size()==0){
            System.out.println("Não há fornecedores registrados.");
        } else {
            System.out.println("Lista de Fornecedores:");
            listarFornecedores();
            Fornecedor fornecedor = null;
            boolean idLido = false;
        
            do{
                try{
                    System.out.print("Digite o ID do Fornecedor para remover: ");
                    int id = scanner.nextInt();
                    fornecedor = encontrarFornecedorPorId(id);
                    idLido = true;
                    scanner.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida! Digite um número válido.");
                    scanner.nextLine(); 
                }
            }while(!idLido);
    
            
    
            if (fornecedor != null) {
                System.out.println("Você deseja confirmar a remoção de " + fornecedor.getNome() + "? (s/n)");
                char confirmacao = scanner.next().charAt(0);
                
                if (confirmacao == 's' || confirmacao == 'S'){
                    Sistema.fornecedores.remove(fornecedor);
                    Sistema.salvarFornecedores();
                    limparConsole();
                    System.out.println("Fornecedor removido com sucesso.");
                }else{
                    limparConsole();
                    System.out.println("Operação cancelada.");
                }
            } else {
                limparConsole();
                System.out.println("Fornecedor não encontrado.");
            }
        }
    }

    public void listarFornecedores() {
        for(int i=0; i<Sistema.fornecedores.size();i++){
            Fornecedor fornecedor = Sistema.fornecedores.get(i);
            System.out.print(fornecedor.getId()+ " - ");
            System.out.println(fornecedor.getNome());
            System.out.println("Contato: " + fornecedor.getContato() + "\n");
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

    public void cadastrarProduto(Scanner scanner){
        limparConsole();
        boolean IdExist = true;
        boolean valorLido = false;

        System.out.println("\nCadastrar Produto:");

        System.out.print("Digite o nome do Produto: ");
        String nome = scanner.nextLine();

        int id = -1;
        do{
            try{
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
            } catch(InputMismatchException e) {
                System.out.println("Entrada inválida! Digite um número válido.");
                scanner.nextLine(); 
            }
        }while(IdExist);
        scanner.nextLine(); 
        
        System.out.print("Digite a categoria do produto: ");
        String categoria = scanner.nextLine();

        double price = -1;
        do{
            try{
                System.out.print("Digite o preço de venda do produto: ");
                price = scanner.nextDouble();
                scanner.nextLine();
                valorLido = true;
            } catch(InputMismatchException e) {
                System.out.println("Entrada inválida! Digite um número válido.");
                scanner.nextLine(); 
            }
        } while(!valorLido);
        valorLido = false;

        double cost = -1;
        do{
            try{
                System.out.print("Digite o custo de compra do produto: ");
                cost = scanner.nextDouble();
                scanner.nextLine();
                valorLido = true;
            } catch(InputMismatchException e) {
                System.out.println("Entrada inválida! Digite um número válido.");
                scanner.nextLine(); 
            }
        } while(!valorLido);
        valorLido = false;

        int minCapacity = -1;
        do{
            try{
                System.out.print("Digite o estoque minimo desejado do produto: ");
                minCapacity = scanner.nextInt();
                scanner.nextLine();
                valorLido = true;
            } catch(InputMismatchException e) {
                System.out.println("Entrada inválida! Digite um número válido.");
                scanner.nextLine(); 
            }
        } while(!valorLido);
        valorLido = false;

        int maxCapacity = -1;
        do{
            try{
                System.out.print("Digite o estoque máximo do produto: ");
                maxCapacity = scanner.nextInt();
                scanner.nextLine();
                valorLido = true;
            } catch(InputMismatchException e) {
                System.out.println("Entrada inválida! Digite um número válido.");
                scanner.nextLine(); 
            }
        } while(!valorLido);
        valorLido = false;

        System.out.println("Lista de Fornecedores:");
        listarFornecedores();
        Fornecedor fornecedor = null;
        boolean idLido = false;
    
        do{
            try{
                System.out.println("Selecione o ID do fornecedor:");
                int fornecedorID = scanner.nextInt();
                fornecedor = encontrarFornecedorPorId(fornecedorID);
                idLido = true;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Digite um número válido.");
                scanner.nextLine(); 
            }
        }while(!idLido);
        scanner.nextLine();
        if(fornecedor!=null){
            Produto produto = new Produto(nome, id, categoria, price, cost, minCapacity, maxCapacity, fornecedor);
    
            Sistema.products.add(produto);
            Sistema.salvarProdutos();
            limparConsole();
            System.out.println("Produto cadastrado com sucesso.");
        } else {
            limparConsole();
            System.out.println("Fornecedor não encontrado.");
        }
    }
    
    private void removerProduto(Scanner scanner) {
        if(Sistema.products.size()==0){
            System.out.println("Não há produtos cadastrados.");
        }else{
            System.out.println("Lista de Produtos:");
            listarProdutos();
            boolean idLido = false;
            Produto produto = null;
            do{
                try{
                    System.out.print("Digite o ID do Produto para remover: ");
                    int id = scanner.nextInt();
                    produto = encontrarProdutoPorId(id);
                    idLido = true;
                } catch(InputMismatchException e) {
                    System.out.println("Entrada inválida! Digite um número válido.");
                    scanner.nextLine(); 
                }
            } while(!idLido);
    
            if (produto != null) {
                if(produto.getQtdEstoque() == 0){
                    System.out.println("Deseja confirmar a remoção de " + produto.getNome() + "? (s/n)");
                    char confirmacao = scanner.next().charAt(0);

                    if(confirmacao == 's' || confirmacao == 'S'){
                        Sistema.products.remove(produto);
                        Sistema.salvarProdutos();
                        limparConsole();
                        System.out.println("Produto removido com sucesso.");
                    }else{
                        limparConsole();
                        System.out.println("Operação de remoção cancelada. ");
                    }
                    
                }else{
                    limparConsole();
                    System.out.println("Ainda há unidades em estoque, remova antes de deletar o produto.");
                }
            } else {
                limparConsole();
                System.out.println("Produto não encontrado.");
            }
        }
    }
    
    public void editarProduto(Scanner scanner) {
        if(Sistema.products.size()==0){
            System.out.println("Não há produtos cadastrados.");
        }else{
            System.out.println("Lista de produtos: ");
            listarProdutos();
            boolean idLido = false;
            Produto produto = null;

            do{
                try{
                    System.out.print("Digite o ID do Produto para editar: ");
                    int id = scanner.nextInt();
                    produto = encontrarProdutoPorId(id); 
                    idLido = true;
                } catch(InputMismatchException e) {
                    System.out.println("Entrada inválida! Digite um número válido.");
                    scanner.nextLine(); 
                    idLido = false;
                }
            }while(!idLido);

            int opcao = -1;

            if(produto != null){
                produto.mostrarProduto();
                do {
                    try{
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
                        scanner.nextLine();
                    } catch(InputMismatchException e) {
                        System.out.println("Entrada inválida! Digite um número válido.");
                        scanner.nextLine(); 
                    }

                    switch (opcao) {
                        case 1:
                            int novoID = -1;
                            boolean IdExist = true;
                            do{
                                try{
                                    System.out.print("Digite o novo ID do Produto: ");
                                    novoID = scanner.nextInt();
                                    IdExist=false;
                                    for(int i=0; i<Sistema.products.size();i++){
                                        if(Sistema.products.get(i).getId()==novoID){
                                            IdExist=true;
                                            System.out.println("ID de produto já existente no sistema, insira outro id: ");
                                            break;
                                        }
                                    };
                                } catch(InputMismatchException e) {
                                    System.out.println("Entrada inválida! Digite um número válido.");
                                    scanner.nextLine(); 
                                }
                            }while(IdExist);
                            scanner.nextLine();

                            System.out.println("Deseja confirmar alteração de ID? (s/n)");
                            char confirmacao = scanner.next().charAt(0);
                            scanner.nextLine();

                            if(confirmacao == 's' || confirmacao == 'S'){
                                produto.setID(novoID);
                                limparConsole();
                                System.out.println("O ID foi alterado com sucesso. ");
                            }else{
                                limparConsole();
                                System.out.println("Operação cancelada. ");
                            }

                            break;
                        case 2:
                            System.out.println("Informe o novo nome do produto: ");
                            String novoNome = scanner.nextLine();
                            
                            System.out.println("Deseja confirmar alteração de nome? (s/n)");
                            confirmacao = scanner.next().charAt(0);
                            scanner.nextLine();

                            if(confirmacao == 's' || confirmacao == 'S'){
                                produto.setNome(novoNome);
                                limparConsole();
                                System.out.println("O nome foi alterado com sucesso. ");
                            }else{
                                limparConsole();
                                System.out.println("Operação cancelada. ");
                            }

                            break;
                        case 3:
                            System.out.println("Informe a nova categoria do produto: ");
                            String novaCat = scanner.nextLine();

                            System.out.println("Deseja confirmar alteração de categoria? (s/n)");
                            confirmacao = scanner.next().charAt(0);
                            scanner.nextLine();

                            if(confirmacao == 's' || confirmacao == 'S'){
                                produto.setCategoria(novaCat);
                                limparConsole();
                                System.out.println("A categoria foi alterado com sucesso. ");
                            }else{
                                limparConsole();
                                System.out.println("Operação cancelada. ");
                            }

                            break;
                        case 4:
                            boolean valorLido = false;
                            Double novoPreco = -1.0;
                            do{
                                try{
                                    System.out.println("Informe o novo preço de venda do produto: ");
                                    novoPreco = scanner.nextDouble();
                                    scanner.nextLine();
                                    valorLido = true;
                                } catch(InputMismatchException e) {
                                    System.out.println("Entrada inválida! Digite um número válido.");
                                    scanner.nextLine(); 
                                }
                            }while(!valorLido);

                            System.out.println("Deseja confirmar alteração de preço? (s/n)");
                            confirmacao = scanner.next().charAt(0);
                            scanner.nextLine();

                            if(confirmacao == 's' || confirmacao == 'S'){
                                produto.setPrice(novoPreco);
                                limparConsole();
                                System.out.println("O preço foi alterado com sucesso. ");
                            }else{
                                limparConsole();
                                System.out.println("Operação cancelada. ");
                            }
                            break;
                        case 5:
                            valorLido = false;
                            Double novoCost = -1.0;
                            do{
                                try{
                                    System.out.println("Informe o novo custo de compra do produto: ");
                                    novoCost = scanner.nextDouble();
                                    scanner.nextLine();
                                    valorLido = true;
                                } catch(InputMismatchException e) {
                                    System.out.println("Entrada inválida! Digite um número válido.");
                                    scanner.nextLine(); 
                                }
                            }while(!valorLido);

                            System.out.println("Deseja confirmar alteração de custo? (s/n)");
                            confirmacao = scanner.next().charAt(0);
                            scanner.nextLine();

                            if(confirmacao == 's' || confirmacao == 'S'){
                                produto.setCost(novoCost);
                                limparConsole();
                                System.out.println("O custo foi alterado com sucesso. ");
                            }else{
                                limparConsole();
                                System.out.println("Operação cancelada. ");
                            }
                            
                            break;
                        case 6:
                            valorLido = false;
                            int novoMinCapacity = -1;
                            do{
                                try{
                                    System.out.println("Informe o novo estoque mínimo desejado do produto: ");
                                    novoMinCapacity = scanner.nextInt();
                                    scanner.nextLine();
                                    valorLido = true;
                                } catch(InputMismatchException e) {
                                    System.out.println("Entrada inválida! Digite um número válido.");
                                    scanner.nextLine(); 
                                }
                            }while(!valorLido);

                            System.out.println("Deseja confirmar alteração da capacidade mínima desejada? (s/n)");
                            confirmacao = scanner.next().charAt(0);
                            scanner.nextLine();

                            if(confirmacao == 's' || confirmacao == 'S'){
                                produto.setMinCapacity(novoMinCapacity);
                                limparConsole();
                                System.out.println("A capacidade mínima foi alterada com sucesso. ");
                            }else{
                                limparConsole();
                                System.out.println("Operação cancelada. ");
                            }

                            break;
                        case 7:
                            valorLido = false;
                            int novoMaxCapacity = -1;
                            do{
                                try{
                                    System.out.println("Informe a nova capacidade máxima de estoque do produto: ");
                                    novoMaxCapacity = scanner.nextInt();
                                    scanner.nextLine();
                                    valorLido = true;
                                } catch(InputMismatchException e) {
                                    System.out.println("Entrada inválida! Digite um número válido.");
                                    scanner.nextLine(); 
                                }
                            }while(!valorLido);

                            System.out.println("Deseja confirmar alteração da capacidade máxima? (s/n)");
                            confirmacao = scanner.next().charAt(0);
                            scanner.nextLine();

                            if(confirmacao == 's' || confirmacao == 'S'){
                                produto.setMaxCapacity(novoMaxCapacity);
                                limparConsole();
                                System.out.println("A capacidade mínima foi alterada com sucesso. ");
                            }else{
                                limparConsole();
                                System.out.println("Operação cancelada. ");
                            }
                            break;
                        case 8:
                            valorLido = false;
                            int novoFornecedorID = -1;
                            do{
                                try{
                                    System.out.print("Digite o novo ID de fornecedor: ");
                                    novoFornecedorID = scanner.nextInt();
                                    scanner.nextLine();
                                    valorLido = true;
                                } catch(InputMismatchException e) {
                                    System.out.println("Entrada inválida! Digite um número válido.");
                                    scanner.nextLine(); 
                                }
                            }while(!valorLido);

                            Fornecedor novoFornecedor = encontrarFornecedorPorId(novoFornecedorID);
                            if(novoFornecedor!=null){
                                System.out.println("Deseja confirmar alteração de fornecedor? (s/n)");
                                confirmacao = scanner.next().charAt(0);
                                scanner.nextLine();

                                if(confirmacao == 's' || confirmacao == 'S'){
                                    produto.setFornecedor(novoFornecedor);
                                    limparConsole();
                                    System.out.println("O fornecedor foi alterada com sucesso. ");
                                }else{
                                    limparConsole();
                                    System.out.println("Operação cancelada. ");
                                }
                            }else{
                                System.out.println("Fornecedor não encontrado.");
                            }
                            break;
                        case 0:
                            limparConsole();
                            System.out.println("Voltando.");
                            break;
                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                    }
                } while (opcao != 0);
                Sistema.salvarProdutos();
            }else{
                System.out.println("Produto não encontrado. ");
            }
        }
    }
    
    // ARRUMAR OQ APARECE
    public void listarProdutos() {
        System.out.println("Produtos:");
        for(int i=0; i<Sistema.products.size();i++){
            Produto produto = Sistema.products.get(i);
            System.out.print(produto.getId() + " - ");
            System.out.println(produto.getNome());
            System.out.println();
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

    public void consultarProduto(Scanner scanner){
        int opcao = -1;

        do {
            try{
                System.out.println("\nOpções:");
                System.out.println("1. Consultar todos os produtos");
                System.out.println("2. Consultar produto pelo ID");
                System.out.println("0. Voltar");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
    
                switch (opcao) {
                    case 1:
                        for(Produto p : Sistema.products){
                            p.mostrarProduto();
                        }
                    break;
                    case 2:
                        Produto produto = null;
                        listarProdutos();
                        try{
                            System.out.print("Digite o ID do Produto: ");
                            int id = scanner.nextInt();
                            produto = encontrarProdutoPorId(id);
                        } catch(InputMismatchException e) {
                            System.out.println("Entrada inválida! Digite um número válido.");
                            scanner.nextLine(); 
                        }
                
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
            } catch(InputMismatchException e) {
                System.out.println("Entrada inválida! Digite um número válido.");
                scanner.nextLine(); 
            }
        } while (opcao != 0);
    }
    // ARRUMAR QUE N TA SALVANDO AS MOVIMENTAÇÕES
    // ARRUMAR OS LIMPAR CONSOLE
    public void entradaProduto(Scanner scanner) {
        limparConsole();
        boolean IdExist;
        int id;

        listarProdutos();

        do {
            System.out.print("Informe o ID do produto (ou 0 para cancelar): ");
            id = scanner.nextInt();
            scanner.nextLine(); 
    
            if (id == 0) {
                System.out.println("Operação cancelada.");
                return;
            }
    
            IdExist = false;
            for (int i = 0; i < Sistema.products.size(); i++) {
                if (Sistema.products.get(i).getId() == id) {
                    IdExist = true;
                    break;
                }
            }
    
            if (!IdExist) {
                System.out.println("Não há produto com esse ID cadastrado. Insira outro ID.");
            }
        } while (!IdExist);
    
        Produto produto = encontrarProdutoPorId(id);
    
        Fornecedor fornecedor = produto.getFornecedor();
        boolean fornecedorExist = false;
        for(Fornecedor f : Sistema.fornecedores){
            if(fornecedor == f){
                fornecedorExist = true;
                break;
            }
        }

        if (fornecedorExist) {
            System.out.println("O fornecedor deste produto foi removido. Não é possível registrar a entrada.");
            return; 
        }

        produto.mostrarProduto();
    
        boolean validqtde;
        int qtde;
        do {
            System.out.println("Informe a quantidade a ser adicionada ao estoque:");
            qtde = scanner.nextInt();
            scanner.nextLine();
            if (qtde + produto.getQtdEstoque() > produto.getMaxCapacity()) {
                validqtde = false;
                int maxEntrada = produto.getMaxCapacity() - produto.getQtdEstoque();
                System.out.println("A quantidade informada excede a capacidade máxima de estoque. O valor máximo para isso não ocorrer é " + maxEntrada);
            } else {
                validqtde = true;
                produto.setQtdEstoque(qtde);
            }
        } while (!validqtde);
    
        Movimentacao mov = new Movimentacao("entrada", qtde, produto, this, Sistema.movimentacoes.size() + 1);
        Sistema.movimentacoes.add(mov);
        Sistema.salvarMovimentacoes();
        limparConsole();
        System.out.println("Operação realizada com sucesso.");
    }
    // ARRUMAR OS LIMPAR CONSOLE
    public void saidaProduto(Scanner scanner){
        limparConsole();
        boolean IdExist;
        int id;

        listarProdutos();

        do {
            System.out.print("Informe o ID do produto (ou 0 para cancelar): ");
            id = scanner.nextInt();
            scanner.nextLine(); 
    
            if (id == 0) {
                System.out.println("Operação cancelada.");
                return;
            }
    
            IdExist = false;
            for (int i = 0; i < Sistema.products.size(); i++) {
                if (Sistema.products.get(i).getId() == id) {
                    IdExist = true;
                    break;
                }
            }
    
            if (!IdExist) {
                System.out.println("Não há produto com esse ID cadastrado. Insira outro ID.");
            }
        } while (!IdExist);
    
        Produto produto = encontrarProdutoPorId(id);
    
        Fornecedor fornecedor = produto.getFornecedor();
        boolean fornecedorExist = false;
        for(Fornecedor f : Sistema.fornecedores){
            if(fornecedor == f){
                fornecedorExist = true;
                break;
            }
        }

        if (fornecedorExist) {
            System.out.println("O fornecedor deste produto foi removido. Não é possível registrar a entrada.");
            return; 
        }

        produto.mostrarProduto();

        boolean validqtde;
        int qtde;
        do{
            System.out.println("Informe a quantidade a ser removida do estoque:");
            qtde = scanner.nextInt();
            scanner.nextLine();
            if(qtde>produto.getQtdEstoque()){
                validqtde = false;
                int maxSaida = produto.getQtdEstoque();
                System.out.println("Há apenas "+ maxSaida + " deste produto em estoque. Informe um valor menor: ");
            } else {
                if(produto.getQtdEstoque()-qtde<produto.getMinCapacity()){
                    System.out.println("A quantidade em estoque será menor que a quantidade mínima desejada. Deseja proceguir (s/n)?");
                    char op = scanner.next().charAt(0);
                    scanner.nextLine();
                    if(op == 's'){
                        validqtde = true;
                        produto.setQtdEstoque(-qtde);
                    } else {
                        validqtde = false;
                    }
                } else {
                    validqtde = true;
                    produto.setQtdEstoque(-qtde);
                }
            }
        }while (!validqtde);
        
        Movimentacao mov = new Movimentacao("saída", qtde, produto, this, Sistema.movimentacoes.size() + 1);
        Sistema.movimentacoes.add(mov);
        Sistema.salvarMovimentacoes();
        limparConsole();
        System.out.println("Operação realizada com sucesso.");
    }

    public void gerarRelatorioSugestaoCompra() {
        System.out.println("\nRelatório de Sugestão de Compra");
        System.out.println("--------------------------------------");
        
        boolean necessidadeCompra = false;
        
        for (Produto produto : Sistema.products) {
            int qtdAtual = produto.getQtdEstoque();
            int qtdMinima = produto.getMinCapacity();
            
            if (qtdAtual <= qtdMinima) {
                necessidadeCompra = true;
                String motivo = (qtdAtual == 0) ? "Estoque zerado" : "Estoque abaixo do mínimo";
                
                System.out.println("Produto: " + produto.getNome() + " | Quantidade Atual: " + qtdAtual + " | Quantidade Mínima: " + qtdMinima + 
                        " | Motivo para compra: "+ motivo);

                System.out.println("--------------------------------------");
            }
        }
        
        if (!necessidadeCompra) {
            System.out.println("Todos os produtos estão dentro dos níveis adequados de estoque.");
            System.out.println("--------------------------------------");
        }
    }

    public void gerarRelatorioMovimentacoes() {
        for(Movimentacao m : Sistema.movimentacoes){
            System.out.println(m.getProduto().getNome());
            System.out.println(m.getQuantia());
            System.out.println(m.getTipo());
            System.out.println();
        }
    }

    public void gerarRelatorioEstoque(Scanner scanner) {
        int opcao = -1;

        do {
            try{
                System.out.println("\nOpções:");
                System.out.println("1. Gerar relatório para todos os produtos");
                System.out.println("2. Gerar relatório de um produto");
                System.out.println("0. Voltar");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
    
                switch (opcao) {
                    case 1:
                    break;
                    case 2:
                        Produto produto = null;
                        listarProdutos();
                        try{
                            System.out.print("Digite o ID do Produto: ");
                            int id = scanner.nextInt();
                            produto = encontrarProdutoPorId(id);
                        } catch(InputMismatchException e) {
                            System.out.println("Entrada inválida! Digite um número válido.");
                            scanner.nextLine(); 
                        }
                
                        if (produto != null) {
                            System.out.println("\nRelatório de Estoque");
                            System.out.println("--------------------------------------");
                            System.out.println("\nNome do Produto: " + produto.getNome());
                            System.out.println("Quantia em estoque: " + produto.getQtdEstoque());
                            System.out.println("Capacidade de armazenamento máxima: " + produto.getMaxCapacity());
                            System.out.println("Mínimo estoque desejado: " + produto.getMinCapacity());
                            System.out.println("Custo por unidade: " + produto.getCost());
                            System.out.println("Preço de venda: " + produto.getPrice());
                            double lucroUnidade = produto.getPrice()-produto.getCost();
                            System.out.println("Lucro por produto: " + lucroUnidade);
                            double lucro=0;
                            int comprados=0, vendidos=0;
                            for(Movimentacao m : Sistema.movimentacoes){
                                if(m.getProduto().getId() == produto.getId()){
                                    if(m.getTipo() == "entrada"){
                                        lucro-=(m.getQuantia()*m.getProduto().getCost());
                                        comprados+=m.getQuantia();
                                    }
                                    if(m.getTipo() == "saida"){
                                        lucro += (m.getQuantia()*m.getProduto().getPrice());
                                        vendidos+=m.getQuantia();
                                    }
                                }
                            }
                            System.out.println("Total de produtos comprados: " + comprados);
                            System.out.println("Total de produtos vendidos: " + vendidos);
                            System.out.println("Total de lucro do produto: " + lucro);
                            System.out.println();
                            if(produto.getQtdEstoque()<produto.getMinCapacity()){
                                System.out.println("**** Produto em quantidade menor que o desejado: SUGESTÃO DE COMPRA ****\n");
                            }
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
            } catch(InputMismatchException e) {
                System.out.println("Entrada inválida! Digite um número válido.");
                scanner.nextLine(); 
            }
        } while (opcao != 0);
    }

    public static void limparConsole() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println("Erro ao limpar console");
        }
    } 
    
}