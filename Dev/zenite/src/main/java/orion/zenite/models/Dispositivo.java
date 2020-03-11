package orion.zenite.models;

public class Dispositivo {

    private int id;
    private String codigo;
    private TipoDispositivo tipoDispositivo;

    public Dispositivo(int id, String codigo, TipoDispositivo tipoDispositivo) {
        this.id = id;
        this.codigo = codigo;
        this.tipoDispositivo = tipoDispositivo;
    }

    public Dispositivo(String codigo, TipoDispositivo tipoDispositivo) {
        this.codigo = codigo;
        this.tipoDispositivo = tipoDispositivo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public TipoDispositivo getTipoDispositivo() {
        return tipoDispositivo;
    }

    public void setTipoDispositivo(TipoDispositivo tipoDispositivo) {
        this.tipoDispositivo = tipoDispositivo;
    }
}
