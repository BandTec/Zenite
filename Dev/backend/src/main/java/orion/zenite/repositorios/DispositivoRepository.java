package orion.zenite.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import orion.zenite.entidades.Dispositivo;
import orion.zenite.entidades.TipoDispositivo;

import java.util.List;

public interface DispositivoRepository extends JpaRepository<Dispositivo, Integer> {

    @Query(value = "select max(d.id) from Dispositivo d")
    int lastId();

    List<Dispositivo> findByTipoDispositivo(TipoDispositivo tipoDispositivo);
}
