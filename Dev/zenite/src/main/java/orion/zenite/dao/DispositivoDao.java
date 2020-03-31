package orion.zenite.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import orion.zenite.models.Dispositivo;
import orion.zenite.models.TipoDispositivo;

import java.util.List;

public interface DispositivoDao extends JpaRepository<Dispositivo, Integer> {

    @Query(value = "select max(d.id) from Dispositivo d")
    int lastId();

    List<Dispositivo> findByTipoDispositivo(TipoDispositivo tipoDispositivo);
}
