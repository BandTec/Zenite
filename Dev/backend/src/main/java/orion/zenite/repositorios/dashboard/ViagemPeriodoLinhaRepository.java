package orion.zenite.repositorios.dashboard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import orion.zenite.entidades.Dashboard.ViagemPeriodoLinha;

import java.util.List;

public interface ViagemPeriodoLinhaRepository extends JpaRepository<ViagemPeriodoLinha, String> {
    @Query(nativeQuery = true, value = "select * from vwTempoDeViagemPorPeriodo where id_linha = :idLinha")
    List<ViagemPeriodoLinha> findViagemPeriodoLinha(@Param("idLinha") Integer idLinha);
}
