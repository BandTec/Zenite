package orion.zenite.entidades;

import javax.persistence.*;

@Entity
@Table(name="tblEndereco")
public class Endereco  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEndereco")
    private int id;

    @Column(name = "CEP", length = 10, nullable = false)
    private String cep;

    @Column(length = 120, nullable = false)
    private String logradouro;

    @Column(length = 16, nullable = false)
    private String numero;

    @Column(length = 60)
    private String complemento;

    @Column(length = 40)
    private String cidade;

    @Column(length = 2)
    private String estado;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
