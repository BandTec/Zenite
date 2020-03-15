package orion.zenite.models;

public class Conta {

    private int idConta;
    private String senha;
    private String email;
    private Nivel nivel;

    public Conta() { } ;

    public Conta(String senha, String email, int nivel) {
        this.senha = senha;
        this.email = email;
        this.nivel = Nivel.escolherPorId(nivel);
    }

    public Conta(String senha, String email, String nivel) {
        this.senha = senha;
        this.email = email;
        this.nivel = Nivel.valueOf(nivel.toUpperCase());
    }


    public Conta(int idConta, String senha, String email, String nivel) {
        this.idConta = idConta;
        this.senha = senha;
        this.email = email;
        this.nivel = Nivel.valueOf(nivel.toUpperCase());
    }

    @Override
    public String toString() {
        return "Conta{" +
                "id=" + idConta +
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

    public int getIdConta() {
        return idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
       this.nivel = Nivel.valueOf(nivel.toUpperCase());
    }

    public void setNivel(int nivel) {
        this.nivel = Nivel.escolherPorId(nivel);
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }
}
