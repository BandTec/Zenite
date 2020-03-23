package orion.zenite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import orion.zenite.models.Linha;

public interface LinhaDao extends JpaRepository<Linha, Integer> {
    @Query(value = "select max(l.id) from Linha l")
    int lastId();
}
