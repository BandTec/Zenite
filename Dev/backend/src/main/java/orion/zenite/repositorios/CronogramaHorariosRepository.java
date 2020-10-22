package orion.zenite.repositorios;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import orion.zenite.entidades.*;

import java.util.List;

public interface CronogramaHorariosRepository extends JpaRepository<CronogramaHorarios, Integer> {

    //@Query(value = "select max(id_cronograma_horarios) as id_cronograma_horarios from tbl_cronograma_horarios",
    //nativeQuery = true)
    @Query(value = "select max(e.id) from CronogramaHorarios e")
    int lastId();

    List<CronogramaHorarios> findByCronograma(Cronograma cronograma);

    List<CronogramaHorarios> findByMotorista(Motorista motorista);

}
