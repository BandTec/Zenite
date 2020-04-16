package orion.zenite.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import orion.zenite.entidades.Carro;
import orion.zenite.entidades.CarroLinha;

import java.util.List;

public interface CarroLinhaRepository extends JpaRepository<CarroLinha, Integer> {

    @Query(value = "select max(c.id) from CarroLinha c")
    int lastId();

    List<CarroLinha> findByIdLinha(int id);
}
