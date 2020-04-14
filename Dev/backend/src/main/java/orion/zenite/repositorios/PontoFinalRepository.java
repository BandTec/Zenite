package orion.zenite.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import orion.zenite.entidades.PontoFinal;

public interface PontoFinalRepository extends JpaRepository<PontoFinal, Integer> {
    @Query(value = "select max(p.id) from PontoFinal p")
    int lastId();

    PontoFinal findByNome(String nome);
}
