package orion.zenite.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import orion.zenite.entidades.Carro;
import orion.zenite.entidades.Dispositivo;

import java.util.List;

public interface CarroRepository extends JpaRepository<Carro, Integer> {

    @Query(value = "select max(c.id) from Carro c")
    int lastId();

    Carro findByDispositivo(Dispositivo dispositivo);

    List<Carro> findByNumeroIgnoreCase(String numero);
}
