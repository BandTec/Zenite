package orion.zenite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import orion.zenite.models.MotoristaCarro;

public interface MotoristaCarroDao extends JpaRepository<MotoristaCarro, Integer> {
    @Query(value = "select max(m.id) from MotoristaCarro m")
    int lastId();
}
