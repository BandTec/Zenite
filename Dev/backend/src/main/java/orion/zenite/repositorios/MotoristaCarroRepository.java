package orion.zenite.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import orion.zenite.entidades.FiscalLinha;
import orion.zenite.entidades.MotoristaCarro;

import java.util.List;

public interface MotoristaCarroRepository extends JpaRepository<MotoristaCarro, Integer> {
    @Query(value = "select max(m.id) from MotoristaCarro m")
    int lastId();

    List<MotoristaCarro> findByIdMotorista(Integer id);

    List<MotoristaCarro> findByIdCarro(Integer id);

    MotoristaCarro findByIdMotoristaAndIdCarro(Integer idMotorista, Integer idCarro);

}
