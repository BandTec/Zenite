package orion.zenite.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import orion.zenite.models.Usuario;

public class UsuarioMapper implements RowMapper<Usuario> {

    @Override
    public Usuario mapRow(ResultSet linha, int linhaNum) throws SQLException {
        Usuario usuario = new Usuario();

        usuario.setEmail(linha.getString("email"));
        usuario.setId(linha.getInt("id"));
        usuario.setPassword(linha.getString("password"));
        usuario.setName(linha.getString("name"));

        return usuario;
    }
}