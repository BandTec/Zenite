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

    // super("tblLinha", new String[]{"idLinha", "fkPontoIda", "fkPontoVolta", "numeroLinha"});

    private String consulta;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Object buscarPorId(int id) {
        try {
            this.consulta = "SELECT linha.idLinha, linha.numeroLinha, " +
                    "pontoIda.idPontoFinal AS 'idPontoIda', pontoIda.nomeTerminal AS 'nomeIda', " +
                    "pontoVolta.idPontoFinal AS 'idPontoVolta', pontoVolta.nomeTerminal AS 'nomeVolta' " +
                    "FROM tblLinha AS linha " +
                    "INNER JOIN tblPontoFinal AS pontoIda " +
                    "ON pontoIda.idPontoFinal = linha.fkPontoIda " +
                    "INNER JOIN tblPontoFinal AS pontoVolta " +
                    "ON pontoVolta.idPontoFinal = linha.fkPontoVolta " +
                    "WHERE idLinha = ?";

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
            this.consulta = "SELECT linha.idLinha, linha.numeroLinha, " +
                    "pontoIda.idPontoFinal AS 'idPontoIda', pontoIda.nomeTerminal AS 'nomeIda', " +
                    "pontoVolta.idPontoFinal AS 'idPontoVolta', pontoVolta.nomeTerminal AS 'nomeVolta' " +
                    "FROM tblLinha AS linha " +
                    "INNER JOIN tblPontoFinal AS pontoIda " +
                    "ON pontoIda.idPontoFinal = linha.fkPontoIda " +
                    "INNER JOIN tblPontoFinal AS pontoVolta " +
                    "ON pontoVolta.idPontoFinal = linha.fkPontoVolta";

            return jdbcTemplate.query(this.consulta, new LinhaMapper());

        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
            return null;
        }
    }

    public List<?> buscarTodosPorPontoVolta(PontoFinal pontoVolta) {
        try {
            this.consulta = "SELECT linha.idLinha, linha.numeroLinha, " +
                    "pontoIda.idPontoFinal AS 'idPontoIda', pontoIda.nomeTerminal AS 'nomeIda', " +
                    "pontoVolta.idPontoFinal AS 'idPontoVolta', pontoVolta.nomeTerminal AS 'nomeVolta' " +
                    "FROM tblLinha AS linha " +
                    "INNER JOIN tblPontoFinal AS pontoIda " +
                    "ON pontoIda.idPontoFinal = linha.fkPontoIda " +
                    "INNER JOIN tblPontoFinal AS pontoVolta " +
                    "ON pontoVolta.idPontoFinal = linha.fkPontoVolta " +
                    "WHERE fkPontoVolta = ? OR pontoVolta.nomeTerminal like ?";

            return jdbcTemplate.query(this.consulta,
                    new LinhaMapper(), pontoVolta.getId(), pontoVolta.getNome());

        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
            return null;
        }
    }

    public List<?> buscarTodosPorPontoIda(PontoFinal pontoIda) {
        try {
            this.consulta = "SELECT linha.idLinha, linha.numeroLinha, " +
                    "pontoIda.idPontoFinal AS 'idPontoIda', pontoIda.nomeTerminal AS 'nomeIda', " +
                    "pontoVolta.idPontoFinal AS 'idPontoVolta', pontoVolta.nomeTerminal AS 'nomeVolta' " +
                    "FROM tblLinha AS linha " +
                    "INNER JOIN tblPontoFinal AS pontoIda " +
                    "ON pontoIda.idPontoFinal = linha.fkPontoIda " +
                    "INNER JOIN tblPontoFinal AS pontoVolta " +
                    "ON pontoVolta.idPontoFinal = linha.fkPontoVolta " +
                    "WHERE fkPontoIda = ? OR pontoIda.nomeTerminal like ?";

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
            this.consulta = "SELECT TOP 1 idLinha FROM tblLinha ORDER BY idLinha DESC";
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
            this.consulta = "INSERT INTO tblLinha (numeroLinha, fkPontoIda, fkPontoVolta) values (?, ?, ?)";
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
            this.consulta = "UPDATE tblLinha " +
                    "SET numeroLinha = ?, fkPontoIda = ?, fkPontoVolta = ? " +
                    "WHERE idLinha = ?";

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
            this.consulta = "DELETE FROM tblLinha WHERE idLinha = ?";

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
