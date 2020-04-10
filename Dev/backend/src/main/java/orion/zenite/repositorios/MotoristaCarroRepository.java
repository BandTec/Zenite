package orion.zenite.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import orion.zenite.entidades.MotoristaCarro;

public interface MotoristaCarroRepository extends JpaRepository<MotoristaCarro, Integer> {
    @Query(value = "select max(m.id) from MotoristaCarro m")
    int lastId();
}
