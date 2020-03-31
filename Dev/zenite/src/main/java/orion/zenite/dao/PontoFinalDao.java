package orion.zenite.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import orion.zenite.models.PontoFinal;

public interface PontoFinalDao extends JpaRepository<PontoFinal, Integer> {
    @Query(value = "select max(p.id) from PontoFinal p")
    int lastId();

    PontoFinal findByNome(String nome);
}
