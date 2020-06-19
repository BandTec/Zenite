package orion.zenite.repositorios.dashboard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import orion.zenite.entidades.Dashboard.OperandoParado;
import orion.zenite.entidades.Dashboard.ViagemMotorista;

import java.util.List;
import java.util.Optional;

public interface OperandoParadoRepository extends JpaRepository<OperandoParado, Integer> {

    @Query(nativeQuery=true, value="select * from vwOnibusCirculando")
    Optional<OperandoParado> findOnibusCirculando();

}
