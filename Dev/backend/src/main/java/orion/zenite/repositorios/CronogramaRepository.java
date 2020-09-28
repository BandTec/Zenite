package orion.zenite.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import orion.zenite.entidades.Cronograma;

public interface CronogramaRepository extends JpaRepository<Cronograma, Integer> {

    //@Query(value = "select max(id_cronograma) as id_cronograma from tbl_cronograma", nativeQuery = true)
    @Query(value = "select max(e.id) from Cronograma e")
    int lastId();

}
