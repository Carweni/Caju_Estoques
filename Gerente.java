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
}