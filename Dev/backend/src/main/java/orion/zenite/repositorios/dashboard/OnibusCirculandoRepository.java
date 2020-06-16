package orion.zenite.repositorios.dashboard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import orion.zenite.entidades.Dashboard.OnibusCirculando;
import java.util.List;

public interface OnibusCirculandoRepository extends JpaRepository<OnibusCirculando, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM vwQtdOnibusCirculandoPorPeriodo where id_linha = :idLinha ")
    List<OnibusCirculando> findOnibusCirculando(@Param("idLinha") Integer idLinha);
}
