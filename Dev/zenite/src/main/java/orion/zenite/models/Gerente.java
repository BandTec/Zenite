package orion.zenite.models;

public class Gerente extends Funcionario{

    public Gerente() {};

    public Gerente(int id, String senha, String email, String nivel, String nome, String cpf, String dataNascimento, String numeroTelefone, Endereco endereco) {
        super(id, senha, email, nivel, nome, cpf, dataNascimento, numeroTelefone, endereco);
    }

    public Gerente(String senha, String email, String nivel, String nome, String cpf, String dataNascimento, String numeroTelefone, Endereco endereco) {
        super(senha, email, nivel, nome, cpf, dataNascimento, numeroTelefone, endereco);
    }
}
