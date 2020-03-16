package orion.zenite.models;

public class Administrador extends Conta {

    private int id;
    private String nome;

    public Administrador() {}

    public Administrador(String senha, String email, String nivel, int id, String nome) {
        super(senha, email, nivel);
        this.id = id;
        this.nome = nome;
    }

    public Administrador(String senha, String email, int nivel, String nome) {
        super(senha, email, nivel);
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Administrador{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
