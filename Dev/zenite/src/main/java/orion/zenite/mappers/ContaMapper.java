package orion.zenite.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import orion.zenite.models.Conta;
import orion.zenite.models.Nivel;

/*
    * Essa classe mapper filtra o resultado do banco
    * permitindo adicionar corretamente
    * os campos da tabela aos atributos da classe
 */
public class ContaMapper implements RowMapper<Conta> {

    @Override
    public Conta mapRow(ResultSet linha, int linhaNum) throws SQLException {
        Conta conta = new Conta();
        Nivel nivel;

        conta.setEmail(linha.getString("email"));
        conta.setId(linha.getInt("idConta"));
        conta.setNivel(Nivel.valueOf(linha.getString("descricao").toUpperCase()));
        conta.setSenha(linha.getString("senha"));

        return conta;
    }
}