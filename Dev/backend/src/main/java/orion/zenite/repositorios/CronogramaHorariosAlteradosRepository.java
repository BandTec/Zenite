package orion.zenite.repositorios;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import orion.zenite.entidades.CronogramaHorariosAlterados;

public interface CronogramaHorariosAlteradosRepository extends JpaRepository<CronogramaHorariosAlterados, Integer> {

    //@Query(value = "select max(id_cronograma_horarios_alterados) as id_cronograma_horarios_alterados from
    //tbl_cronograma_horarios_alterados", nativeQuery = true)
    @Query(value = "select max(e.id) from CronogramaHorariosAlterados e")
    int lastId();

}
