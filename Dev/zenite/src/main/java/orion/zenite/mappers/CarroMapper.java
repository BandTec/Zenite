package orion.zenite.mappers;

import org.springframework.jdbc.core.RowMapper;
import orion.zenite.models.Carro;
import orion.zenite.models.Dispositivo;
import orion.zenite.models.Linha;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarroMapper implements RowMapper<Carro> {

    @Override
    public Carro mapRow(ResultSet resultSet, int i) throws SQLException {
        Carro carro = new Carro();
        carro.setId(resultSet.getInt("idCarro"));
        carro.setNumero(resultSet.getString("numeroCarro"));

      //  Linha linha = new Linha();
      //  linha.setId(resultSet.getInt("fkLinha"));
      //  carro.setLinha(linha);

        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setTipoDispositivo(resultSet.getInt("fkTipo"));
        dispositivo.setCodigo(resultSet.getString("codigoDispositivo"));
        dispositivo.setId(resultSet.getInt("idDispositivo"));
        carro.setDispositivo(dispositivo);

        return carro;
    }
}
