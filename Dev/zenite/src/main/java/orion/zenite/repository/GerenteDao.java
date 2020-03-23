package orion.zenite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import orion.zenite.models.Endereco;

public interface GerenteDao extends JpaRepository<Endereco, Integer> {
}
