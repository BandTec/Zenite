package orion.zenite.modelos;

import java.util.ArrayList;

public class CronogramaHorarioSimples {

   private String data;

    private ArrayList<Viagens> viagens;

    public CronogramaHorarioSimples() {
    }

    public ArrayList<Viagens> getViagens() {
        return viagens;
    }

    public void setViagens(ArrayList<Viagens> viagens) {
        this.viagens = viagens;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
