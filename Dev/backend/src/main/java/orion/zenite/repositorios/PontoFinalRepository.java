package orion.zenite.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import orion.zenite.entidades.Fiscal;
import orion.zenite.entidades.PontoFinal;

import java.util.List;

public interface PontoFinalRepository extends JpaRepository<PontoFinal, Integer> {
    @Query(value = "select max(p.id) from PontoFinal p")
    int lastId();

    PontoFinal findByNome(String nome);

    List<PontoFinal> findAllByNomeContaining(String nome);

    @Query(value = "select min(p.id) from PontoFinal p where p.nome = :nome")
    int findByNomeV2(@Param("nome") String nome);
}
