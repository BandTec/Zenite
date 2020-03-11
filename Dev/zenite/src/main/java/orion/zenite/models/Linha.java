package orion.zenite.models;


public class Linha {

    private int id;
    private String numero;
    private PontoFinal pontoIda;
    private PontoFinal pontoVolta;

    public Linha(int id, String numero, PontoFinal pontoIda, PontoFinal pontoVolta) {
        this.id = id;
        this.numero = numero;
        this.pontoIda = pontoIda;
        this.pontoVolta = pontoVolta;
    }

    public Linha(String numero, PontoFinal pontoIda, PontoFinal pontoVolta) {
        this.numero = numero;
        this.pontoIda = pontoIda;
        this.pontoVolta = pontoVolta;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public PontoFinal getPontoIda() {
        return pontoIda;
    }

    public void setPontoIda(PontoFinal pontoIda) {
        this.pontoIda = pontoIda;
    }

    public PontoFinal getPontoVolta() {
        return pontoVolta;
    }

    public void setPontoVolta(PontoFinal pontoVolta) {
        this.pontoVolta = pontoVolta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
