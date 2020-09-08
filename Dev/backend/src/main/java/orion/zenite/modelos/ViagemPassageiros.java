package orion.zenite.modelos;

public class ViagemPassageiros {

    private Integer qtdPassageiros;

    public Integer getQtdPassageiros() {
        return qtdPassageiros;
    }

    public void setQtdPassageiros(Integer qtdPassageiros) {
        this.qtdPassageiros = qtdPassageiros;
    }

    @Override
    public String toString() {
        return "ViagemPassageiros{" +
                "qtdPassageiros=" + qtdPassageiros +
                '}';
    }
}
