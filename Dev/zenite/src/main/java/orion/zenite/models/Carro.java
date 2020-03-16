package orion.zenite.models;

public class Carro {

    private int id;
    private String numero;
    private Linha linha;
    private Dispositivo dispositivo;

    public Carro() { }

    public Carro(String numero, Linha linha, Dispositivo dispositivo) {
        this.numero = numero;
        this.linha = linha;
        this.dispositivo = dispositivo;

    }

    public Carro(int id, String numero, Linha linha, Dispositivo dispositivo) {
        this.id = id;
        this.numero = numero;
        this.linha = linha;
        this.dispositivo = dispositivo;
    }

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

    public Linha getLinha() {
        return linha;
    }

    public void setLinha(Linha linha) {
        this.linha = linha;
    }

    public Dispositivo getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(Dispositivo dispositivo) {
        this.dispositivo = dispositivo;
    }

    @Override
    public String toString() {
        return "Carro{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", linha=" + linha + '\'' +
                ", dispositivo=" + dispositivo + '\'' +
                '}';
    }
}
