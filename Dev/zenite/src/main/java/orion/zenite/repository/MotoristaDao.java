package orion.zenite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import orion.zenite.models.Motorista;

public interface MotoristaDao extends JpaRepository<Motorista, Integer> {
    @Query(value = "select max(m.id) from Motorista m")
    int lastId();
}
