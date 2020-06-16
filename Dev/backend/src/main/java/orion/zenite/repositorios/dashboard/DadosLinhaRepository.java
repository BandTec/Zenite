package orion.zenite.repositorios.dashboard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import orion.zenite.entidades.Dashboard.DadosLinha;
import orion.zenite.entidades.Dashboard.ViagemMotorista;

import java.util.List;

public interface DadosLinhaRepository extends JpaRepository<DadosLinha, String> {
    @Query(nativeQuery = true, value = "SELECT * FROM vwDadosLinha ")
    List<DadosLinha> findDadosLinha();

}
