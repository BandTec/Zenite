package orion.zenite.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import orion.zenite.entidades.CarroLinha;

public interface CarroLinhaRepository extends JpaRepository<CarroLinha, Integer> {

    @Query(value = "select max(c.id) from CarroLinha c")
    int lastId();
}
