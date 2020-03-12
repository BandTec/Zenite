package orion.zenite.models;


public class Motorista extends Funcionario {
    private String cnh;
    private Dispositivo rfid;

    public Motorista() {};

    public Motorista(int id, String senha, String email, String nivel, String nome,
                     String cpf, String dataNascimento, String numeroTelefone,
                     Endereco endereco, String cnh, Dispositivo rfid) {
        super(id, senha, email, nivel, nome, cpf, dataNascimento, numeroTelefone, endereco);
        this.rfid = rfid;
        this.cnh = cnh;
    }

    public Motorista(String senha, String email, int nivel, String nome, String cpf,
                     String dataNascimento, String numeroTelefone, Endereco endereco,
                     String cnh, Dispositivo rfid) {
        super(senha, email, nivel, nome, cpf, dataNascimento, numeroTelefone, endereco);
        this.cnh = cnh;
        this.rfid = rfid;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public Dispositivo getRfid() {
        return rfid;
    }

    public void setRfid(Dispositivo rfid) {
        this.rfid = rfid;
    }

    @Override
    public String toString() {
        return "Motorista{" +
                "cnh='" + cnh + '\'' +
                ", rfid=" + rfid +
                '}';
    }
}
