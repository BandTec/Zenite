package orion.zenite.mappers;

import org.springframework.jdbc.core.RowMapper;
import orion.zenite.models.Endereco;

import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * Essa classe mapper filtra o resultado do banco
 * permitindo adicionar corretamente
 * os campos da tabela aos atributos da classe
 */
public class EnderecoMapper  implements RowMapper<Endereco> {

    @Override
    public Endereco mapRow(ResultSet linha, int linhaNum) throws SQLException {
        Endereco endereco = new Endereco();

        endereco.setId(linha.getInt("idEndereco"));
        endereco.setCep(linha.getString("CEP"));
        endereco.setCidade(linha.getString("cidade"));
        endereco.setComplemento(linha.getString("complemento"));
        endereco.setEstado(linha.getString("estado"));
        endereco.setLogradouro(linha.getString("logradouro"));
        endereco.setNumero(linha.getString("numero"));

        return endereco;
    }
}