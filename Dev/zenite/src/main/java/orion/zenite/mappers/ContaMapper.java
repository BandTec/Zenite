package orion.zenite.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import orion.zenite.models.Conta;

/*
    * Essa classe mapper filtra o resultado do banco
    * permitindo adicionar corretamente
    * os campos da tabela aos atributos da classe
 */
public class ContaMapper implements RowMapper<Conta> {

    @Override
    public Conta mapRow(ResultSet linha, int linhaNum) throws SQLException {
        Conta conta = new Conta();

        conta.setEmail(linha.getString("email"));
        conta.setIdConta(linha.getInt("idConta"));
        //conta.setNivel(linha.getString("descricao"));
        conta.setNivel(linha.getInt("fkNivel"));
        conta.setSenha(linha.getString("senha"));

        return conta;
    }
}