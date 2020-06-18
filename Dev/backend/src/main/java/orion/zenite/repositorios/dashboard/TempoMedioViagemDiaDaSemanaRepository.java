package orion.zenite.repositorios.dashboard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import orion.zenite.entidades.Dashboard.TempoMedioViagemDiaDaSemana;

import java.util.List;

public interface TempoMedioViagemDiaDaSemanaRepository extends JpaRepository<TempoMedioViagemDiaDaSemana, Integer> {
    @Query(nativeQuery = true, value = "select * from vwTempoMedioViagemPorDiaSemana where id_linha= :idLinha")
    List<TempoMedioViagemDiaDaSemana> findTempoMedioViagemDiaDaSemana(@Param("idLinha") Integer idLinha);
}
