package orion.zenite.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tblCarro")
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idCarro")
    private int id;

    @Column(name="numeroCarro")
    private String numero;

    @Column(name="placa")
    private String placa;

    @Column(name="modelo")
    private String modelo;

    @Column(name="fabricante")
    private String fabricante;

    @Column(name="acessivel")
    private Boolean acessivel;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="fkDispositivo", referencedColumnName = "idDispositivo")
    private Dispositivo dispositivo;

    @JsonIgnore
    @OneToMany(mappedBy = "carro", cascade = CascadeType.REMOVE)
    private List<CarroLinha> carroLinhas;

    @JsonIgnore
    @OneToMany(mappedBy = "carro", cascade = CascadeType.REMOVE)
    private List<MotoristaCarro> motoristaCarro;

    @JsonIgnore
    @OneToMany(mappedBy = "carro", cascade = CascadeType.REMOVE)
    private List<Viagem> viagem;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fkGerente")
    private Gerente gerente;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Dispositivo getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(Dispositivo dispositivo) {
        this.dispositivo = dispositivo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public Boolean isAcessivel() {
        return acessivel;
    }

    public void setAcessivel(Boolean acessivel) {
        this.acessivel = acessivel;
    }

    public List getLinhasId() {
        ArrayList linhasId = new ArrayList();
        for (CarroLinha carro : carroLinhas) {
            linhasId.add(carro.getIdLinha());
        }
        return linhasId;
    }

    public Gerente getGerente() {
        return gerente;
    }

    public void setGerente(Gerente gerente) {
        this.gerente = gerente;
    }
}
