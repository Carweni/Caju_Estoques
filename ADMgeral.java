import java.util.Scanner;

public class ADMgeral extends Usuario {

    public ADMgeral(String nome, int id, String cargo, String senha) {
        super(nome, id, cargo, senha);
    }

    public void exibirMenuAdmGeral() {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Cadastrar Usuário Gerente");
            System.out.println("2. Alterar Permissões de Usuário");
            System.out.println("3. Remover Usuário");
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
                case 0:
                    System.out.println("Saindo do sistema.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private void cadastrarUsuarioGerente() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nCadastrar Usuário Gerente:");
        System.out.print("Digite o nome do Usuário Gerente: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o cargo do Usuário Gerente: ");
        String cargo = scanner.nextLine();
        System.out.print("Digite o ID do Usuário Gerente: ");
        int id = scanner.nextInt();
        scanner.nextLine();  
        System.out.print("Digite a senha do Usuário Gerente: ");
        String senha = scanner.nextLine();

        Gerente novoGerente = new Gerente(nome, id, cargo, senha);
        Sistema.users.add(novoGerente);

        System.out.println("Usuário Gerente cadastrado com sucesso.");
    }

    private void alterarPermissoesUsuario() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o ID do Usuário para alterar permissões: ");
        int id = scanner.nextInt();
        Usuario usuario = encontrarUsuarioPorId(id);

        if (usuario instanceof Gerente) {
            // IMPLEMENTAR
        } else {
            System.out.println("Usuário não encontrado ou não é um gerente.");
        }
    }

    private void removerUsuario() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o ID do Usuário para remover: ");
        int id = scanner.nextInt();
        Usuario usuario = encontrarUsuarioPorId(id);

        if (usuario != null) {
            Sistema.users.remove(usuario);
            System.out.println("Usuário removido com sucesso.");
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
}
