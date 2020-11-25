package orion.zenite.modelos;

import java.util.ArrayList;

public class ViagensMotorista {

    private String data;

    private ArrayList<ViagemMotorista> viagens;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public ArrayList<ViagemMotorista> getViagens() {
        return viagens;
    }

    public void setViagens(ArrayList<ViagemMotorista> viagens) {
        this.viagens = viagens;
    }
}
