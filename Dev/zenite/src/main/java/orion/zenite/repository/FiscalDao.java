package orion.zenite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import orion.zenite.models.Fiscal;

public interface FiscalDao extends JpaRepository<Fiscal, Integer> {

    @Query(value = "select max(f.id) from Fiscal f")
    int lastId();
}
