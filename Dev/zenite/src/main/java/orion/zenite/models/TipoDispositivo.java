package orion.zenite.models;

public enum TipoDispositivo {
    ARDUINO(1),
    CARTAO(2);

    private int id;

    TipoDispositivo(int id){
        this.id = id;
    }

    public static TipoDispositivo escolherPorId(int id){
        TipoDispositivo encontrada = null;
        for(TipoDispositivo atual : values()){
            if(atual.getId() == id){
                encontrada = atual;
            }
        }

        return encontrada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
