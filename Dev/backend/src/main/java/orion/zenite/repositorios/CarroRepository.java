package orion.zenite.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import orion.zenite.entidades.Carro;
import orion.zenite.entidades.Dispositivo;
import orion.zenite.entidades.Linha;

import java.util.List;
import java.util.Optional;

public interface CarroRepository extends JpaRepository<Carro, Integer> {

    @Query(value = "select max(c.id) from Carro c")
    int lastId();

    List<Carro> findAllByNumeroContaining(String numero);

    Optional<Carro> findByDispositivo(Dispositivo dispositivo);

    List<Carro> findByNumeroIgnoreCase(String numero);
}
