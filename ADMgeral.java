import java.util.InputMismatchException;
import java.util.Scanner;

public class ADMgeral extends Usuario {

    public ADMgeral(String nome, int id, String cargo, String senha) {
        super(nome, id, cargo, senha);
    }

    public void exibirMenuAdmGeral(Scanner scanner) {
        int opcao = -1;

        do {
            try {
                System.out.println("\nMenu:");
                System.out.println("1. Cadastrar Usuário Gerente");
                System.out.println("2. Alterar Permissões de Usuário");
                System.out.println("3. Remover Usuário");
                System.out.println("4. Listar Usuários");
                System.out.println("5. Alterar Informações de Administrador");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine(); 

                switch (opcao) {
                    case 1:
                        cadastrarUsuarioGerente(scanner);
                        break;
                    case 2:
                        System.out.println();
                        System.out.println();
                        alterarPermissoesUsuario(scanner);
                        break;
                    case 3:
                        removerUsuario(scanner);
                        break;
                    case 4:
                        listarUsers();
                        break;
                    case 5:
                        alterarInfos(scanner);
                        break;
                    case 0:
                        limparConsole();
                        System.out.println("Saindo do menu de administração.");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                limparConsole();
                System.out.println("Entrada inválida! Digite um número válido.");
                scanner.nextLine(); 
            }
        } while (opcao != 0);
    }
    
    private void cadastrarUsuarioGerente(Scanner scanner) {
        limparConsole();
        boolean IdExist = true;
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
        int id = -1;
        do{
            try{
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
            } catch(InputMismatchException e) {
                System.out.println("Entrada inválida! Digite um número válido.");
                scanner.nextLine(); 
            }
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
        Sistema.salvarUsuarios();

        limparConsole();
        System.out.println("Usuário Gerente cadastrado com sucesso.");
    }

    private void alterarPermissoesUsuario(Scanner scanner) {
        System.out.println("Lista de Usuários:");
        listarUsers();
        Usuario usuario = null;
        boolean idLido = false;
        
        do{
            try{
                System.out.print("Digite o ID do Usuário para alterar permissões: ");
                int id = scanner.nextInt();
                usuario = encontrarUsuarioPorId(id);
                idLido = true;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Digite um número válido.");
                scanner.nextLine(); 
            }
        }while(!idLido);

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

            limparConsole();
            ((Gerente)usuario).setPermissao(permission);
            Sistema.salvarUsuarios();
            System.out.println("Permissão de gerente alterada com sucesso.");
        } else {
            limparConsole();
            System.out.println("Usuário não encontrado ou não é um gerente.");
        }
    }

    private void removerUsuario(Scanner scanner) {
        System.out.println("Lista de Usuários:");
        listarUsers();
        Usuario usuario = null;
        boolean idLido = false;
        
        do{
            try{
                System.out.print("Digite o ID do Usuário para remove-lo: ");
                int id = scanner.nextInt();
                usuario = encontrarUsuarioPorId(id);
                idLido = true;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Digite um número válido.");
                scanner.nextLine(); 
            }
        }while(!idLido);

        if (usuario != null) {
            if(usuario.getId() == 1){
                System.out.println("Administrador não pode ser removido.");
            } else {
                System.out.println("Você deseja confirmar a remoção de " + usuario.getNome() + "? (s/n)");
                char confirmacao = scanner.next().charAt(0);
                
                if (confirmacao == 's' || confirmacao == 'S'){
                    Sistema.users.remove(usuario);
                    Sistema.salvarUsuarios();
                    limparConsole();
                    System.out.println("Usuário removido com sucesso.");
                }else{
                    limparConsole();
                    System.out.println("Operação cancelada.");
                }
                
            }
        } else {
            limparConsole();
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

    public void listarUsers() {
        for(int i=0; i<Sistema.users.size();i++){
            Usuario usuario = Sistema.users.get(i);
            System.out.print(usuario.getId()+ "- ");
            System.out.println(usuario.getNome());
            if(usuario instanceof Gerente) {
                Gerente usuarioG = (Gerente) usuario;
                System.out.print("Permissão para cadastrar/remover fornecedores: ");
                if(usuarioG.getPermissao()[0]){
                    System.out.println("Sim");
                }else{
                    System.out.println("Não");
                }

                System.out.print("Permissão para cadastrar/remover produtos: ");
                if(usuarioG.getPermissao()[1]){
                    System.out.println("Sim");
                }else{
                    System.out.println("Não");
                }

                System.out.print("Permissão para registrar entrada e saída produtos: ");
                if(usuarioG.getPermissao()[2]){
                    System.out.println("Sim");
                }else{
                    System.out.println("Não");
                }
            }
            System.out.println();
        }
    }

    public static void limparConsole() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println("Erro ao limpar console");
        }
    }    
    
    public void alterarInfos(Scanner scanner) {  
        limparConsole();
        int opcao = -1;

        do {
            try{
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
                            limparConsole();
                            System.out.println("Nome alterado com sucesso!");
                        } else {
                            limparConsole();
                            System.out.println("Operação cancelada");
                        }
                        break;
                    case 2:
                        System.out.println("Digite nova senha: ");
                        scanner.nextLine();
                        String senha = scanner.nextLine();
                        System.out.println("Confirma (s/n): ");
                        confirm = scanner.next().charAt(0);
                        if(confirm == 's'){
                            super.setSenha(senha);
                            limparConsole();
                            System.out.println("Senha alterada com sucesso!");
                        } else {
                            limparConsole();
                            System.out.println("Operação cancelada");
                        }
                        break;
                    case 0:
                        limparConsole();
                        System.out.println("Voltando.");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Digite um número válido.");
                scanner.nextLine(); 
            }
        } while (opcao != 0);
    }
}
