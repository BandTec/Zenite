package orion.zenite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import orion.zenite.models.CarroLinha;

public interface CarroLinhaDao extends JpaRepository<CarroLinha, Integer> {

    @Query(value = "select max(c.id) from CarroLinha c")
    int lastId();
}
