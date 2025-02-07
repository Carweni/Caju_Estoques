import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Sistema {

    static ArrayList<Usuario> users;
    static ArrayList<Movimentacao> movimentacoes;
    static ArrayList<Produto> products;
    static ArrayList<Fornecedor> fornecedores;
    static final String FILE_NAME_U = "usuarios.dat";
    static final String FILE_NAME_F = "fornecedores.dat";
    static final String FILE_NAME_P = "produtos.dat";
    static final String FILE_NAME_M = "movimentacoes.dat";

    public Sistema() {
        users = new ArrayList<>();
        movimentacoes = new ArrayList<>();
        products = new ArrayList<>();
        fornecedores = new ArrayList<>();
    }

    public static void main(String[] args) {
        Sistema sistema = new Sistema();

        // Carregar do arquivo ao iniciar o sistema:
        users = carregarUsuarios();
        fornecedores = carregarFornecedores(); 
        products = carregarProdutos();
        movimentacoes = carregarMovimentacoes();

        if (users.isEmpty()) {
            Usuario admGeral = new ADMgeral("admin", 1, "administrador geral", "123");
            users.add(admGeral);
            salvarUsuarios(); 
        }

        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        do {
            try {
                System.out.println("\nInício:");
                System.out.println("1. Entrar");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine(); 

                switch (opcao) {
                    case 1:
                        limparConsole();
                        Login(sistema, scanner);
                        break;
                    case 0:
                        System.out.println("Saindo do sistema.");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Digite um número válido.");
                scanner.nextLine();
            }
        } while (opcao != 0);

        scanner.close();
    }

    public static void Login(Sistema sistema, Scanner scanner) {
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
            limparConsole();
            System.out.println("Login bem-sucedido!");
            sistema.exibirMenu(usuarioAutenticado, scanner);
        } else {
            limparConsole();
            System.out.println("Credenciais inválidas. Tente novamente.");
        }
    }

    public void exibirMenu(Usuario usuario, Scanner scanner) {
        if (usuario instanceof ADMgeral) {
            ADMgeral admGeral = (ADMgeral) usuario;
            admGeral.exibirMenuAdmGeral(scanner);
        } else if (usuario instanceof Gerente) {
            Gerente gerente = (Gerente) usuario;
            gerente.exibirMenuGerente(scanner);
        } else {
            System.out.println("Tipo de usuário inválido.");
        }
    }

    public static void salvarUsuarios() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME_U))) {
            oos.writeObject(users);
        } catch (IOException e) {
            System.err.println("Erro ao salvar usuários: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Usuario> carregarUsuarios() {
        File arquivo = new File(FILE_NAME_U);
        if (!arquivo.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (ArrayList<Usuario>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar usuários: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void salvarFornecedores() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME_F))) {
            oos.writeObject(fornecedores);
        } catch (IOException e) {
            System.err.println("Erro ao salvar fornecedores: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Fornecedor> carregarFornecedores() {
        File arquivo = new File(FILE_NAME_F);
        if (!arquivo.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (ArrayList<Fornecedor>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar fornecedores: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void salvarProdutos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME_P))) {
            oos.writeObject(products);
        } catch (IOException e) {
            System.err.println("Erro ao salvar produtos: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Produto> carregarProdutos() {
        File arquivo = new File(FILE_NAME_P);
        if (!arquivo.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (ArrayList<Produto>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar produtos: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void salvarMovimentacoes() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME_M))) {
            oos.writeObject(movimentacoes);
        } catch (IOException e) {
            System.err.println("Erro ao salvar produtos: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Movimentacao> carregarMovimentacoes() {
        File arquivo = new File(FILE_NAME_M);
        if (!arquivo.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (ArrayList<Movimentacao>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar movimentações: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void limparConsole() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println("Erro ao limpar console");
        }
    }   
}