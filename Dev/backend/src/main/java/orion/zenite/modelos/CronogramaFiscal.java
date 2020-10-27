package orion.zenite.modelos;

import java.util.ArrayList;

public class CronogramaFiscal {

    private Integer idLinha;

    private String nomeLinha;

    private ArrayList<CronogramaLinha> cronograma;

    public Integer getIdLinha() {
        return idLinha;
    }

    public void setIdLinha(Integer idLinha) {
        this.idLinha = idLinha;
    }

    public String getNomeLinha() {
        return nomeLinha;
    }

    public void setNomeLinha(String nomeLinha) {
        this.nomeLinha = nomeLinha;
    }

    public ArrayList<CronogramaLinha> getCronograma() {
        return cronograma;
    }

    public void setCronograma(ArrayList<CronogramaLinha> cronograma) {
        this.cronograma = cronograma;
    }



}
