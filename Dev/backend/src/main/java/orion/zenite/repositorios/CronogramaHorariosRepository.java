package orion.zenite.repositorios;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import orion.zenite.entidades.CronogramaHorarios;

import java.time.LocalDateTime;
import java.util.Optional;

public interface CronogramaHorariosRepository extends JpaRepository<CronogramaHorarios, Integer> {

    //@Query(value = "select max(id_cronograma_horarios) as id_cronograma_horarios from tbl_cronograma_horarios",
    //nativeQuery = true)
    @Query(value = "select max(e.id) from CronogramaHorarios e")
    int lastId();

    @Query(value = "select top 1 * from tbl_cronograma_horarios ch where ch.id_motorista = ?1  and hora_prevista_saida <= ?2 and (ch.viagem_status = 2 or ch.viagem_status = 1);", nativeQuery = true)
    Optional<CronogramaHorarios> findActualOrNextViagem(int id, LocalDateTime data);
}
