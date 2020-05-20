package br.com.orion.listaestatica;

public class Onibus {

    private int numOnibus;
    private String placa;
    private String modelo;
    private String fabricante;
    private boolean acessivel;

    public Onibus(int numOnibus, String placa, String modelo, String fabricante, boolean acessivel) {
        this.numOnibus = numOnibus;
        this.placa = placa;
        this.modelo = modelo;
        this.fabricante = fabricante;
        this.acessivel = acessivel;
    }

    public int getNumOnibus() {
        return numOnibus;
    }

    public void setNumOnibus(int numOnibus) {
        this.numOnibus = numOnibus;
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

    public boolean isAcessivel() {
        return acessivel;
    }

    public void setAcessivel(boolean acessivel) {
        this.acessivel = acessivel;
    }
}
