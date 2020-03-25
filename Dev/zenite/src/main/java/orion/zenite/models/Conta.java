package orion.zenite.models;

import javax.persistence.*;

@Entity
@Table(name = "tblConta")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idConta")
    private int idConta;

    @Column(nullable = false)
    private String senha;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name="fkNivel")
    private Nivel nivel;

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

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }
}
