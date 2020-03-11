package orion.zenite.models;

public class Fiscal extends Funcionario {
    private String registroFiscal;
    private Dispositivo dispositivo;

    public Fiscal(int id, String senha, String email, Nivel nivel, String nome, String cpf, String dataNascimento, String numeroTelefone, Endereco endereco, String registroFiscal) {
        super(id, senha, email, nivel, nome, cpf, dataNascimento, numeroTelefone, endereco);
        this.registroFiscal = registroFiscal;
    }

    public Fiscal(String senha, String email, Nivel nivel, String nome, String cpf, String dataNascimento, String numeroTelefone, Endereco endereco, String registroFiscal) {
        super(senha, email, nivel, nome, cpf, dataNascimento, numeroTelefone, endereco);
        this.registroFiscal = registroFiscal;
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
}
