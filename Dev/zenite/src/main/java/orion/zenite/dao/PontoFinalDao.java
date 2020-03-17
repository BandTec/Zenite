package orion.zenite.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import orion.zenite.mappers.PontoFinalMapper;
import orion.zenite.models.Conta;
import orion.zenite.models.PontoFinal;

import java.util.List;

@Repository
public class PontoFinalDao implements Dao {

    private String consulta;


    //  tblPontoFinal
    //  "idPontoFinal", "nomeTerminal";


    @Autowired
    private JdbcTemplate jdbcTemplate;


    public boolean inserir(Object obj) {
        try {
            PontoFinal ponto = (PontoFinal) obj;

            this.consulta = "INSERT INTO tblPontoFinal (nomeTerminal) values (?)";

            jdbcTemplate.update(consulta, ponto.getNome());

            return true;
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return false;
        }
    }


    @Override
    public Object buscarPorId(int id) {
        try {
            this.consulta = "SELECT FROM tblPontoFinal WHERE idPontoFinal = ?";

            return jdbcTemplate.queryForObject(consulta, new PontoFinalMapper(), id);

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<?> buscarTodos() {
        try {
            this.consulta = "SELECT * FROM tblPontoFinal";

            return jdbcTemplate.query(consulta, new PontoFinalMapper());

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }

    @Override
    public int ultimoId() {
        try {
            this.consulta = "SELECT TOP 1 idPontoFinal FROM tblPontoFinal ORDER BY idPontoFinal DESC";

            return jdbcTemplate.queryForObject(consulta, Integer.class);

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public boolean alterar(Object obj) {
        try {
            PontoFinal ponto = (PontoFinal) obj;

            this.consulta = "UPDATE tblPontoFinal SET nomeTerminal = ? WHERE idPontoFinal = ?";

            jdbcTemplate.update(consulta, ponto.getNome(), ponto.getId());

            return true;
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletar(int id) {
        try {
            this.consulta = "DELETE FROM tblPontoFinal WHERE idPontoFinal = ?";

            jdbcTemplate.update(consulta, new PontoFinalMapper(), id);

            return true;
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return false;
        }
    }
}
