package orion.zenite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import orion.zenite.models.Endereco;

public interface MotoristaCarroDao extends JpaRepository<Endereco, Integer> {
}
