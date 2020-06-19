package orion.zenite.repositorios.dashboard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import orion.zenite.entidades.Dashboard.ViagemMotorista;

import java.util.List;


public interface ViagemMotoristaRepository extends JpaRepository<ViagemMotorista, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM vwTempoViagemPorMotorista where id_linha = :idLinha ")
    List<ViagemMotorista> findViagemMotorista(@Param("idLinha") Integer idLinha);

}
