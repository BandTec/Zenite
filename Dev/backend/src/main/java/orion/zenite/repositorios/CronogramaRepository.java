package orion.zenite.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import orion.zenite.entidades.Cronograma;

public interface CronogramaRepository extends JpaRepository<Cronograma, Integer> {

    @Query(value = "select max(c.id) from cronograma c")
    int lastId();

}
