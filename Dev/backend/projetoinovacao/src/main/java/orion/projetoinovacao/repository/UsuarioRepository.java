package orion.projetoinovacao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import orion.projetoinovacao.model.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    Usuario findByName(String name);

    Usuario findByEmail(String email);

    Usuario findByEmailAndPassword(String email, String password);

    Usuario findById(long id);

    Boolean existsByName(String name);

    Boolean existsByEmail(String email);
}
