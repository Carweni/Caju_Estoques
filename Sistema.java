import java.util.ArrayList;
import java.util.Scanner;

//PRA FAZER: se escrever no menu algo q n é numero, quebra - concertar
public class Sistema {

    static ArrayList<Usuario> users;
    static ArrayList<Movimentacao> movimentacoes;
    static ArrayList<Produto> products;
    static ArrayList<Fornecedor> fornecedores;

    public Sistema() {
        users = new ArrayList<>();
        movimentacoes = new ArrayList<>();
        products = new ArrayList<>();
        fornecedores = new ArrayList<>();
    }

    public static void main(String[] args) {
        Sistema sistema = new Sistema();

        Usuario admGeral = new ADMgeral("admin", 1, "administrador geral", "123");

        users.add(admGeral);

        Scanner scanner = new Scanner(System.in);

        int opcao;

        do {
            System.out.println("\nInício:");
            System.out.println("1. Entrar");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    Login(sistema);
                    break;
                case 0:
                    System.out.println("Saindo do sistema.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    
        scanner.close();
    }
                    
    public static void Login(Sistema sistema){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome de usuário: ");
        String nomeUsuario = scanner.nextLine();

        System.out.println("Digite a senha: ");
        String senhaUsuario = scanner.nextLine();

        Usuario usuarioAutenticado = null;
        for (Usuario usuario : users) {
            if (usuario.getNome().equals(nomeUsuario) && usuario.getSenha().equals(senhaUsuario)) {
                usuarioAutenticado = usuario;
                break;
            }
        }

        if (usuarioAutenticado != null) {
            System.out.println("Login bem-sucedido!");
            sistema.exibirMenu(usuarioAutenticado);
        } else {
            System.out.println("Credenciais inválidas. Tente novamente.");
        }
    }

    public void exibirMenu(Usuario usuario) {
        Scanner scanner = new Scanner(System.in);

        if (usuario instanceof ADMgeral) {
            ADMgeral admGeral = (ADMgeral) usuario;
            admGeral.exibirMenuAdmGeral();
        } else if (usuario instanceof Gerente) {
            Gerente gerente = (Gerente) usuario;
            gerente.exibirMenuGerente();
            //exibirMenuUsuario(usuario);
        } else {
            System.out.println("Tipo de usuário inválido.");
        }
    }

    private void exibirMenuUsuario(Usuario usuario) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\nMenu:");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
        } while (opcao != 0);
    }
}