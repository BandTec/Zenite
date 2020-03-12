package orion.zenite.models;

public class Conta {

    private int id;
    private String senha;
    private String email;
    private Nivel nivel;

    public Conta() { } ;

    public Conta(String senha, String email, String nivel) {
        this.senha = senha;
        this.email = email;
        this.nivel = Nivel.valueOf(nivel.toUpperCase());
    }

    public Conta(int id, String senha, String email, String nivel) {
        this.id = id;
        this.senha = senha;
        this.email = email;
        this.nivel = Nivel.valueOf(nivel.toUpperCase());
    }

    @Override
    public String toString() {
        return "Conta{" +
                "id=" + id +
                ", senha='" + senha + '\'' +
                ", email='" + email + '\'' +
                ", nivel=" + nivel +
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
