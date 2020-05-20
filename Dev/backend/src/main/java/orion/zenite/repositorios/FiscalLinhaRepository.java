package orion.zenite.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import orion.zenite.entidades.FiscalLinha;

import java.util.List;

public interface FiscalLinhaRepository extends JpaRepository<FiscalLinha, Integer> {

    @Query(value = "select max(fl.id) from FiscalLinha fl")
    int lastId();

    List<FiscalLinha> findByIdLinha(int id);

    List<FiscalLinha> findByIdFiscal(int id);

}
