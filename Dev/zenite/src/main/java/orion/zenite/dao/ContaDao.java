package orion.zenite.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import orion.zenite.mappers.ContaMapper;
import orion.zenite.models.Conta;
import orion.zenite.models.Nivel;
import orion.zenite.payload.LoginRequest;

import java.util.List;

/*
    * Classe que realiza consultas no banco de dados com o JDBC
    *
    * A configuração do banco de dados se encontra no arquivo:
    * ~/main/resources/application.properties
 */
@Repository
public class ContaDao implements Dao{

    private String consulta;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /*
     * A classe mapper "ContaMapper()" filtra o resultado do banco
     * adicionando corretamente os campos da tabela
     * aos atributos da classe
     */

    @Override
    public Object buscarPorId(int id) {
        try {
            this.consulta = "EXEC spConta_BuscaPorId @idConta = ?";

            Conta conta = jdbcTemplate.queryForObject(
                    consulta,
                    new ContaMapper(),
                    id
            );

            return conta;
        }  catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }

    public Nivel buscarNivelPorEmail(String email) {
        try {
            Conta conta = this.buscarPorEmail(email);
            return conta != null ? conta.getNivel() : null;
        }  catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }

    public boolean verificarSeExistePorEmail(String email) {
        try {
            return this.buscarPorEmail(email) != null ? true : false;
        }  catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
            return false;
        }
    }


    public Conta buscarPorEmail(String email) {
        try {
            this.consulta = "EXEC spConta_BuscarPorEmail @email = ?";

            Conta conta = jdbcTemplate.queryForObject(
                    consulta,
                    new ContaMapper(),
                    email
            );

            return conta;
        }  catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }

    public Conta buscarPorEmailSenha(LoginRequest loginRequest) {
        try {
            this.consulta = "EXEC spLogin @email = ?, @senha = ?";

            Conta conta = jdbcTemplate.queryForObject(
                    consulta,
                    new ContaMapper(),
                    loginRequest.getEmail(),
                    loginRequest.getSenha()
            );

            return conta;
        } catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Conta> buscarTodos() {
        try {
            this.consulta = "EXEC spConta_BuscaTodos";

            List<Conta> contas = jdbcTemplate.query(consulta, new ContaMapper());

            return contas;
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }

    public int ultimoId(){
        try {
            this.consulta = "EXEC spConta_BuscaUltimoId";

            int ultimoId = jdbcTemplate.queryForObject(
                    consulta, Integer.class);

            return ultimoId;

        } catch (DataAccessException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public boolean inserir(Object obj) {
        try {
            Conta conta = (Conta) obj;
            this.consulta = "EXEC spConta_Inserir @email = ?, @senha = ?, @fkNivel = ?";

            jdbcTemplate.update(
                    consulta,
                    conta.getEmail(), conta.getSenha(), conta.getNivel().getId()
            );

            return true;

        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean alterar(Object obj) {
        try {
            Conta conta = (Conta) obj;
            this.consulta = "EXEC spConta_Alterar @email = ?, @senha = ?, @fkNivel = ?, @idConta = ?";

            jdbcTemplate.update(
                    consulta,
                    conta.getEmail(), conta.getSenha(), conta.getNivel().getId(), conta.getIdConta()
                    );

            return true;

        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deletar(int id) {
        try {
            this.consulta = "EXEC spConta_Deletar @idConta = ?";
            jdbcTemplate.update(consulta, id);

            return true;

        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }
}
