package orion.zenite.repositorios;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import orion.zenite.entidades.CronogramaHorarios;
import orion.zenite.entidades.Linha;

import java.util.List;
@Repository
@Transactional
public interface CronogramaHorariosRepository extends JpaRepository<CronogramaHorarios, Integer> {

    //@Query(value = "select max(id_cronograma_horarios) as id_cronograma_horarios from tbl_cronograma_horarios",
    //nativeQuery = true)
    @Query(value = "select max(e.id) from CronogramaHorarios e")
    int lastId();

    List<CronogramaHorarios> findByIdCronogramaHorarios(Integer id);

    List<CronogramaHorarios> findAllByLinha(Linha id);
}
