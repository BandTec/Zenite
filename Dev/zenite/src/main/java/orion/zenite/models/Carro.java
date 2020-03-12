package orion.zenite.models;

public class Carro {

    private int id;
    private String numero;
    private Linha Linha;

    public Carro() { }

    public Carro(String numero, Linha linha) {
        this.numero = numero;
        Linha = linha;
    }

    public Carro(int id, String numero, Linha linha) {
        this.id = id;
        this.numero = numero;
        Linha = linha;
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
        return Linha;
    }

    public void setLinha(Linha linha) {
        Linha = linha;
    }

    @Override
    public String toString() {
        return "Carro{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", Linha=" + Linha +
                '}';
    }
}
