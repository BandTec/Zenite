package orion.zenite.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import orion.zenite.mappers.CarroMapper;
import orion.zenite.mappers.EnderecoMapper;
import orion.zenite.models.Carro;
import orion.zenite.models.Endereco;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
            this.consulta = "EXEC spEndereco_BuscaPorId @idEndereco = ?";

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
            this.consulta = "EXEC spEndereco_BuscaPorCep @cep = ?";

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
            this.consulta = "EXEC spEndereco_BuscaTodosEnderecos";
            return jdbcTemplate.query(consulta, new EnderecoMapper());
        }catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }

    @Override
    public int ultimoId(){
        try {
            this.consulta = "EXEC spEndereco_BuscaUltimoId";

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
            this.consulta = "EXEC spEndereco_Inserir @CEP = ?, @logradouro = ?, @numero = ?, " +
                    "@complemento = ?, @cidade = ?, @estado = ?";

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
            this.consulta = "EXEC spEndereco_Alterar @CEP = ?, @logradouro = ?, @numero = ?, " +
                    "@complemento = ?, @cidade = ?, @estado = ?, @idEndereco = ?";

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
            this.consulta = "EXEC spEndereco_Deletar @idEndereco = ?";
            jdbcTemplate.update(consulta, id);

            return true;

        } catch (DataAccessException e) {
            e.printStackTrace();

            return false;
        }
    }
}
