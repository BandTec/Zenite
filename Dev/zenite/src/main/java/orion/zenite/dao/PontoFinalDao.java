package orion.zenite.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import orion.zenite.mappers.PontoFinalMapper;
import orion.zenite.models.PontoFinal;

import java.util.List;

@Repository
public class PontoFinalDao implements Dao {

    private String consulta;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public boolean inserir(Object obj) {
        try {
            PontoFinal ponto = (PontoFinal) obj;

            this.consulta = "EXEC spPontoFinal_Inserir @nomeTerminal = ?";

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
            this.consulta = "EXEC spPontoFinal_BuscaPorId @idPontoFinal = ?";

            return jdbcTemplate.queryForObject(consulta, new PontoFinalMapper(), id);

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<?> buscarTodos() {
        try {
            this.consulta = "EXEC spPontoFinal_BuscaTodos";

            return jdbcTemplate.query(consulta, new PontoFinalMapper());

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }

    @Override
    public int ultimoId() {
        try {
            this.consulta = "EXEC spPontoFinal_BuscaUltimoId";

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

            this.consulta = "EXEC spPontoFinal_Alterar @nomeTerminal = ?, @idPontoFinal = ?";

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
            this.consulta = "EXEC spPontoFinal_Deletar @idPontoFinal = ?";

            jdbcTemplate.update(consulta, new PontoFinalMapper(), id);

            return true;
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return false;
        }
    }
}
