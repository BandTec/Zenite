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
public class EnderecoDao implements Dao{
    private String consulta;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
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

    @Override
    public List<Endereco> buscarTodos() {
        try {
            this.consulta = "SELECT * FROM tblEndereco";
            return jdbcTemplate.query(consulta, new EnderecoMapper());
        }catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }

    @Override
    public int ultimoId(){
        try {
            this.consulta = "SELECT TOP 1 idEndereco FROM tblEndereco ORDER BY idEndereco DESC";

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
            Endereco endereco = (Endereco) obj;
            this.consulta = "INSERT INTO tblEndereco (" +
                    "CEP, logradouro, numero, complemento, cidade, estado" +
                    ") values (?, ?, ?, ?, ?, ?)";

            jdbcTemplate.update(
                    consulta,
                    endereco.getCep(), endereco.getLogradouro(), endereco.getNumero(),
                    endereco.getComplemento(), endereco.getCidade(), endereco.getEstado()
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
            Endereco endereco = (Endereco) obj;
            this.consulta = "UPDATE tblEndereco SET CEP = ?, " +
                    "logradouro = ?, numero = ?, complemento = ?, cidade = ?, " +
                    "estado = ? WHERE idEndereco = ?";

            jdbcTemplate.update(
                    consulta,
                    endereco.getCep(), endereco.getLogradouro(), endereco.getNumero(),
                    endereco.getComplemento(), endereco.getCidade(), endereco.getEstado(),
                    endereco.getId()
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
            this.consulta = "DELETE FROM tblEndereco WHERE idEndereco = ?";
            jdbcTemplate.update(consulta, id);

            return true;

        } catch (DataAccessException e) {
            e.printStackTrace();

            return false;
        }
    }
}
