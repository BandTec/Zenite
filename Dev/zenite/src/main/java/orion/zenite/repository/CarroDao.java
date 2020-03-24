package orion.zenite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import orion.zenite.models.Carro;
import orion.zenite.models.Dispositivo;

import java.util.List;

public interface CarroDao extends JpaRepository<Carro, Integer> {

    @Query(value = "select max(c.id) from Carro c")
    int lastId();

    Carro findByDispositivo(Dispositivo dispositivo);

    List<Carro> findByNumeroIgnoreCase(String numero);
}
