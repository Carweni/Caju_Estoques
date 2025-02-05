import java.util.Scanner;

public class ADMgeral extends Usuario {

    public ADMgeral(String nome, int id, String cargo, String senha) {
        super(nome, id, cargo, senha);
    }

    // ver se listar usuarios como opção tem que mudar la no documento
    public void exibirMenuAdmGeral() {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Cadastrar Usuário Gerente");
            System.out.println("2. Alterar Permissões de Usuário");
            System.out.println("3. Remover Usuário");
            System.out.println("4. Listar Usuários");
            System.out.println("5. Alterar Informações de Administrador");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    cadastrarUsuarioGerente();
                    break;
                case 2:
                    alterarPermissoesUsuario();
                    break;
                case 3:
                    removerUsuario();
                    break;
                case 4:
                    listarUsers();
                    break;
                case 5:
                    alterarInfos();
                    break;
                case 0:
                    System.out.println("Saindo do sistema.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    // aqui falta só guardar as informções de usuário e deixar bonito
    private void cadastrarUsuarioGerente() {
        Scanner scanner = new Scanner(System.in);
        boolean IdExist;
        boolean[] permission = new boolean[3];
        for (int i = 0; i < permission.length; i++) {
            permission[i] = false;
        }

        System.out.println("\nCadastrar Usuário Gerente:");

        System.out.print("Digite o nome do Usuário Gerente: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o cargo do Usuário Gerente: ");
        String cargo = scanner.nextLine();

        // Verifica se id de usuário já existe no sistema.
        int id;
        do{
            System.out.print("Digite o ID do Usuário Gerente: ");
            id = scanner.nextInt();
            IdExist=false;
            for(int i=0; i<Sistema.users.size();i++){
                if(Sistema.users.get(i).getId()==id){
                    IdExist=true;
                    System.out.println("ID de usuário já existente no sistema, insira outro id: ");
                    break;
                }
            };
        }while(IdExist);
        scanner.nextLine();  
        
        System.out.println("Usuário terá permissão para cadastrar/remover fornecedores (s/n) : ");
        char confirm = scanner.next().charAt(0);
        if(confirm == 's'){
            permission[0] = true;
            System.out.println("Permissão concedida");
        } else {
            System.out.println("Permissão negada");
        }
        
        System.out.println("Usuário terá permissão para cadastrar/remover produtos (s/n) : ");
        confirm = scanner.next().charAt(0);
        if(confirm == 's'){
            permission[1] = true;
            System.out.println("Permissão concedida");
        } else {
            System.out.println("Permissão negada");
        }

        System.out.println("Usuário terá permissão para registrar entrada e saída produtos (s/n) : ");
        confirm = scanner.next().charAt(0);
        if(confirm == 's'){
            permission[2] = true;
            System.out.println("Permissão concedida");
        } else {
            System.out.println("Permissão negada");
        }

        System.out.print("Digite a senha do Usuário Gerente: ");
        scanner.nextLine();
        String senha = scanner.nextLine();

        Gerente novoGerente = new Gerente(nome, id, cargo, senha, permission);
        Sistema.users.add(novoGerente);

        System.out.println("Usuário Gerente cadastrado com sucesso.");
    }

    // aqui falta listar bonito os users e dependendo de se a gente deixar assim a maneira de alterar temos q reformular no docs
    private void alterarPermissoesUsuario() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Lista de Usuários:");
        listarUsers();

        System.out.print("Digite o ID do Usuário para alterar permissões: ");
        int id = scanner.nextInt();
        Usuario usuario = encontrarUsuarioPorId(id);

        if (usuario instanceof Gerente) {
            boolean[] permission = new boolean[3];
            for (int i = 0; i < permission.length; i++) {
                permission[i] = false;
            }

            System.out.println("Usuário terá permissão para cadastrar/remover fornecedores (s/n) : ");
            char confirm = scanner.next().charAt(0);
            if(confirm == 's'){
                permission[0] = true;
                System.out.println("Permissão concedida");
            } else {
                System.out.println("Permissão negada");
            }
            
            System.out.println("Usuário terá permissão para cadastrar/remover produtos (s/n) : ");
            confirm = scanner.next().charAt(0);
            if(confirm == 's'){
                permission[1] = true;
                System.out.println("Permissão concedida");
            } else {
                System.out.println("Permissão negada");
            }

            System.out.println("Usuário terá permissão para registrar entrada e saída produtos (s/n) : ");
            confirm = scanner.next().charAt(0);
            if(confirm == 's'){
                permission[2] = true;
                System.out.println("Permissão concedida");
            } else {
                System.out.println("Permissão negada");
            }

            ((Gerente)usuario).setPermissao(permission);
        } else {
            System.out.println("Usuário não encontrado ou não é um gerente.");
        }
    }

    //  tem q fazer o adm confirmar, deixar bonito e adicionar no docs q o adm n pode remover ele mesmo
    private void removerUsuario() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Lista de Usuários:");
        listarUsers();

        System.out.print("Digite o ID do Usuário para remover: ");
        int id = scanner.nextInt();
        Usuario usuario = encontrarUsuarioPorId(id);

        if (usuario != null) {
            if(usuario.getId() == 1){
                System.out.println("Administrador não pode ser removido.");
            } else {
                Sistema.users.remove(usuario);
                System.out.println("Usuário removido com sucesso.");
            }
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    private Usuario encontrarUsuarioPorId(int id) {
        for (Usuario usuario : Sistema.users) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }
        return null;
    }

    //PRA FAZER: ARRUMAR BONITINHO O QUE IMPRIME NA TELA
    public void listarUsers() {
        for(int i=0; i<Sistema.users.size();i++){
            Usuario usuario = Sistema.users.get(i);
            System.out.print(usuario.getId()+ "- ");
            System.out.println(usuario.getNome());
            if(usuario instanceof Gerente) {
                Gerente usuarioG = (Gerente) usuario;
                System.out.print(usuarioG.getPermissao()[0]);
                System.out.print(usuarioG.getPermissao()[1]);
                System.out.println(usuarioG.getPermissao()[2]);
            }
        }
    }
    
    // ver se vamos deixar isso mesmo e adicionar la no docs ou só deixar assim, ou apagar
    public void alterarInfos() {    
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("1. Alterar nome");
            System.out.println("2. Alterar senha");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            char confirm;

            switch (opcao) {
                case 1:
                    System.out.println("Digite o nome: ");
                    scanner.nextLine();
                    String nome = scanner.nextLine();
                    System.out.println("Confirma (s/n): ");
                    confirm = scanner.next().charAt(0);
                    if(confirm == 's'){
                        super.setNome(nome);
                        System.out.println("Nome alterado com sucesso!");
                    } else {
                        System.out.println("Operação cancelada");
                    }
                    break;
                case 2:
                    System.out.println("Digite nova senha: ");
                    String senha = scanner.nextLine();
                    System.out.println("Confirma (s/n): ");
                    confirm = scanner.next().charAt(0);
                    if(confirm == 's'){
                        super.setSenha(senha);
                        System.out.println("Senha alterada com sucesso!");
                    } else {
                        System.out.println("Operação cancelada");
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
}
