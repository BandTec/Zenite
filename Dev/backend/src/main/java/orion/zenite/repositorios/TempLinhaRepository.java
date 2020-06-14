package orion.zenite.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import orion.zenite.entidades.Linha;
import orion.zenite.entidades.PontoFinal;
import orion.zenite.entidades.TempLinha;

import java.util.List;

public interface TempLinhaRepository extends JpaRepository<TempLinha, Integer> {

    @Query(value = "select max(l.id) from TempLinha l")
    int lastId();

    List<Linha> findAllByNumeroContaining(String numero);

    Linha findByPontoIda(PontoFinal pontoIda);

    List<Linha> findAllByPontoIda(PontoFinal pontoIda);

    Linha findByPontoVolta(PontoFinal pontoVolta);

    List<Linha> findAllByPontoVolta(PontoFinal pontoVolta);

    List<Linha> findByNumeroIgnoreCaseContaining(String numero);

    List<Linha> findAllByPontoIdaNomeIgnoreCaseContaining(String nome);

    List<Linha> findAllByPontoVoltaNomeIgnoreCaseContaining(String nome);

    boolean existsByNumero(String numero);

}
