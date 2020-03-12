package orion.zenite.models;

public class Fiscal extends Funcionario {
    private String registroFiscal;
    private Dispositivo dispositivo;

    public Fiscal() { };

    public Fiscal(int id, String senha, String email, String nivel,
                  String nome, String cpf, String dataNascimento,
                  String numeroTelefone, Endereco endereco,
                  String registroFiscal, Dispositivo dispositivo)
    {
        super(id, senha, email, nivel, nome, cpf, dataNascimento, numeroTelefone, endereco);
        this.registroFiscal = registroFiscal;
        this.dispositivo = dispositivo;
    }

    public Fiscal(String senha, String email, int nivel,
                  String nome, String cpf, String dataNascimento,
                  String numeroTelefone, Endereco endereco,
                  String registroFiscal, Dispositivo dispositivo)
    {
        super(senha, email, nivel, nome, cpf, dataNascimento, numeroTelefone, endereco);
        this.registroFiscal = registroFiscal;
        this.dispositivo = dispositivo;
    }

    public void setRegistroFiscal(String registroFiscal) {
        this.registroFiscal = registroFiscal;
    }

    public String getRegistroFiscal() {
        return registroFiscal;
    }

    public Dispositivo getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(Dispositivo dispositivo) {
        this.dispositivo = dispositivo;
    }

    @Override
    public String toString() {
        return "Fiscal{" +
                "registroFiscal='" + registroFiscal + '\'' +
                ", dispositivo=" + dispositivo +
                '}';
    }
}
