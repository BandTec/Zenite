package orion.zenite.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import orion.zenite.models.Linha;
import orion.zenite.models.PontoFinal;

import java.util.List;

public interface LinhaDao extends JpaRepository<Linha, Integer> {
    @Query(value = "select max(l.id) from Linha l")
    int lastId();

    Linha findByPontoIda(PontoFinal pontoIda);

    List<Linha> findAllByPontoIda(PontoFinal pontoIda);

    Linha findByPontoVolta(PontoFinal pontoVolta);

    List<Linha> findAllByPontoVolta(PontoFinal pontoVolta);

    Linha findByNumeroIgnoreCase(String numero);

    boolean existsByNumero(String numero);
}
