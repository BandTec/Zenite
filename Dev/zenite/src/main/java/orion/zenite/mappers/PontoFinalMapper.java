package orion.zenite.mappers;


import org.springframework.jdbc.core.RowMapper;
import orion.zenite.models.PontoFinal;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PontoFinalMapper implements RowMapper<PontoFinal> {
    @Override
    public PontoFinal mapRow(ResultSet resultSet, int i) throws SQLException {
        PontoFinal ponto = new PontoFinal();
        ponto.setId(resultSet.getInt("idPontoFinal"));
        ponto.setNome(resultSet.getString("nomeTerminal"));

        return ponto;
    }
}
