package orion.zenite.repositorios;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import orion.zenite.entidades.CronogramaHorariosAlterados;

public interface CronogramaHorariosAlteradosRepository extends JpaRepository<CronogramaHorariosAlterados, Integer> {

    //@Query(value = "select max(id_cronograma_horarios_alterados) from tbl_cronograma_horarios_alterados where id_cronograma = :id_Cronograma", nativeQuery = true)
    @Query(value = "select max(e.id) from CronogramaHorariosAlterados e")
    Integer lastId(@Param("id_Cronograma") int idCronograma);

    @Query(value = "select max(id_cronograma_horarios_alterados) from tbl_cronograma_horarios_alterados where id_cronograma = :id_Cronograma", nativeQuery = true)
    Integer findByIdCronograma(@Param("id_Cronograma") int idCronograma);

}
