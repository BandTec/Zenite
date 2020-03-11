package orion.zenite.models;

public class Administrador extends Conta {

    private int id;
    private String nome;

    public Administrador(String senha, String email, Nivel nivel, int id, String nome) {
        super(senha, email, nivel);
        this.id = id;
        this.nome = nome;
    }

    public Administrador(String senha, String email, Nivel nivel, String nome) {
        super(senha, email, nivel);
        this.nome = nome;
    }
}
