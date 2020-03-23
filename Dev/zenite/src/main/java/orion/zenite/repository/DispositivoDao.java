package orion.zenite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import orion.zenite.models.Dispositivo;

public interface DispositivoDao extends JpaRepository<Dispositivo, Integer> {

    @Query(value = "select max(d.id) from Dispositivo d")
    int lastId();
}
