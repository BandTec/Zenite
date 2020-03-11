package orion.zenite.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import orion.zenite.mappers.UsuarioMapper;
import orion.zenite.models.Usuario;
import orion.zenite.payload.LoginRequest;

import java.util.List;

@Repository
public class UsuarioDao {

    private String consulta;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Usuario> getTodosUsuarios() {
        this.consulta = "select * from usuario";
        return jdbcTemplate.query(consulta, new UsuarioMapper());
    }

    public Usuario pesquisarEmailSenha(LoginRequest loginRequest) {

        this.consulta = "select * from usuario where email = ? and password = ?";

        Usuario usuario = jdbcTemplate.queryForObject(
                consulta,
                new BeanPropertyRowMapper<Usuario>(Usuario.class),
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );

        return usuario;
    }


}
