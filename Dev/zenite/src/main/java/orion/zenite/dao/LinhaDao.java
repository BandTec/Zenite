package orion.zenite.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import orion.zenite.mappers.LinhaMapper;
import orion.zenite.models.Linha;

import java.util.List;

@Repository
public class LinhaDao implements Dao {

    // super("tblLinha", new String[]{"idLinha", "fkPontoIda", "fkPontoVolta", "numeroLinha"});

    private String consulta;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Object buscarPorId(int id) {
        try{
            Linha linha = new Linha();
            this.consulta = "SELECT * FROM tblLinha where idLinha = ?";

            //linha = jdbcTemplate.queryForObject();

            return linha;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<?> buscarTodos() {
        try{
            this.consulta = "SELECT linha.idLinha, linha.numeroLinha, " +
                    "pontoIda.idPontoFinal AS 'idPontoIda', pontoIda.nomeTerminal AS 'nomeIda', " +
                    "pontoVolta.idPontoFinal AS 'idPontoVolta', pontoVolta.nomeTerminal AS 'nomeVolta' " +
                    "FROM tblLinha AS linha " +
                    "INNER JOIN tblPontoFinal AS pontoIda " +
                    "ON pontoIda.idPontoFinal = linha.fkPontoIda " +
                    "INNER JOIN tblPontoFinal AS pontoVolta " +
                    "ON pontoVolta.idPontoFinal = linha.fkPontoVolta";

            return jdbcTemplate.query(this.consulta, new LinhaMapper());

        }catch(Exception e){
            System.out.println("Erro " + e.getMessage());
            return null;
        }
    }

    @Override
    public int ultimoId() {
        return 0;
    }

    @Override
    public boolean inserir(Object obj) {
        return false;
    }

    @Override
    public boolean alterar(Object obj) {
        return false;
    }

    @Override
    public boolean deletar(int id) {
        return false;
    }
}
