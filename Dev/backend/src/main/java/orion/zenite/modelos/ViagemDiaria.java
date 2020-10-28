package orion.zenite.modelos;

import java.util.ArrayList;

public class ViagemDiaria {
    private int viagensRealizadas;

    private int viagensRestantes;

    private ArrayList<ViagemMotorista> listaViagens;

    public int getViagensRealizadas() {
        return viagensRealizadas;
    }

    public void setViagensRealizadas(int viagensRealizadas) {
        this.viagensRealizadas = viagensRealizadas;
    }

    public int getViagensRestantes() {
        return viagensRestantes;
    }

    public void setViagensRestantes(int viagensRestantes) {
        this.viagensRestantes = viagensRestantes;
    }

    public ArrayList<ViagemMotorista> getListaViagens() {
        return listaViagens;
    }

    public void setListaViagens(ArrayList<ViagemMotorista> listaViagens) {
        this.listaViagens = listaViagens;
    }
}
