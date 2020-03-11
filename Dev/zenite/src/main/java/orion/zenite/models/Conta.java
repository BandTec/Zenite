package orion.zenite.models;

public abstract class Conta {

    private int id;
    private String senha;
    private String email;
    private Nivel nivel;

    public Conta(String senha, String email, Nivel nivel) {
        this.senha = senha;
        this.email = email;
        this.nivel = nivel;
    }

    public Conta(int id, String senha, String email, Nivel nivel) {
        this.id = id;
        this.senha = senha;
        this.email = email;
        this.nivel = nivel;
    }

    @Override
    public String toString() {
        return "Usuario: " +
                "senha='" + senha + '\'' +
                ", email='" + email +
                '}';
    }


    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }
}
