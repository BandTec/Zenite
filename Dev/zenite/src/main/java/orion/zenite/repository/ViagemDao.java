package orion.zenite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import orion.zenite.models.Viagem;

public interface ViagemDao extends JpaRepository<Viagem, Integer> {
    @Query(value = "select max(v.id) from Viagem v")
    int lastId();
}
