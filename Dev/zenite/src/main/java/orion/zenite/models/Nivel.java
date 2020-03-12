package orion.zenite.models;

public enum Nivel {
    ADM(1),
    GERENTE(2),
    USUARIO(3);

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
