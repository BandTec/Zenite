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
public class UsuarioDao{

    private String consulta;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Usuario buscarPorId(int id) {
        this.consulta = "SELECT * FROM usuario WHERE id = ?";

        Usuario usuario = jdbcTemplate.queryForObject(
                consulta,
                new BeanPropertyRowMapper<Usuario>(Usuario.class),
                id
        );

        return usuario;
    }

    public Usuario buscarPorEmailSenha(LoginRequest loginRequest) {
        this.consulta = "SELECT * FROM usuario WHERE email = ? AND password = ?";

        Usuario usuario = jdbcTemplate.queryForObject(
                consulta,
                new BeanPropertyRowMapper<Usuario>(Usuario.class),
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );

        return usuario;
    }

    public List<Usuario> buscarTodos() {
        this.consulta = "SELECT * FROM usuario";
        return jdbcTemplate.query(consulta, new UsuarioMapper());
    }

    public void inserir(Usuario usuario) {
        this.consulta = "INSERT INTO usuario (name, email, password) values (?, ?, ?)";

        jdbcTemplate.update(
                consulta,
                usuario.getName(), usuario.getEmail(), usuario.getPassword()
        );
    }


    public void alterar(Usuario usuario) {
        this.consulta = "UPDATE usuario SET name = ?, email = ?, password = ? WHERE id=?";

        jdbcTemplate.update(
                consulta,
                usuario.getName(), usuario.getEmail(), usuario.getPassword(), usuario.getId()
                );
    }

    public void deletar(int id) {
        this.consulta = "DELETE FROM usuario WHERE id = ?";
        jdbcTemplate.update(consulta, id);
    }

}
