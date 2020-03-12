package orion.zenite.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
public class ContaDao {

    private String consulta;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /*
     * A classe mapper "ContaMapper()" filtra o resultado do banco
     * adicionando corretamente os campos da tabela
     * aos atributos da classe
     */

    public Conta buscarPorId(int id) {
        try {
            this.consulta = "SELECT c.*, n.descricao " +
                    "FROM tblConta as c inner join tblNivel as n " +
                    "on n.idNivel = c.fkNivel " +
                    "WHERE idConta = ?";

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

    // getNivel
    public Nivel buscarNivelPorEmail(String email) {
        try {
            this.consulta = "SELECT c.*, n.descricao " +
                    "FROM tblConta as c inner join tblNivel as n " +
                    "on n.idNivel = c.fkNivel " +
                    "WHERE email = ?";

            Conta conta = jdbcTemplate.queryForObject(
                    consulta,
                    new ContaMapper(),
                    email
            );

            return conta.getNivel();
        }  catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }

    public boolean verificarSeExistePorEmail(String email) {
        try {
            this.consulta = "SELECT c.*, n.descricao " +
                    "FROM tblConta as c inner join tblNivel as n " +
                    "on n.idNivel = c.fkNivel " +
                    "WHERE email = ?";

            Conta conta = jdbcTemplate.queryForObject(
                    consulta,
                    new ContaMapper(),
                    email
            );

            return true;
        }  catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
            return false;
        }
    }

    public Conta buscarPorEmailSenha(LoginRequest loginRequest) {
        try {
            this.consulta = "SELECT c.*, n.descricao " +
                    "FROM tblConta as c inner join tblNivel as n " +
                    "on n.idNivel = c.fkNivel " +
                    "WHERE email = ? AND senha = ?";

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

    public List<Conta> buscarTodos() {
        try {
            this.consulta = "SELECT c.*, n.descricao " +
                    "FROM tblConta as c inner join tblNivel as n " +
                    "on n.idNivel = c.fkNivel";

            return jdbcTemplate.query(consulta, new ContaMapper());
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }

    public boolean inserir(Conta conta) {
        try {
            this.consulta = "INSERT INTO tblConta (email, senha, fkNivel) values (?, ?, ?)";

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

    public boolean alterar(Conta conta) {
        try {
            this.consulta = "UPDATE tblConta SET email = ?, senha = ? fkNivel = ? WHERE idConta = ?";

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

    public boolean deletar(int id) {
        try {
            this.consulta = "DELETE FROM tblConta WHERE idConta = ?";
            jdbcTemplate.update(consulta, id);

            return true;

        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

}
