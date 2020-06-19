package orion.zenite.repositorios.dashboard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import orion.zenite.entidades.Dashboard.ViagemPeriodo;

import java.util.List;

public interface ViagemPeriodoRepository extends JpaRepository<ViagemPeriodo, Integer> {

    @Query(nativeQuery = true, value = "select * from vwTempoMedioViagemPeriodo")
    List<ViagemPeriodo> findTempoMedioViagemHora();

}
