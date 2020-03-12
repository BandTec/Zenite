package orion.zenite.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import orion.zenite.mappers.EnderecoMapper;
import orion.zenite.models.Endereco;

import java.util.List;

/*
 * Classe que realiza consultas no banco de dados com o JDBC
 *
 * A configuração do banco de dados se encontra no arquivo:
 * ~/main/resources/application.properties
 */
@Repository
public class EnderecoDao {
    private String consulta;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Endereco buscarPorId(int id) {
        try {
            this.consulta = "SELECT * FROM tblEndereco WHERE idEndereco = ?";

            Endereco endereco = jdbcTemplate.queryForObject(
                    consulta,
                    new BeanPropertyRowMapper<Endereco>(Endereco.class),
                    id
            );

            return endereco;
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }

    public Endereco buscarPorCep(String cep) {
        try {
            this.consulta = "SELECT * FROM tblEndereco WHERE CEP = ?";

            Endereco endereco = jdbcTemplate.queryForObject(
                    consulta,
                    new BeanPropertyRowMapper<Endereco>(Endereco.class),
                    cep
            );

            return endereco;
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }

    public List<Endereco> buscarTodos() {
        try {
            this.consulta = "SELECT * FROM tblEndereco";
            return jdbcTemplate.query(consulta, new EnderecoMapper());
        }catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }

    public boolean inserir(Endereco endereco) {
        try {
            this.consulta = "INSERT INTO tblEndereco (" +
                    "CEP, logradouro, numero, complemento, cidade, estado" +
                    ") values (?, ?, ?)";

            jdbcTemplate.update(
                    consulta,
                    endereco
            );

            return true;

        } catch (DataAccessException e) {
            e.printStackTrace();

            return false;
        }
    }

    public boolean alterar(Endereco endereco) {
        try {
            this.consulta = "UPDATE tblEndereco SET CEP = ?, " +
                    "logradouro = ?, numero = ? complemento = ? cidade = ? " +
                    "estado = ? WHERE idEndereco=?";

            jdbcTemplate.update(
                    consulta,
                    endereco
            );

            return true;

        } catch (DataAccessException e) {
            e.printStackTrace();

            return false;
        }
    }

    public boolean deletar(int id) {
        try {
            this.consulta = "DELETE FROM tblEndereco WHERE idEndereco = ?";
            jdbcTemplate.update(consulta, id);

            return true;

        } catch (DataAccessException e) {
            e.printStackTrace();

            return false;
        }
    }
}
