package orion.zenite.models;

public enum Nivel {
    ADMIN(1),
    GERENTE(2),
    FISCAL(3),
    MOTORISTA(4),
    PASSAGEIRO(5);

    private int id;

    Nivel(int id){
        this.id = id;
    }

    public static Nivel escolherPorId(int id){
        Nivel encontrada = null;
        for(Nivel atual : values()){
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
