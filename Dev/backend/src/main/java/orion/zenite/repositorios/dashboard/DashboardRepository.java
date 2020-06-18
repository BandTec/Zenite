package orion.zenite.repositorios.dashboard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import orion.zenite.entidades.Dashboard.DadosLinha;
import java.util.Optional;

public interface DashboardRepository extends JpaRepository<DadosLinha, String> {
    @Query(nativeQuery = true, value = "SELECT * FROM vwQtdCarrosNaoAlocados")
    Optional<Integer> findCarrosNaoAlocados();

    @Query(nativeQuery = true, value = "SELECT * FROM vwTempoMedioViagemHora")
    Optional<Integer> findTempoMedioViagemHora();
}
