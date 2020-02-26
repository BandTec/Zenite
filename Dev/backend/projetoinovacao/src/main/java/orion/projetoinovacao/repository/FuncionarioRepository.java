package orion.projetoinovacao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import orion.projetoinovacao.model.Funcionario;

import java.util.List;
import java.util.Optional;

@Repository
public interface FuncionarioRepository extends CrudRepository<Funcionario, Long> {

    Funcionario findByName(String name);

    Funcionario findByEmail(String email);

    Funcionario findById(long id);

    Boolean existsByName(String name);

    Boolean existsByEmail(String email);
}
