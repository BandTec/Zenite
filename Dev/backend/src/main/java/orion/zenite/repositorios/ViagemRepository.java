package orion.zenite.repositorios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import orion.zenite.entidades.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
public interface ViagemRepository extends JpaRepository<Viagem, Integer> {
    @Query(value = "select max(v.id) from Viagem v")
    int lastId();

    List<Viagem> findByLinha(Linha linha);
    Page<Viagem> findByLinha(Pageable var1, Linha linha);

    List<Viagem> findByCarro(Carro carro);
    Page<Viagem> findByCarro(Pageable var1, Carro carro);

    List<Viagem> findByMotorista(Motorista motorista);
    Page<Viagem> findByMotorista(Pageable var1, Motorista motorista);

    List<Viagem> findByFiscal(Fiscal fiscal);
    Page<Viagem> findByFiscal(Pageable var1, Fiscal fiscal);

    List<Viagem> findByHoraSaidaBetween(LocalDateTime horaComeco, LocalDateTime horaFim);

    List<Viagem> findByHoraChegadaBetween(LocalDateTime horaComeco, LocalDateTime horaFim);

    List<Viagem> findByQtdPassageirosLessThanEqual(int qtd);

    List<Viagem> findByQtdPassageirosGreaterThanEqual(int qtd);

    List<Viagem> findByOrderByQtdPassageiros();
}


