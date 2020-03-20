package orion.zenite.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import orion.zenite.mappers.LinhaMapper;
import orion.zenite.models.Linha;
import orion.zenite.models.PontoFinal;

import java.util.List;

@Repository
public class LinhaDao implements Dao {

    private String consulta;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Object buscarPorId(int id) {
        try {
            this.consulta = "EXEC spLinha_BuscaPorId @idLinha = ?";

            return jdbcTemplate.queryForObject(this.consulta,
                    new LinhaMapper(),
                    id
            );

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<?> buscarTodos() {
        try {
            this.consulta = "EXEC spLinha_BuscaTodos";

            return jdbcTemplate.query(this.consulta, new LinhaMapper());

        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
            return null;
        }
    }

    public List<?> buscarTodosPorPontoVolta(PontoFinal pontoVolta) {
        try {
            this.consulta = "EXEC spLinha_BuscaTodosPorPontoIda @idPontoIda = ?, @pontoIda = ?";

            return jdbcTemplate.query(this.consulta,
                    new LinhaMapper(), pontoVolta.getId(), pontoVolta.getNome());

        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
            return null;
        }
    }

    public List<?> buscarTodosPorPontoIda(PontoFinal pontoIda) {
        try {
            this.consulta = "EXEC spLinha_BuscaTodosPorPontoVolta @idPontoVolta = ?, @pontoVolta = ?";

            return jdbcTemplate.query(this.consulta,
                    new LinhaMapper(), pontoIda.getId(), pontoIda.getNome());

        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
            return null;
        }
    }

    @Override
    public int ultimoId() {
        try {
            this.consulta = "EXEC spLinha_BuscaUltimoId";
            return jdbcTemplate.queryForObject(this.consulta, Integer.class);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public boolean inserir(Object obj) {
        try {
            Linha linha = (Linha) obj;
            this.consulta = "EXEC spLinha_Inserir @numeroLinha = ?, @fkPontoIda = ?, @fkPontoVolta = ?";
            jdbcTemplate.update(this.consulta,
                    linha.getNumero(),
                    linha.getPontoIda().getId(),
                    linha.getPontoVolta().getId());
            return true;
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean alterar(Object obj) {
        try {
            Linha linha = (Linha) obj;
            this.consulta = "EXEC spLinha_Alterar " +
                    "@numeroLinha = ?, @fkPontoIda = ?, @fkPontoVolta = ?, " +
                    "@idLinha = ?";

            jdbcTemplate.update(this.consulta,
                    linha.getNumero(),
                    linha.getPontoIda().getId(),
                    linha.getPontoVolta().getId(),
                    linha.getId()
            );

            return true;
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletar(int id) {
        try {
            this.consulta = "EXEC spLinha_Deletar @idLinha = ?";

            jdbcTemplate.update(this.consulta,
                    id
            );

            return true;
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return false;
        }
    }
}
