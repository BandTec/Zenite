package orion.zenite.mappers;

import org.springframework.jdbc.core.RowMapper;
import orion.zenite.models.Conta;
import orion.zenite.models.Linha;
import orion.zenite.models.PontoFinal;

import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * Essa classe mapper filtra o resultado do banco
 * permitindo adicionar corretamente
 * os campos da tabela aos atributos da classe
 */
public class LinhaMapper implements RowMapper<Linha>{

    @Override
    public Linha mapRow(ResultSet resultSet, int i) throws SQLException {
        Linha linha = new Linha();
        linha.setId(resultSet.getInt("idLinha"));
        linha.setNumero(resultSet.getString("numeroLinha"));

        PontoFinal pontoIda = new PontoFinal();
        pontoIda.setId(resultSet.getInt("idPontoIda"));
        pontoIda.setNome(resultSet.getString("nomeIda"));
        linha.setPontoIda(pontoIda);

        PontoFinal pontoVolta = new PontoFinal();
        pontoVolta.setId(resultSet.getInt("idPontoVolta"));
        pontoVolta.setNome(resultSet.getString("nomeVolta"));
        linha.setPontoVolta(pontoVolta);

        return linha;
    }
}